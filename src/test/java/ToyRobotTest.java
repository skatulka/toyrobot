import org.mockito.Mockito;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

public class ToyRobotTest {

    @DataProvider(name = "toyRobotTesting")
    public Object[][] toyRobotTesting() {
        return new Object[][]{
                {"0,0,NORTH",""}
        };
    };

    @Test(dataProvider = "toyRobotTesting")
    public static void testToyRobot(String expectedFinalReport, String[] commands) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        ToyRobot toyRobot = new ToyRobot();


        Robot robot = Mockito.mock(Robot.class);
        Table table = new Table(5, 5, robot);
    }
}
