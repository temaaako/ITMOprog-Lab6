package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "help"
 */
public  class Help implements Command {

    @Override
    public Packet execute(Storage storage) {
        return storage.help();
    }
}
