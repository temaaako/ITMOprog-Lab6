package Objects;

import java.io.Serializable;

public class Packet implements Serializable {
    public final String content;
    public Packet(String content) {
        this.content = content;
    }
}
