import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {

    private final int width;
    private final int height;
    private final Robot robot;

    private final Pattern placePattern = Pattern.compile("PLACE\\s+([0-9]+)\\s*,\\s*([0-9]+)\\s*,\\s*((?:NORTH|SOUTH|EAST|WEST))");
    private final Pattern movePattern = Pattern.compile("MOVE");
    private final Pattern leftTurnPattern = Pattern.compile("LEFT");
    private final Pattern rightTurnPattern = Pattern.compile("RIGHT");
    private final Pattern reportPattern = Pattern.compile("REPORT");

    public Table(final int width, final int height){
        this(width, height, new Robot());
    }

    public Table(final int width, final int height, Robot robot) {
        this.width = width;
        this.height = height;
        this.robot = robot;
    }

    public boolean isValidPosition(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < width;
    }

    public void readCommand(final String commandLine){
        if (commandLine == null || commandLine.isEmpty()){
            return;
        }
        final String ucCommand = commandLine.trim().toUpperCase();
        if (ucCommand.isEmpty()){
            return;
        }
        if (movePattern.matcher(ucCommand).matches()){
            robot.move();
            return;
        }
        if (leftTurnPattern.matcher(ucCommand).matches()){
            robot.leftTurn();
            return;
        }
        if (rightTurnPattern.matcher(ucCommand).matches()){
            robot.rightTurn();
            return;
        }
        if (reportPattern.matcher(ucCommand).matches()){
            String report = robot.report();
            if (report != null && !report.isEmpty()) {
                System.out.println(report);
            }
            return;
        }
        Matcher placeMatcher = placePattern.matcher(ucCommand);
        if (placeMatcher.matches()) {
            int x = Integer.parseInt(placeMatcher.group(1));
            int y = Integer.parseInt(placeMatcher.group(2));
            Direction direction = Direction.valueOf(placeMatcher.group(3));
            robot.place(this, x, y, direction);
            return;
        }
    }
}
