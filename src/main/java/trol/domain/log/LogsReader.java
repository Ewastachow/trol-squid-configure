package trol.domain.log;

import trol.domain.squid.util.FileHelper;

import java.io.IOException;

public class LogsReader {

    BlockTimeManager timeManager;
    String accessLogPath;
    int lastLine;

    LogsReader(String accessLogPath) {
        this.accessLogPath = accessLogPath;
        timeManager = new BlockTimeManager("/etc/squid/blockTimes");
        lastLine = 0;
    }

    LogsReader(String accessLogPath, String blockTimesPath) {
        this.accessLogPath = accessLogPath;
        timeManager = new BlockTimeManager(blockTimesPath);
        lastLine = 0;
    }

    public void printLogs() throws IOException {
        for(String l : FileHelper.createLineListFromFileWithoutBlank(accessLogPath))
            System.out.println(l);
    }

    public void updateWastedTime() {
        //TODO: Ma działać tak, zczytujemy nową porcję z logów (po to ten lastLine) i jeżeli
        //znajdziemy tam użytkownika danego, to dodamy do jego wasted time granulację
        // poprzez timeManager
    }

    public void reset() {
        lastLine = 0;
    }
}
