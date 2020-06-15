package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "shuffle"
 */
public  class Shuffle implements Command {

    @Override
    public Packet execute(Storage storage) {
        return storage.shuffle();
    }
}
