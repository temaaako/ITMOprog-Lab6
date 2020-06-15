package Controls;

import Objects.Command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public static Command[] readFile(File file) {
        ArrayList<Command> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Command cmd = CommandListener.getCommand(line);
                if (cmd!=null) {
                    commands.add(cmd);
                }
                else {
                    System.out.println("Скрипт" + line + "в файле некорректен");
                    return null;
                }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return commands.toArray(new Command[0]);


    }
}
