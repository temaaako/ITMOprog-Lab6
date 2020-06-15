package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "show"
 */
public  class Show implements Command {
    @Override
    public Packet execute(Storage storage) {
        return storage.show();
    }
}
