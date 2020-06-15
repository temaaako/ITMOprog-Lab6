package Controls;

import Human.Car;
import Human.HumanBeing;
import Human.WeaponType;
import Objects.Command;
import Objects.ObjectPacket;
import Objects.Packet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;


public class StorageForServer implements Storage {
    static ArrayList<HumanBeing> list;
    String fileName = "list_content.json";
    LocalDateTime creationDate;
    long idMAX;
    static {
        list = new ArrayList<>();
    }

    @Override
    public Packet addElement(HumanBeing human) {
        System.out.println("Выполняется команда add");
        human.addIdAndDate(++idMAX);
        list.add(human);

        return new Packet("Элемент добавлен в коллекцию");

    }

    public Packet insertAtIndex(int index, HumanBeing human) {
        System.out.println("Выполняется команда insertAtIndex");
        list.add(index, human);
        human.addIdAndDate(++idMAX);
        return new Packet("Элемент был добавлен в коллекцию");
    }

    @Override
    public Packet clear() {
        System.out.println("Выполняется команда clear");
        list.clear();
        return new Packet("Коллекция очищена!");
    }

    @Override
    public Packet executeScript(Command[] commands) {
        System.out.println("Выполняется команда executeScript");
        for (Command cmd: commands
        ) {
            cmd.execute(this);
        }
        return new Packet("Все комманды были выполнены");
    }

    @Override
    public Packet exit() {
        System.out.println("Выполняется команда exit");
        return new Packet("end");
    }

    @Override
    public Packet filterByWeaponType(WeaponType weaponType) {
        System.out.println("Выполняется команда filterByWeaponType");
        return new ObjectPacket("Получены следующие объекты", list.stream().filter(h -> h.getWeaponType().equals(weaponType)).toArray());
    }

    @Override
    public Packet filterGreaterThanCar(Car car) {
        System.out.println("Выполняется команда filterGreaterThanCar");
        return new ObjectPacket("Получены следующие объекты", list.stream().filter(h -> h.getCar().compareTo(car)>0).toArray());
    }

    public Packet help() {
        System.out.println("Выполняется команда help");
        return new Packet("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness} : добавить новый элемент в коллекцию\n" +
                "update {id} {name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id {id} : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script {path}: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at_index {index} {name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness} : добавить новый элемент в заданную позицию\n" +
                "shuffle : перемешать элементы коллекции в случайном порядке\n" +
                "remove_lower {name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "filter_by_weapon_type {weaponType} : вывести элементы, значение поля weaponType которых равно заданному\n" +
                "filter_greater_than_car {carName} : вывести элементы, значение поля car которых больше заданного\n" +
                "print_unique_weapon_type : вывести уникальные значения поля weaponType всех элементов в коллекции");
    }

    @Override
    public Packet info() {
        System.out.println("Выполняется команда info");
        return new Packet("Дата создания: "+creationDate + "\nТип коллекции: " + list.getClass() + "\nКоличество элементов: " + list.size() );
    }

    @Override
    public Packet printUniqueWeaponType() {
        System.out.println("Выполняется команда printUniqueWeaponType");
        return new ObjectPacket("Уникальные типы оружия: ", list.stream().map(HumanBeing::getWeaponType).distinct().toArray());
    }

    @Override
    public Packet removeById(Long id) {
        System.out.println("Выполняется команда removeById");
        //list.removeIf(h -> h.getId().equals(id));
        list = list.stream().filter(h -> !h.getId().equals(id)).collect(Collectors.toCollection(ArrayList::new));
        return new Packet("Объект удален");
    }

    @Override
    public Packet removeLowerThan(HumanBeing human) {
        System.out.println("Выполняется команда removeLowerThan");
        list = list.stream().filter(h -> !h.equals(human)).collect(Collectors.toCollection(ArrayList::new));
        return new Packet("Элементы удалены");
    }



    @Override
    public Packet show() {
        System.out.println("Выполняется команда show");
        return new ObjectPacket("Получены следующие объекты",  list.toArray(new HumanBeing[0]));
    }

    @Override
    public Packet shuffle() {
        System.out.println("Выполняется команда shuffle");
        Collections.shuffle(list);
        return new Packet("Коллекция перемешана");
    }

    @Override
    public Packet update(Long id, HumanBeing human) {
        System.out.println("Выполняется команда update");
        try {
            list.stream().filter(h -> h.getId().equals(id)).findFirst().get().update(human);
        }
        catch (Exception e){
            return new Packet("При обновлении произошла ошибка");
        }
        return new Packet("Элемент обновлен");
    }

    public static ArrayList<HumanBeing> getListFromText(String text) {
        if (text != null) {
            try {
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<HumanBeing>>() {
                }.getType();
                System.out.println("Данные получены!");
                return gson.fromJson(text, listType);
            } catch (Exception e) {
                System.out.println("Ошибка в получении данных из файла.");
                return new ArrayList<>();
            }
        } else {
            System.out.println("Документ пуст");
            return new ArrayList<>();
        }
    }

    public void loadFile() {
        try {
            System.out.println("Выполняется загрузка файла");
            File file = new File(fileName);
            if (!FileSelector.checkAccess(file)) {
                file.createNewFile();
                System.out.println("Файл был создан");
            }
            System.out.println("Выбран файл " + fileName);
            StringBuilder info = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    info.append(line + "\n");
                }
            } catch (Exception e) {
                System.out.println("Ошибка инициализации");
            }
            list = getListFromText(info.toString());
            creationDate = creationDate.now();
            idMAX = list.stream().mapToLong(HumanBeing::getId).max().orElse(0);
        } catch (Exception e) {
            list = new ArrayList<>();
            System.out.println("Данные в файле некорректны");
        }
    }

    @Override
    public void save() {
        System.out.println("Выполняется сохранение файла");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String str = gson.toJson(list);
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(str.getBytes());
            System.out.println("Данные были сохранены в файл");
        } catch (IOException e) {
            System.out.println("Произошла ошибка при записи данных в файл. Попробуйте ещё раз.");
        }
    }

    public Packet execute(Method method, Object[] arguments) throws
            InvocationTargetException, IllegalAccessException {
        return (Packet) method.invoke(this, (Object) arguments);
    }

    ///////////////////////////////////
    /**private  void executeProgramFromFile(File file, ArrayList<HumanBeing> list) {
     String commandsText = InfoGetter.readFile(file);
     String[] commands = commandsText.split("\\r?\\n");
     for (String command : commands) {
     CommandListener.executeCommandWithList(command, list, file);
     }
     System.out.println("Все команды были выполнены!");
     }
     private  void exitProgram(){
     System.out.println("До скорых встреч");
     System.exit(0);

     }
     private  void filterByWeaponType(String weapon, ArrayList<HumanBeing> list){
     for (HumanBeing human : list){
     if (human.getWeaponType().name().equalsIgnoreCase(weapon)) {
     System.out.println(human.toString());
     }

     }
     }
     private  void filterByCar(String carName, ArrayList<HumanBeing> list) {
     for (HumanBeing human : list) {
     if (carName.compareTo(human.getCar().getName())<0){
     System.out.println(human.toString());
     }
     }
     }
     public InsertAtIndex(int index, HumanBeing human) {
     this.index = index;
     this.human = human;
     }
     private  void printUniqueWeaponType(ArrayList<HumanBeing> list){
     ArrayList<WeaponType> types = new ArrayList<>();
     for(var human : list){
     if(!types.contains(human.getWeaponType())){
     types.add(human.getWeaponType());
     System.out.println(human.getWeaponType());
     }
     }
     }
     private  void removeById(Long id, ArrayList<HumanBeing> list) {
     for (int i = 0; i < list.size(); i++) {
     if (list.get(i).getId().equals(id)) {
     list.remove(list.get(i));
     System.out.println("Объект был удален из коллекции");
     break;
     }
     }
     }
     private  void removeLowerThan(HumanBeing human, ArrayList<HumanBeing> list){
     for (int i=0; i<list.size(); i++){
     if (human.compareTo(list.get(i))>0){
     list.remove(list.get(i));
     i--;
     }
     }
     System.out.println("Все выбранные элементы были удалены");
     }
     private  void saveFile(File file, ArrayList<HumanBeing> list) {
     Gson gson = new GsonBuilder().setPrettyPrinting().create();
     String str = gson.toJson(list);
     try (FileOutputStream fos = new FileOutputStream(file)) {
     fos.write(str.getBytes());
     System.out.println("Данные были сохранены в файл");
     } catch (IOException e) {
     System.out.println("Произошла ошибка при записи данных в файл. Попробуйте ещё раз.");;
     }
     }
     private  void show(ArrayList<HumanBeing> list){
     for (var human :
     list) {
     System.out.println(human.toString());
     }
     }
     private  void shuffle(ArrayList<HumanBeing> list){
     Collections.shuffle(list);
     System.out.println("Коллекция была перемешана!");
     }
     private  void update(Long id, ArrayList<HumanBeing> list) {
     for (var human : list
     ) {
     if (human.getId() == id) {
     human = new HumanBeing(id);
     System.out.println("Элемент был обновлен!");
     break;
     }
     }
     }
     private  void getInfo(File file, ArrayList<HumanBeing> list){
     try {
     PrintStream output = System.out;
     Path path = file.toPath();
     BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);
     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     output.println("Collection type: " + list.getClass().toString() + "\nInitialization data: " + format.format(new Date(attr.creationTime().toMillis())) + "\nAmount of elements: " + list.size());
     }catch (IOException e){
     System.out.println("Не удалось получить данные о коллекции из файла");
     }
     }
     **/
    ///////////////////////////////////////////////////////
}
//{name} {coordinate x} {coordinate y} {is real hero} {has toothpick} {impact speed} {weapon type} {mood} {car name} {car coolness}