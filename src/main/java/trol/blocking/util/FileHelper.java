package trol.blocking.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class FileHelper {

    public static boolean fileGreaterOrEqualThan(String path, int N) throws IOException {
        Path filePath = Paths.get(path);
        List<String> result = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"));
        String line = "";
        int counter = 0;

        while ((line = reader.readLine()) != null)
            ++counter;


        return counter>=N;
    }

    public static List<String> readLastLinesSince(String path, int N) throws IOException {
        Path filePath = Paths.get(path);
        List<String> result = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"));
        String line = "";
        int counter = 0;

        while ((line = reader.readLine()) != null) {
            if (counter >= N)
                result.add(line);
            ++counter;
        }

        return result;
    }

    public static List<String> createLineListFromFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllLines(filePath, Charset.forName("UTF-8"));
    }

    public static List<String> createLineListFromPath(Path path) throws IOException {
        return Files.readAllLines(path, Charset.forName("UTF-8"));
    }

    public static List<String> createLineListFromFileWithoutBlank(String path) throws IOException {
        Path filePath = Paths.get(path);
        List<String> result = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, Charset.forName("UTF-8"));
        String line = "";

        while ((line = reader.readLine()) != null) {
            if (line.trim().length() > 0)
                result.add(line);
        }

        return result;
    }

    public static List<String> createWordsListFromLine(String line) {
        List<String> result = new ArrayList<>();
        result.addAll(Arrays.asList(line.split("[\r \t]+")));
        return result;
    }

    /**
     * @param words
     * @return "" if words is null or words length is equal to zero
     */
    public static String createStringFromWordsList(List<String> words) {
        if (words == null || words.size() == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.size() - 1; ++i) {
            builder.append(words.get(i));
            builder.append(" ");
        }
        builder.append(words.get(words.size() - 1));
        return builder.toString();
    }

    /**
     * NullPointerException raised in case of null parameter
     */
    public static String removeQuotationMarks(String string) {
        return string.replace("\"", "");
    }

    /**
     *
     * @param path to file create if necessary
     * @param string with line breaks already
     */
    public static void saveStringAsFile(Path path, String string) throws IOException {
        List<String> lines = Arrays.asList(string.split("\\r\\n|[\\r\\n]"));
        Files.write(path, lines, Charset.forName("UTF-8"));
    }

    public static void saveStringListAsFile(Path path, List<String> stringList) throws IOException {
        Files.write(path, stringList, Charset.forName("UTF-8"));
    }

    /**
     * Accept include lines in format .Include<path>
     * @param includeLine
     * @return path from include if includeLine had correct format, otherwise null
     */
    public static String getPathStringFromIncludeLine(String includeLine){
        String rex = "^.Include<.+>$";
        if(includeLine.matches(rex))
            return includeLine.substring(9,includeLine.length()-1);
        else
            return null;
    }

    public static String verifyDomain(String domain){
        Pattern pattern = Pattern.compile("^\\.[a-zA-Z0-9\\.]+[a-zA-Z0-9]$");
        if(pattern.matcher(domain).matches())
            return domain;
        else
            return "."+domain;
    }

    public static String removeWhiteChars(String string){
        return string.replaceAll("\\s+","");
    }

    public static String dansguardianTimeControlLine(LocalTime beginTime, LocalTime endTime){
        StringBuilder sb = new StringBuilder();
        sb.append("#time: ");
        sb.append(beginTime.getHour());
        sb.append(" ");
        sb.append(beginTime.getMinute());
        sb.append(" ");
        sb.append(endTime.getHour());
        sb.append(" ");
        sb.append(endTime.getMinute());
        sb.append(" 0123456");
        return sb.toString();
    }
}
