package Controls;

import Objects.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;

public class SenderForServer {
    private static byte[] serialize(Packet message) {
        System.out.println("Выполняется сериализация");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(outputStream)) {
            output.writeObject(message);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
    public static void write(SelectionKey key) throws IOException {
        DirectionalPackage directionalPackage = (DirectionalPackage) key.attachment();
        DatagramChannel chan = (DatagramChannel)key.channel();
        SocketAddress socketAddress = directionalPackage.getSocketAddress();
        chan.send(ByteBuffer.wrap(serialize(directionalPackage.getPacket())), socketAddress);
    }
}
