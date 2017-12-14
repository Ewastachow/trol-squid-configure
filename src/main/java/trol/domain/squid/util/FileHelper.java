package trol.domain.squid.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(line.split("[\r \t]+")));
        return result;
    }

    /**
     *
     * @param words
     * @return "" if words is null or words length is equal to zero
     */
    public static String createStringFromWordsList(List<String> words){
        if(words == null || words.size() == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < words.size()-1; ++i) {
            builder.append(words.get(i));
            builder.append(" ");
        }
        builder.append(words.get(words.size()-1));
        return builder.toString();
    }
}
