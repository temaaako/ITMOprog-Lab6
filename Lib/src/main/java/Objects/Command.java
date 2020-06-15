package Objects;

import Controls.Storage;

import java.io.Serializable;

public interface Command extends Serializable {

    Packet execute(Storage storage);
}
