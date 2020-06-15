package Controls;

import Human.Mood;
import Human.WeaponType;

public abstract class CommandValidator {
    public static boolean addOrRemoveElementIsCorrect(String[] command) {
        if (command.length == 11) {         //add element
            if (newElementIsCorrect(command)) {
                return true;
            } else {
                System.out.println("Ошибка ввода");
                return false;
            }
        } else {
            System.out.println("Ошибка ввода");
            return false;
        }
    }

    public static boolean updateIsCorrect(String[] command) {
        try {
            Long.parseLong(command[1]);
        }
        catch (Exception e){
            return false;
        }
        return twelveArgumentsWithNewElement(command);
    }

    public static boolean insertIsCorrect(String[] command) {
        try {
            Integer.parseInt(command[1]);
        }
        catch (Exception e){
            return false;
        }
        return twelveArgumentsWithNewElement(command);
    }

    private static boolean twelveArgumentsWithNewElement(String[] command) {
        if (command.length == 12) {         //add element
            if (newElementIsCorrect(command)) {
                return true;
            } else {
                System.out.println("Ошибка ввода");
                return false;
            }
        } else {
            System.out.println("Ошибка ввода");
            return false;
        }
    }


    public static boolean newElementIsCorrect(String[] command) {
        int l = command.length;
        try {
            Integer.parseInt(command[l - 9]);
            Integer.parseInt(command[l - 8]);
            Boolean.parseBoolean(command[l - 7]);
            Boolean.parseBoolean(command[l - 6]);
            Double.parseDouble(command[l - 5]);

            WeaponType.valueOf(command[l - 4]);
            Mood.valueOf(command[l - 3]);

            Boolean.parseBoolean(command[l - 1]);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка ввода");
            return false;
        }
    }
    public static boolean filterByWeaponTypeIsCorrect(String[] command){
        if (command.length>2) return false;
            try{
                WeaponType.valueOf(command[1]);
                return true;
            }
            catch (Exception e){
                return false;
            }

    }
    public static boolean filterGreaterThanCarIsCorrect(String[] command){
        if (command.length>3) return false;
        try{
            Boolean.parseBoolean(command[2]);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static boolean removeByIdIsCorrect(String[] command) {
        if (command.length>2) return false;
        try{

            Long.parseLong(command[1]);

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
//add_element {name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness}
//public static Long count;
////    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
////    private String name; //Поле не может быть null, Строка не может быть пустой
////    private Coordinates coordinates; //Поле не может быть null
////    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
////    private boolean realHero;
////    private Boolean hasToothpick; //Поле не может быть null
////    private Double impactSpeed; //Максимальное значение поля: 913, Поле может быть null
////    private WeaponType weaponType; //Поле может быть null
////    private Mood mood; //Поле не может быть null
////    private Car car; //Поле может быть null