package Commands;

import Controls.Storage;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "print_unique_weapon_type"
 */
public  class PrintUniqueWeaponType implements Command {

    @Override
    public Packet execute(Storage storage) {
        return storage.printUniqueWeaponType();
    }
}
