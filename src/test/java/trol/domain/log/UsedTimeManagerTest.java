package trol.domain.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import trol.dao.users.UserDAO;
import trol.domain.filemanager.FileController;
import trol.domain.terminal.TerminalExecute;

import java.io.IOException;

import static org.junit.Assert.*;

public class UsedTimeManagerTest {

    @Test
    public void getState() {
        UsedTimeManager man = new UsedTimeManager("resources/trol.log/testLogFile1",
                new TerminalExecute());
        assertEquals(UsedTimeManagerState.FREE,man.getState());
    }
}