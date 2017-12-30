package trol.domain.log;

import trol.domain.squid.util.FileHelper;
import trol.domain.terminal.TerminalExecute;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogsReader {

    private BlockTimeManager timeManager;
    private TerminalExecute term;
    private String accessLogPath;
    private int lastLine;
    private List<String> users;
    private Set<String> foundUsers = new HashSet<>();
    private Set<InetAddress> usersReadyToBlock = new HashSet<>();

    LogsReader(String accessLogPath, List<Inet4Address> users, TerminalExecute term) {
        this(accessLogPath,users, term, "/etc/squid/blockTimes");
    }

    LogsReader(String accessLogPath, List<Inet4Address> users, TerminalExecute term, String blockTimesPath) {
        this.term = term;
        this.accessLogPath = accessLogPath;
        for(Inet4Address l : users) {
            this.users.add(l.getHostAddress());
        }
        timeManager = new BlockTimeManager(blockTimesPath,this.users);
        lastLine = 0;
    }

    /**
     * Should be called in equal intervals by other thread
     * @return List of Inet4Addresses of users that are ready to block or empty list.
     * @throws UnknownHostException When users list is invalid.
     * @throws IOException problems with logs or block times file
     */
    public List<InetAddress> checkUsersLogs() throws IOException, InterruptedException {
        ArrayList<InetAddress> result = new ArrayList<>();

        if(timeManager.nextDay()) {
            updateNextDay();
        }
        else {
            updateUsersReadyToBlock();
        }

        for(InetAddress u : usersReadyToBlock)
            result.add(u);

        return result;
    }

    private void updateNextDay() throws IOException, InterruptedException {
        timeManager.clearWastedTime();
        usersReadyToBlock.clear();
        lastLine = 0;
        term.executeCommand("squid -k rotate");
    }

    private void updateUsersReadyToBlock() throws IOException {

        List<String> newLines = FileHelper.readLastLinesSince(accessLogPath, lastLine);
        lastLine += newLines.size();

        for (String l : newLines)
            parseLine(l);

        for (String user : foundUsers) {
            if (timeManager.addTime(60, user))
                usersReadyToBlock.add(Inet4Address.getByName(user));
        }

    }

    private void parseLine(String line) {
        String user = line.replaceAll("[\\[\\]]]","").split("[\t ]+")[0];
        foundUsers.add(user);
    }

    public void printLogs() throws IOException {
        for(String l : FileHelper.createLineListFromFileWithoutBlank(accessLogPath))
            System.out.println(l);
    }

}
