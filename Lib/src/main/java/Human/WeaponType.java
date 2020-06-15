package Human;

import java.lang.ref.WeakReference;
import java.util.Random;

public enum WeaponType {
    HAMMER,
    SHOTGUN,
    RIFLE,
    BAT;

    public static WeaponType randomWeapon() {
        int pick = new Random().nextInt(WeaponType.values().length);
        return WeaponType.values()[pick];
    }
}