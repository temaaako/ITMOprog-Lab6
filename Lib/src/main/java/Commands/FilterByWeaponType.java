package Commands;

import Controls.Storage;
import Human.WeaponType;
import Objects.Command;
import Objects.Packet;

/**
 * Команда "filter_by_weapon_type"
 */
public  class FilterByWeaponType implements Command {
WeaponType weaponType;

    public FilterByWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public Packet execute(Storage storage) {
        return storage.filterByWeaponType(this.weaponType);
    }
}
