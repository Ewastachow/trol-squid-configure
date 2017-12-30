package trol.domain.log;

public class BlockTimeManager {
    String timesPath;

    BlockTimeManager(String timesPath) {
        this.timesPath = timesPath;
    }

    public boolean addTime(int seconds, String user) {
        //TODO: ma dopisać do pliku i jeżeli przekroczy użytkownikowi jego blokadę czasową,
        //TODO: to myk myk zwraca true i w ten sposób przekazuje że użytkownika trzeba zablokować
        return false;
    }
}
