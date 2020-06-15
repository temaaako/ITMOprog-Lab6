package Commands;

import Controls.Storage;
import Human.HumanBeing;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "insert_at_index"
 */
public  class InsertAtIndex implements Command {
    int index;
    HumanBeing human;


    public InsertAtIndex(int index, HumanBeing human) {
        this.index = index;
        this.human = human;
    }

    public Packet execute(Storage storage) {
            return storage.insertAtIndex(this.index, this.human);
    }
}
