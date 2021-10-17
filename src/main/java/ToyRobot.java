import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class ToyRobot {

    public static void main(String[] args) {
        Table table = new Table(5,5);
        try (Scanner scanner = new Scanner(System.in)){
            String command = null;
            while (!"EXIT".equals(command)){
                command = scanner.nextLine();
                if (command != null && !command.isEmpty()) {
                    table.readCommand(command);
                }
            }
        }
    }
}
