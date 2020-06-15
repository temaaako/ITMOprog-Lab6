package Controls;

import Objects.Packet;

import java.net.SocketAddress;

public class DirectionalPackage {
    private Packet packet;
    private final SocketAddress socketAddress;

    public DirectionalPackage(SocketAddress socketAddress, Packet packet) {
        this.packet = packet;
        this.socketAddress = socketAddress;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }
}
