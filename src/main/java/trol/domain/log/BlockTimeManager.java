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

    public void clearWastedTime() {
        //TODO: Wywoływana domyślnie o 00:00 chodzi o wyzerowanie wasted timeu w pliku
    }
}
