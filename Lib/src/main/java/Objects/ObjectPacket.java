package Objects;

import Human.HumanBeing;

import java.util.ArrayList;

public class ObjectPacket extends Packet {
    public final Object[] objects;
    public ObjectPacket(String content, Object[] objects) {
        super(content);
        this.objects=objects;

    }
}
