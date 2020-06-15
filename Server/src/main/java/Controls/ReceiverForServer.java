package Controls;

import Objects.Packet;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;

import static Controls.PacketReceiver.deserialize;

public class ReceiverForServer {
    public static DirectionalPackage read(SelectionKey key) throws IOException {
        DatagramChannel chan = (DatagramChannel)key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(16*1024);
        SocketAddress socketAddress = (chan.receive(byteBuffer));
        Packet packet = deserialize(byteBuffer.array());
        return new DirectionalPackage(socketAddress, packet);
    }
}
