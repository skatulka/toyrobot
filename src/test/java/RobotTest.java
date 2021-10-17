import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.Consumer;

public class RobotTest {
    private static final String EXPECTED_INITIAL_REPORT = "0,0,null";

    @DataProvider(name = "placeTesting")
    public Object[][] placeTesting() {
        return new Object[][]{
                {0, 0, Direction.NORTH, "0,0,NORTH"},
                {3, 1, Direction.SOUTH, "3,1,SOUTH"},
                {4, 4, Direction.WEST, "4,4,WEST"},
                {2, 3, Direction.EAST, "2,3,EAST"},
                {5, 5, Direction.NORTH, "0,0,null"},
                {-1, 0, Direction.NORTH, "0,0,null"},
                {0, -1, Direction.NORTH, "0,0,null"},
                {1, 1, null, "0,0,null"}
        };
    };

    @Test(dataProvider = "placeTesting")
    public static void testPlace(int x, int y, Direction direction, String expectedReport){
        Robot robot = new Robot();
        Table table = new Table(5, 5, robot);

        // Verify that these do not throw errors before robot is placed
        robot.move();
        robot.leftTurn();
        robot.rightTurn();

        // Place the robot on the table and verify report updates values
        robot.place(table, x, y, direction);
        String actualReport = robot.report();
        Assert.assertEquals(actualReport, expectedReport);
    }

    @DataProvider(name = "movementTesting")
    public Object[][] movementTesting() {
        return new Object[][]{
                {0, 0, Direction.NORTH, "0,1,NORTH"},
                {0, 0, Direction.SOUTH, "0,0,SOUTH"},
                {0, 0, Direction.WEST, "0,0,WEST"},
                {0, 0, Direction.EAST, "1,0,EAST"},
                {4, 4, Direction.SOUTH, "4,3,SOUTH"},
                {4, 4, Direction.EAST, "4,4,EAST"},
                {4, 4, Direction.WEST, "3,4,WEST"},
                {4, 4, Direction.NORTH, "4,4,NORTH"},
                {4, 4, Direction.SOUTH, "4,3,SOUTH"},
                {1, 3, Direction.WEST, "0,3,WEST"}
        };
    };

    @Test(dataProvider = "movementTesting")
    public static void testMovement(int x, int y, Direction direction, String expectedReport){
        Robot robot = new Robot();
        Table table = new Table(5, 5, robot);

        // Place the robot on the table, move, and verify report
        robot.place(table, x, y, direction);
        robot.move();
        String actualReport = robot.report();
        Assert.assertEquals(actualReport, expectedReport);
    }

    @DataProvider(name = "turnTesting")
    public Object[][] turnTesting() {
        return new Object[][]{
                {Direction.NORTH, robotDo(r->r.leftTurn()), Direction.WEST},
                {Direction.WEST, robotDo(r->r.leftTurn()), Direction.SOUTH},
                {Direction.SOUTH, robotDo(r->r.leftTurn()), Direction.EAST},
                {Direction.EAST, robotDo(r->r.leftTurn()), Direction.NORTH},
                {Direction.NORTH, robotDo(r->r.rightTurn()), Direction.EAST},
                {Direction.EAST, robotDo(r->r.rightTurn()), Direction.SOUTH},
                {Direction.SOUTH, robotDo(r->r.rightTurn()), Direction.WEST},
                {Direction.WEST, robotDo(r->r.rightTurn()), Direction.NORTH},
                {Direction.NORTH, robotDo(r->{r.rightTurn(); r.rightTurn();}), Direction.SOUTH},
                {Direction.SOUTH, robotDo(r->{r.rightTurn(); r.rightTurn();}), Direction.NORTH},
                {Direction.EAST, robotDo(r->{r.rightTurn(); r.rightTurn();}), Direction.WEST},
                {Direction.WEST, robotDo(r->{r.rightTurn(); r.rightTurn();}), Direction.EAST},
                {Direction.NORTH, robotDo(r->{r.leftTurn(); r.rightTurn();}), Direction.NORTH},
                {Direction.SOUTH, robotDo(r->{r.leftTurn(); r.rightTurn();}), Direction.SOUTH},
                {Direction.EAST, robotDo(r->{r.leftTurn(); r.rightTurn();}), Direction.EAST},
                {Direction.WEST, robotDo(r->{r.leftTurn(); r.rightTurn();}), Direction.WEST},
        };
    };

    @Test(dataProvider = "turnTesting")
    public static void testTurning(Direction direction, Consumer<Robot> operation, Direction expected){
        Robot robot = new Robot();
        Table table = new Table(5, 5, robot);

        // Place the robot on the table, move, and verify report
        robot.place(table, 0, 0, direction);
        operation.accept(robot);
        Direction actual = robot.getFacing();
        Assert.assertEquals(actual, expected);
    }

    private Consumer<Robot> robotDo(Consumer<Robot> operation){
        return operation;
    }
}
