package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "execute_script"
 */
public  class ExecuteScript implements Command {


  Command[] commands;

    @Override
    public Packet execute(Storage storage) {
        return storage.executeScript(this.commands);
    }

    public ExecuteScript(Command[] commands) {
        this.commands = commands;
    }
}
