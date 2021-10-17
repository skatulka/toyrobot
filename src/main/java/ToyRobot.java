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
