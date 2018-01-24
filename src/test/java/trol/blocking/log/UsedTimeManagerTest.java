package trol.blocking.log;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UsedTimeManagerTest {

    @Test
    public void getState() {
        UsedTimeManager man = new UsedTimeManager("resources/trol/log/testLogFile1");
        assertEquals(UsedTimeManagerState.FREE,man.getState());
    }

    @Test
    public void parseLineExistedTest() {
        UsedTimeManager testManager =
                new UsedTimeManager("src/test/resources/trol/log/testLogFile1");
        Map<String, Integer> users = new HashMap<>();
        users.put("10.10.10.245", 1);

        try {
            Method parseTestLine =
                    UsedTimeManager.
                            class.
                            getDeclaredMethod("parseLine",Map.class,String.class);
            parseTestLine.setAccessible(true);
            parseTestLine.invoke(testManager,users,"2018.1.12 13:25:08 - 10.10.10.245 http://planzajec.eaiib.agh.edu.pl/view/timetable/253?date=2018-01-08  GET 12707 15  1 200 text/html   -");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assertEquals(new Integer(2),users.get("10.10.10.245"));
    }

    @Test
    public void parseLineFindingTest() {
        UsedTimeManager testManager =
                new UsedTimeManager("src/test/resources/trol/log/testLogFile1");
        Map<String, Integer> users = new HashMap<>();
        users.put("10.10.10.245", 1);
        assertEquals(false,users.containsKey("10.10.10.246"));

        try {
            Method parseTestLine =
                    UsedTimeManager.
                            class.
                            getDeclaredMethod("parseLine",Map.class,String.class);
            parseTestLine.setAccessible(true);
            parseTestLine.invoke(testManager,users,"2018.1.12 13:25:08 - 10.10.10.246 http://planzajec.eaiib.agh.edu.pl/view/timetable/253?date=2018-01-08  GET 12707 15  1 200 text/html   -");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        assertEquals(new Integer(1),users.get("10.10.10.245"));
        assertEquals(true,users.containsKey("10.10.10.246"));
    }
}