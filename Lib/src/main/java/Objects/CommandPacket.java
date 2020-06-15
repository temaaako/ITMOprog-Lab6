package Objects;


public class CommandPacket extends Packet {
    public final Command command;

    public CommandPacket(Command command, String message) {
        super(message);
        this.command = command;
    }
}
