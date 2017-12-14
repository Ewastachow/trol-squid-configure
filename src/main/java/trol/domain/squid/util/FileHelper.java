package trol.domain.squid.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    public static List<String> createLineListFromFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        createPathIfNotExist(filePath);
        return Files.readAllLines(filePath, Charset.forName("UTF-8"));
    }

    public static List<String> createWordsListFromLine(String line){
        //TODO: Implement -> Przyjmóje Stringa z linijką z squid.conf, zwraca listę wyrazów w tym stringu
        return null;
    }

    private static void createPathIfNotExist(Path path) throws IOException {
        if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS))
            Files.createFile(path);
    }
}
