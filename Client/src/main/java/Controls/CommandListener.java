package Controls;

import Commands.*;
import Human.Car;
import Human.HumanBeing;
import Human.WeaponType;
import Objects.Command;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Класс, отвечающий за считывание команд с клавиатуры и инициирования их исполнения
 */
public class CommandListener {
    static boolean isScript;
    /**
     * Метод, считывающий команды, введеные пользователем
     *
     * @param list лист, который будет изменяться
     * @param file файл, из которого получен лист
     */
    public static void listenCommand(ArrayList<HumanBeing> list, File file) {
        /**
         * {@code Scanner} для ввода пользовательской информации
         */
        Scanner scanner = new Scanner(System.in);
        do {
            getCommand(scanner.nextLine());
        } while (true);
    }

    /**
     * Метод, отвечающий за вызов команды
     *
     */

    public static Command getCommand(String userInput) {
        String[] cmd = userInput.split(" ");
        try {
            switch (cmd[0].toUpperCase()) {
                case "ADD":
                    if (cmd[1].equals("random") && cmd.length<=3){
                        if (cmd.length == 3 && Integer.parseInt(cmd[2])<=150){
                            int count = Integer.parseInt(cmd[2]);
                            Command[] commands = new Command[count];
                            for (int i = 0; i<count; i++ ){
                                commands[i] = new AddElement(HumanBeing.getRandomHuman());
                            }
                            return new ExecuteScript(commands);
                        }
                        else {
                            return new AddElement(HumanBeing.getRandomHuman());
                        }
                    }
                    if (CommandValidator.addOrRemoveElementIsCorrect(cmd)){
                        return new AddElement(new HumanBeing(cmd[1],cmd[2], cmd[3],cmd[4], cmd[5],cmd[6], cmd[7],cmd[8], cmd[9],cmd[10]));
                    }
                    break;
                case "CLEAR":
                    if (cmd.length>1) return null;
                    return new Clear();
                case "EXECUTE_SCRIPT":
                    if (isScript){
                        return null;
                    }
                    isScript= true;
                    String path = userInput.split(" ", 2)[1];
                    File file = new File(path);
                    Command[] scriptCommands = Parser.readFile(file);
                    isScript = false;
                    if (scriptCommands==null){
                        return null;
                    }
                    return new ExecuteScript(scriptCommands);
                case "EXIT":
                    if (cmd.length>1) return null;
                    return new Exit();
                case "FILTER_BY_WEAPON_TYPE":
                    if (CommandValidator.filterByWeaponTypeIsCorrect(cmd))
                    return new FilterByWeaponType(WeaponType.valueOf(cmd[1]));
                case "FILTER_GREATER_THAN_CAR":
                    if (cmd.length==2 && cmd[1].equals("random"))
                        return new FilterGreaterThanCar(Car.getRandomCar());
                    if(CommandValidator.filterGreaterThanCarIsCorrect(cmd)){
                        return new FilterGreaterThanCar(new Car(cmd[1],Boolean.parseBoolean(cmd[2])));
                    }
                    break;
                case "HELP":
                    if (cmd.length>1) return null;
                    return new Help();
                case "INFO":
                    if (cmd.length>1) return null;
                    return new Info();
                case "INSERT_AT_INDEX":
                    if (cmd.length==3 && cmd[2].equals("random")){
                        return new InsertAtIndex(Integer.parseInt(cmd[1]), HumanBeing.getRandomHuman());
                    }
                    if (CommandValidator.insertIsCorrect(cmd)){
                        return new InsertAtIndex(Integer.parseInt(cmd[1]), new HumanBeing(cmd[2],cmd[3],cmd[4],cmd[5],cmd[6],cmd[7],cmd[8],cmd[9],cmd[10],cmd[11]));
                    }
                    break;
                case "PRINT_UNIQUE_WEAPON_TYPE":
                    if (cmd.length>1) return null;
                    return new PrintUniqueWeaponType();
                case "REMOVE_BY_ID":
                    if (CommandValidator.removeByIdIsCorrect(cmd))
                    return new RemoveById(Long.parseLong(cmd[1]));
                    return null;
                case "REMOVE_LOWER_THAN":
                    if (cmd[1].equals("random") && cmd.length==2){
                        return new RemoveLowerThan(HumanBeing.getRandomHuman());
                    }
                    if (CommandValidator.addOrRemoveElementIsCorrect(cmd))
                    return new RemoveLowerThan(new HumanBeing(cmd[1],cmd[2], cmd[3],cmd[4], cmd[5],cmd[6], cmd[7],cmd[8], cmd[9],cmd[10]));
                case "SHOW":
                    if (cmd.length>1) return null;
                    return new Show();
                case "SHUFFLE":
                    if (cmd.length>1) return null;
                    return new Shuffle();
                case "UPDATE":
                    if  (cmd[2].equals("random")){
                        return new Update(Long.parseLong(cmd[1]), HumanBeing.getRandomHuman());
                    }
                    if (CommandValidator.updateIsCorrect(cmd))
                    return new Update(Long.parseLong(cmd[1]), new HumanBeing(cmd[2],cmd[3],cmd[4],cmd[5],cmd[6],cmd[7],cmd[8],cmd[9],cmd[10],cmd[11]));
                    return null;
                default:
                    System.out.println("Команды "+ "\""+ cmd[0]+"\""+ " не существует");
                    return null;
            }
            return null;
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Вы забыли ввести аргумент");
            return null;
        }
    }
}
