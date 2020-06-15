package Human;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
/**
 * Объектная модель координат человека
 * Класс содержит поля координат x и y
 */
public class Coordinates implements Serializable {
    private Integer x; //Поле не может быть null
    private int y;
    /**
     * Конструктор создания координаты вручную
     *
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(Integer x, int y) {
        if (x == null) {
            this.x = 0;
        } else this.x = x;
        this.y = y;
    }
    /**
     * Конструктор создания координаты с запросом на ввод значений
     *
     * @param access параметр, отвечающий за предотвращение случайного создания объекта
     */
    public Coordinates(boolean access) {
        boolean correctSettings = true;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Введите x координату: ");
                this.x = scanner.nextInt();
                System.out.println("Введите y координату: ");
                this.y = scanner.nextInt();
                correctSettings = true;

            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage() + ". Попробуйте ещё раз.");
                correctSettings = false;
            }
        } while (!correctSettings);
    }
    public static Coordinates getRandomCoordinates(){
        Random r = new Random();
        return new Coordinates(r.nextInt(1300), r.nextInt(900));
    }

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y;
    }
}