package Human;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Объектная модель машины
 * Класс содержит поля названия name и крутости cool
 */
public class Car implements Serializable, Comparable {
    private String name; //Поле не может быть null
    private Boolean cool; //Поле может быть null
    private static ArrayList<String> carNames;
    /**
     * Конструктор создания координаты вручную
     *
     * @param name название
     * @param cool крутость
     */
    public Car(String name, Boolean cool) {
        if (name == null) {
            this.name = "unknown";
        } else {
            this.name = name;
        }
        this.cool = cool;
    }
    /**
     * Конструктор создания координаты с запросом на ввод значений
     *
     * @param access параметр, отвечающий за предотвращение случайного создания объекта
     */
    public Car(boolean access) {
        Scanner scanner = new Scanner(System.in);
        boolean correctSettings = true;
        do {
            try {
                System.out.println("Введите название машины: ");
                this.name = scanner.nextLine();
                System.out.println("Введите хороша ли она (true или false): ");
                this.cool = scanner.nextBoolean();
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage() + ". Попробуйте ещё раз.");
                correctSettings = false;
            }
        } while (!correctSettings);
    }
public static Car getRandomCar(){
        if (carNames==null) {
            try (BufferedReader bf = new BufferedReader(new FileReader("car_names"))) {
                carNames = new ArrayList<>();
                carNames.addAll(bf.lines().collect(Collectors.toList()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Random r = new Random();
    return new Car(carNames.get(r.nextInt(carNames.size())), r.nextBoolean());
}
    @Override
    public String toString() {
        return "название=" + name + ", крутота=" + cool;
    }
    /**
     * Возвращает название машины
     *
     * @return название машины
     */
    public String getName() {
        return name;
    }


    @Override
    public int compareTo(Object car) {
        if (this.name.compareTo(((Car)car).name)>0) {
            return 1;
        } else if (this.name.compareTo(((Car)car).name)==0) {
            return 0;
        } else return -1;
    }
}