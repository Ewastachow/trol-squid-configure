package trol.domain.log;

import java.util.List;

public class BlockTimeManager {
    private String timesPath;
    private List<String> users;

    BlockTimeManager(String timesPath, List<String> users) {
        this.timesPath = timesPath;
        this.users = users;
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
