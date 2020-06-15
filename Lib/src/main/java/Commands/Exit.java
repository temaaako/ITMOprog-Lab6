package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "exit"
 */
public  class Exit implements Command {
    @Override
    public Packet execute(Storage storage) {
        return storage.exit();
    }

}
