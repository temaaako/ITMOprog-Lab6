package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "clear"
 */
public  class Clear implements Command {

    @Override
    public Packet execute(Storage storage) {
        return storage.clear();
    }
}
