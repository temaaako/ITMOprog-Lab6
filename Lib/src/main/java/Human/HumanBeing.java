package Human;


import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Объектная модель человека.
 * Класс содержит поля состояния человека
 */

public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private Boolean hasToothpick; //Поле не может быть null
    private Double impactSpeed; //Максимальное значение поля: 913, Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле не может быть null
    private Car car; //Поле может быть null
private static ArrayList<String> names;

    /**
     * Конструктор для создания объектов вручную
     *
     * @param name         Имя
     * @param coordinates  Местоположение
     * @param realHero     Является ли героем
     * @param hasToothpick Наличие зубочистки
     * @param impactSpeed  Скорость соударения
     * @param weaponType   Тип оружия
     * @param mood         Настроение
     * @param car          Машина
     */

    public HumanBeing(String name, Coordinates coordinates, boolean realHero, Boolean hasToothpick, Double impactSpeed, WeaponType weaponType, Mood mood, Car car) {

        if (name == null || coordinates == null || hasToothpick == null || mood == null)
            throw new NullPointerException();
        if (name.equals(""))
            throw new InvalidHumanNameException("Недопустимое имя человека");
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        if (impactSpeed>913) this.impactSpeed = 913.0;
        else this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.id = 0L;
    }
    public  HumanBeing(String name, String coordinate_X, String coordinate_Y, String realHero, String hasToothpick, String impactSpeed, String weaponType, String mood, String carName, String carCoolness){
        this(name, new Coordinates(Integer.parseInt(coordinate_X), Integer.parseInt(coordinate_Y)), Boolean.parseBoolean(realHero), Boolean.parseBoolean(hasToothpick),
                Double.parseDouble(impactSpeed), WeaponType.valueOf(weaponType), Mood.valueOf(mood), new Car(carName, Boolean.parseBoolean(carCoolness)));
    }


    /**
     * Конструктор для обновления объекта
     *
     * @param id id объекта для его обновления
     */
    public HumanBeing(Long id) {
        boolean correctSettings = true;
        this.id = id;
        do {
            try {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Name: ");
                this.name = scanner.nextLine();
                if (name.equals(""))
                    throw new InvalidHumanNameException("Недопустимое имя человека");
                System.out.println("Coordinates: ");
                this.coordinates = new Coordinates(true);
                this.creationDate = LocalDateTime.now();
                System.out.println("Real Hero (true/false): ");
                this.realHero = scanner.nextBoolean();
                System.out.println("Has Toothpick (true/false): ");
                this.hasToothpick = scanner.nextBoolean();
                System.out.println("Impact speed: ");
                this.impactSpeed = scanner.nextDouble();
                if (this.impactSpeed>913.0) this.impactSpeed = 913.0;
                System.out.println("Weapon type (HAMMER, SHOTGUN, RIFLE, BAT): ");
                this.weaponType = WeaponType.valueOf(scanner.next().toUpperCase());
                System.out.println("Mood ( SADNESS, LONGING, CALM): ");
                this.mood = Mood.valueOf(scanner.next().toUpperCase());
                this.car = new Car(true);

                if (name == null || coordinates == null || hasToothpick == null || mood == null)
                    throw new NullPointerException();

                correctSettings = true;
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage() + ". Попробуйте ещё раз.");
                correctSettings = false;
            }
        } while (!correctSettings);
    }

    /**
     * Возвращает тип оружия
     *
     * @return тип оружия
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Возвращает id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Возвращает машину
     *
     * @return объект машины
     */
    public Car getCar() {
        return car;
    }

    public void update(HumanBeing human){
        this.name = human.name;
        this.impactSpeed = human.impactSpeed;
        this.coordinates = human.coordinates;
        this.car = human.car;
        this.weaponType = human.weaponType;
        this.mood = human.mood;
        this.hasToothpick = human.hasToothpick;
        this.realHero = human.realHero;
    }

    @Override
    /**
     * Сравнение объектов на основе скорости соударения
     *
     * @return результат сравнения
     */
    public int compareTo(HumanBeing humanBeing) {
        if (this.impactSpeed > humanBeing.impactSpeed) {
            return 1;
        } else if (this.impactSpeed == humanBeing.impactSpeed) {
            return 0;
        } else return -1;
    }

    @Override
    public String toString() {
        return "Человек: id: " + this.id + ", имя: " + this.name + ", координаты: " + this.coordinates.toString() + ", дата записи: "
                + this.creationDate + ", существование: " + this.realHero + ", наличие зубочистки: " + this.hasToothpick +
                ", скорость: " + this.impactSpeed + ", тип оружия: " + this.weaponType + ", настроение: " + this.mood + ", машина: " +
                this.car.toString();
    }
    public HumanBeing addIdAndDate(long count){
        this.creationDate = LocalDateTime.now();
        this.id = count;
        return this;
    }
    public static HumanBeing getRandomHuman(){
        if (names==null){
            try (BufferedReader bf = new BufferedReader(new FileReader("names"))){
                names=new ArrayList<>();
                names.addAll(bf.lines().collect(Collectors.toList()));
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        Random r = new Random();
        return new HumanBeing(names.get(r.nextInt(names.size())), Coordinates.getRandomCoordinates(),
                r.nextBoolean(),r.nextBoolean(), 913 * r.nextDouble(), WeaponType.randomWeapon(),Mood.randomMood(), Car.getRandomCar());
    }
}