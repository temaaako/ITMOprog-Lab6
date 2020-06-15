package Controls;


import Commands.Exit;
import Objects.Command;
import Objects.CommandPacket;
import Objects.Packet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class Server {

    public static DatagramChannel channel;
    public static Selector selector;
    static StorageForServer storageForServer = new StorageForServer();


    public static void checkConsole() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        long endOfSession = System.currentTimeMillis() + 500;

        try {
            while (System.currentTimeMillis() <= endOfSession) {
                if (bufferedReader.ready()) {
                    String command = bufferedReader.readLine();
                    if (command.equals("save")) {
                        storageForServer.save();
                        System.out.println("Коллекция сохранена");
                    }
                    if (command.equals("exit")) {
                        try {
                            channel.close();
                            selector.close();
                            System.exit(0);
                        } catch (Exception e) {
                            channel.close();
                            selector.close();
                            System.exit(1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Сервер инициализируется");
            storageForServer.loadFile();
            channel = DatagramChannel.open();
            selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            channel.bind(new InetSocketAddress(1707));
            System.out.println("Сервер начал работу");

            while (true) {
                int readyChannels = selector.selectNow();
                checkConsole();
                if (readyChannels == 0) continue;
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {

                    SelectionKey key = keyIterator.next();

                    if (key.isReadable()) {
                        DirectionalPackage directionalPackage = ReceiverForServer.read(key);
                        System.out.println("Сервер принял сообщение");
                        Command command = ((CommandPacket) directionalPackage.getPacket()).command;
                        if (command instanceof Exit) {
                            directionalPackage.setPacket(new Packet("end"));
                            key.attach(directionalPackage);
                        }
                        else {
                            try {
                                directionalPackage.setPacket(command.execute(storageForServer));
                                key.attach(directionalPackage);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        key.interestOps(SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        SenderForServer.write(key);
                        System.out.println("Сервер отправил сообщение");
                        key.interestOps(SelectionKey.OP_READ);
                    }

                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
