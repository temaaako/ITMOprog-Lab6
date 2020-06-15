package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "info"
 */
public  class Info  implements Command {


    @Override
    public Packet execute(Storage storage) {
        return storage.info();
    }
}
