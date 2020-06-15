package Controls;

import Human.HumanBeing;
import Objects.Command;
import Objects.CommandPacket;
import Objects.ObjectPacket;
import Objects.Packet;

import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String args[]) throws Exception {

        int portNumber = 1707;

        System.out.println("Введите команду:");

        BufferedReader input =
                new BufferedReader(new InputStreamReader
                        (System.in));
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress =
                InetAddress.getByName("localhost");
        clientSocket.setSoTimeout(4000);

        byte[] sendData;
        byte[] receiveData = new byte[1024 * 16];

        Command command;
        Packet packet;

        while (true) {
            try {
                command = CommandListener.getCommand(input.readLine());
                if (command == null) {
                    continue;
                }
                sendData = PacketSender.serialize(new CommandPacket(command, "Command"));
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length,
                                IPAddress, portNumber);

                clientSocket.send(sendPacket);
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData,
                                receiveData.length);
                try {
                    clientSocket.receive(receivePacket);
                } catch (SocketTimeoutException e) {
                    System.out.println("Повторите подключение");
                    clientSocket.close();
                    break;
                }
                packet = PacketReceiver.deserialize(receivePacket.getData());
                if (packet instanceof ObjectPacket) {
                    for (Object h : ((ObjectPacket) packet).objects) {
                        System.out.println(h);
                    }
                } else {
                    if (packet.content.equals("end")) {
                        System.out.println("Клиент завершает работу");
                        clientSocket.close();
                        break;
                    } else {
                        System.out.println(packet.content);
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка в формате данных");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
