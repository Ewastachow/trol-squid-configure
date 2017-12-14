package trol.domain.squid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static List<String> createLineListFromFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllLines(filePath, Charset.forName("UTF-8"));
    }

    public static List<String> createLineListFromFileWithoutBlank(String path) throws IOException {
        Path filePath = Paths.get(path);
        List<String> result = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"));
        String line = "";

        while((line = reader.readLine()) != null) {
            if(line.trim().length() > 0)
                result.add(line);
        }

        return result;
    }

    public static List<String> createWordsListFromLine(String line){
        //TODO: Implement -> Przyjmóje Stringa z linijką z squid.conf, zwraca listę wyrazów w tym stringu
        return null;
    }
}
