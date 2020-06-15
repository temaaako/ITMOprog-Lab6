package Human;

import java.util.ArrayList;
/**
 *Служебный класс, который проверяет id на уникальность
 */
public abstract class IdChecker {
    /**
     *Метод для проверки id
     * @param id значение id
     * @param list лист, в котором проверяется наличие id
     * @return уникальность id
     */
    public static boolean idIsUnique(Long id, ArrayList<HumanBeing> list){
        for(HumanBeing human : list){
            if (human.getId()==id){
                return false;
            }
        }
        return true;
    }
}
