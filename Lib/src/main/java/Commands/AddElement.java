package Commands;

import Controls.Storage;
import Human.HumanBeing;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "add_element"
 */
public class AddElement implements Command {
    private final HumanBeing human;

    public AddElement(HumanBeing human){
        this.human = human;
    }

    public Packet execute(Storage storage){
        return storage.addElement(this.human);
    }
}
