import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class TableTest {

    @DataProvider(name = "validPositionTesting")
    public Object[][] validPositionTesting() {
        return new Object[][]{
                {5, 5, 0, 0, true},
                {5, 5, 4, 4, true},
                {5, 5, 5, 5, false},
                {0, 0, 0, 0, false},
                {10, 10, 0, 9, true},
                {10, 10, 0, 10, false},
                {10, 10, -1, 0, false},
                {10, 10, 0, -1, false},
                {10, 10, -1, -1, false}
        };
    };

    @Test(dataProvider = "validPositionTesting")
    public static void testIsValidPosition(int width, int height, int x, int y, boolean expected){
        final Table table = new Table(width, height);
        boolean actual = table.isValidPosition(x, y);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "readPlaceCommandTesting")
    public Object[][] readPlaceCommandTesting() {
        return new Object[][]{
                {"PLACE 0, 0, NORTH", 0, 0, Direction.NORTH},
                {"PLACE 5,5,SOUTH", 5, 5, Direction.SOUTH},
                {"PLACE 1, 2, east", 1, 2, Direction.EAST},
                {"place 3, 4, WEST", 3, 4, Direction.WEST},
                {"PLACE 3  ,3,   NORTH", 3, 3, Direction.NORTH}
        };
    };

    @Test(dataProvider = "readPlaceCommandTesting")
    public static void testReadPlaceCommand(String command, int x, int y, Direction facing) {
        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
        table.readCommand(command);
        Mockito.verify(robot).place(table, x, y, facing);
    }

    @DataProvider(name = "readMoveCommandTesting")
    public Object[][] readMoveCommandTesting() {
        return new Object[][]{
                {"MOVE", true},
                {"MOVE   ", true},
                {"M OVE", false},
                {"  MOVE ", true},
                {"MOV", false},
                {"MOVER", false},
                {"MOVE 1", false},
                {null, false},
                {"", false},
                {"    ", false}
        };
    };

    @Test(dataProvider = "readMoveCommandTesting")
    public static void testReadMoveCommand(String command, boolean isValid) {
        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
        table.readCommand(command);
        Mockito.verify(robot, isValid ? times(1) : never()).move();
    }

    @DataProvider(name = "readLeftTurnTesting")
    public Object[][] readLeftTurnTesting() {
        return new Object[][]{
                {"LEFT", true},
                {"LEFT   ", true},
                {"L EFT", false},
                {"  LEFT ", true},
                {"LEF", false},
                {"LEFTY", false},
                {"LEFT 1", false},
                {"RIGHT", false},
                {"LEFT MOVE", false}
        };
    };

    @Test(dataProvider = "readLeftTurnTesting")
    public static void readLeftTurnTesting(String command, boolean isValid) {
        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
        table.readCommand(command);
        Mockito.verify(robot, isValid ? times(1) : never()).leftTurn();
    }

    @DataProvider(name = "readRightTurnTesting")
    public Object[][] readRightTurnTesting() {
        return new Object[][]{
                {"RIGHT", true},
                {"RIGHT   ", true},
                {"R IGHT", false},
                {"  RIGHT ", true},
                {"RIG", false},
                {"RIGHTY", false},
                {"RIGHT 1", false},
                {"LEFT", false},
                {"RIGHT MOVE", false},
        };
    };

    @Test(dataProvider = "readRightTurnTesting")
    public static void readRightTurnTesting(String command, boolean isValid) {
        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
        table.readCommand(command);
        Mockito.verify(robot, isValid ? times(1) : never()).rightTurn();
    }

    @DataProvider(name = "readReportTesting")
    public Object[][] readReportTesting() {
        return new Object[][]{
                {"REPORT", true},
                {"REPORT   ", true},
                {"R EPORT", false},
                {"  REPORT ", true},
                {"REP", false},
                {"REPORTY", false},
                {"REPORT 1", false},
                {"MOVE", false},
                {"REPORT MOVE", false},
        };
    };

    @Test(dataProvider = "readReportTesting")
    public static void testReadReport(String command, boolean isValid) {
        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
        table.readCommand(command);
        Mockito.verify(robot, isValid ? times(1) : never()).report();
    }
}
