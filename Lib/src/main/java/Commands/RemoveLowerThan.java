package Commands;

import Controls.Storage;
import Human.HumanBeing;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "remove_lower_than"
 */
public class RemoveLowerThan implements Command {
    HumanBeing human;

    public RemoveLowerThan(HumanBeing human) {
        this.human = human;
    }

    @Override
    public Packet execute(Storage storage) {
        return storage.removeLowerThan(this.human);
    }
}
