package trol.domain.log;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class LogsReaderTest {

    @Test
    public void printLogs() throws IOException {
        new LogsReader("src/test/resources/trol.log/testLogFile").printLogs();
    }
}