import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DirectionTest {

    @DataProvider(name = "leftTurnTesting")
    public Object[][] leftTurnTesting() {
        return new Object[][]{
            {Direction.NORTH, Direction.WEST},
            {Direction.WEST, Direction.SOUTH},
            {Direction.SOUTH, Direction.EAST},
            {Direction.EAST, Direction.NORTH}
        };
    };

    @Test(dataProvider = "leftTurnTesting")
    public static void testLeftTurn(Direction starting, Direction expected){
        Assert.assertNotNull(starting);
        Direction actual = starting.left();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "rightTurnTesting")
    public Object[][] rightTurnTesting() {
        return new Object[][]{
                {Direction.NORTH, Direction.EAST},
                {Direction.EAST, Direction.SOUTH},
                {Direction.SOUTH, Direction.WEST},
                {Direction.WEST, Direction.NORTH}
        };
    };

    @Test(dataProvider = "rightTurnTesting")
    public static void testRightTurn(Direction starting, Direction expected){
        Assert.assertNotNull(starting);
        Direction actual = starting.right();
        Assert.assertEquals(actual, expected);
    }

    @DataProvider(name = "moveTesting")
    public Object[][] moveTesting(){
        return new Object[][]{
                {0, 0, Direction.NORTH, 0, 1},
                {-1, 5, Direction.NORTH, -1, 6},
                {0, 0, Direction.EAST, 1, 0},
                {-3, 1, Direction.EAST, -2, 1},
                {0, 0, Direction.SOUTH, 0, -1},
                {3, 2, Direction.SOUTH, 3, 1},
                {4, -1, Direction.SOUTH, 4, -2},
                {0, 0, Direction.WEST, -1, 0},
                {5, 2, Direction.WEST, 4, 2},
                {-2, 2, Direction.WEST, -3, 2}
        };
    }

    @Test(dataProvider = "moveTesting")
    public static void moveTesting(int startX, int startY, Direction direction, int expectedX, int expectedY){
        Assert.assertNotNull(direction);
        int actualX = startX + direction.getX();
        int actualY = startY + direction.getY();
        Assert.assertEquals(actualX, expectedX, "Move along x axis was incorrect!");
        Assert.assertEquals(actualY, expectedY, "Move along y axis was incorrect!");
    }
}
