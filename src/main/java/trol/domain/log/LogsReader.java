package trol.domain.log;

import trol.domain.squid.util.FileHelper;
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
    private String accessLogPath;
    private int lastLine;
    private List<String> users;
    private Set<String> foundUsers = new HashSet<>();
    private Set<InetAddress> usersReadyToBlock = new HashSet<>();

    LogsReader(String accessLogPath, List<Inet4Address> users) {
        this(accessLogPath,users,"/etc/squid/blockTimes");
    }

    LogsReader(String accessLogPath, List<Inet4Address> users, String blockTimesPath) {
        this.accessLogPath = accessLogPath;
        for(Inet4Address l : users) {
            this.users.add(l.getHostAddress());
        }
        timeManager = new BlockTimeManager(blockTimesPath,this.users);
        lastLine = 0;
    }
    /**
     * Should be called in equal intervals close to one minute to obtain good performance.
     * @return List of Inet4Addresses of users that are ready to block or empty list.
     * @throws UnknownHostException When users list is invalid.
     * @throws IOException problems with logs or block times file
     */
    public List<InetAddress> checkUsersLogs() throws IOException {
        updateWastedTime();
        ArrayList<InetAddress> result = new ArrayList<>();

        for(InetAddress u : usersReadyToBlock)
            result.add(u);

        return result;
    }

    public void reset() {
        lastLine = 0;
        timeManager.clearWastedTime();
    }

    private void updateWastedTime() throws IOException {
        List<String> newLines = FileHelper.readLastLinesSince(accessLogPath,lastLine);
        lastLine += newLines.size();

        for(String l : newLines)
            parseLine(l);

        for(String user : foundUsers) {
            if (timeManager.addTime(60,user))
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
