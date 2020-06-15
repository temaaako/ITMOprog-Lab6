package Controls;

import Objects.Command;
import Objects.CommandPacket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PacketSender {
    public static byte[] serialize(CommandPacket commandPacket) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream output = new ObjectOutputStream(outputStream)) {
            output.writeObject(commandPacket);
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}