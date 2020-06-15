package Controls;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс, отвечающий за выбор файла
 * Содержит проверку аргумента командной строки, поиск файла и проверку доступа к файлу
 */
public abstract class FileSelector {
    /**
     * Поток вывода информации
     */
    static PrintStream output = System.out;

    /**
     * Проверка доступа к файлу
     * @param file файл
     * @return доступность
     */
    public static boolean checkAccess(File file) {
        boolean success = true;

        if (!file.exists() || file.isDirectory()) {

            output.println("Не удалось найти файл \"" + file.getName() + "\"");
            success = false;

        } else {
            if (!file.canRead()) {
                output.println("Не удалось открыть файл \"" + file.getName() + "\" для чтения");
                success = false;
            }

            if (!file.canWrite()) {
                output.println("Не удалось открыть файл \"" + file.getName() + "\" для записи");
                success = false;
            }
        }

        return success;
    }


}


