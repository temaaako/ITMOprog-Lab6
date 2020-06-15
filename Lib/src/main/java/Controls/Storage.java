package Controls;

import Human.Car;
import Human.HumanBeing;
import Human.WeaponType;
import Objects.Command;
import Objects.Packet;

public interface Storage {
    Packet addElement(HumanBeing human);
    Packet insertAtIndex(int index, HumanBeing human);
    Packet clear();
    Packet executeScript(Command[] commands);
    Packet exit();
    Packet filterByWeaponType(WeaponType weaponType);
    Packet filterGreaterThanCar(Car car);
    Packet help();
    Packet info();
    Packet printUniqueWeaponType();
    Packet removeById(Long id);
    Packet removeLowerThan(HumanBeing human);
    void save();
    Packet show();
    Packet shuffle();
    Packet update(Long id, HumanBeing human);

}
