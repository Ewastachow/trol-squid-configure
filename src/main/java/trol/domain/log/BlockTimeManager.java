package trol.domain.log;

import java.time.LocalTime;
import java.util.List;

public class BlockTimeManager {
    private String timesPath;
    private List<String> users;
    private LocalTime lastUpdateTimestamp;

    BlockTimeManager(String timesPath, List<String> users) {
        this.timesPath = timesPath;
        this.users = users;
        lastUpdateTimestamp =  LocalTime.now();
    }

    /**
     * Check if last update was in previous day or not
     * @return true if now returns date before lastUpdateTimestamp
     */
    public boolean nextDay() {
        return lastUpdateTimestamp.isAfter(LocalTime.now());
    }


    public boolean addTime(int seconds, String user) {
        //TODO: ma dopisać do pliku i jeżeli przekroczy użytkownikowi jego blokadę czasową,
        //TODO: to myk myk zwraca true i w ten sposób przekazuje że użytkownika trzeba zablokować
        return false;
    }


    public void clearWastedTime() {
        //TODO: Wywoływana domyślnie o 00:00 chodzi o wyzerowanie wasted timeu w pliku
    }
}
