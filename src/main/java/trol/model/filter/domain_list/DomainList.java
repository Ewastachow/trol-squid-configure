package trol.model.filter.domain_list;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainList implements Cloneable{

    private List<String> domainList;
    final private String path;

    public DomainList(List<String> readyList, String path) {
        this.domainList = readyList;
        this.path = path;
        toFile();
    }

    public DomainList(String path) {
        this.path = path;
        fromFile();
    }

    public List<String> getDomainList() {
        // fromFile();
        return domainList;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        domainList.forEach((str) -> {
            builder.append(str);
            builder.append("\r\n");});

        return builder.toString();
    }

    public DomainList fromString(String string) {
        String lines[] = string.split("\\r?\\n");

        for(String s : lines)
            domainList.add(s);

        return this;
    }

    public boolean toFile(){
        Path file = Paths.get(path);
        try {
            Files.write(file, domainList, Charset.forName("UTF-8"), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean fromFile(){
        Path file = Paths.get(path);
        try {
            domainList = Files.readAllLines(file, Charset.forName("UTF-8"));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean addDomain(String domain) throws IOException {
        boolean valid = isCorrectDomain(domain);
        if(valid)
            domainList.add(domain);
        boolean success = toFile();
        return valid && success;
    }

    public boolean removeDomain(String domain) throws IOException {
        boolean valid = isCorrectDomain(domain) && domainList.contains(domain);
        if(valid)
            domainList.remove(domain);
        boolean success = toFile();
        return valid && success;
    }

    /* Przciaznie dla clonowania */
    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        List<String> domainsCopy = new ArrayList<>();

        for(String s : domainList)
            domainsCopy.add(s);

        return new DomainList(domainsCopy,path);
    }

    private boolean isCorrectDomain(String string) {
        String patternString = "^\\.[a-zA-Z0-9\\.]+[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(string);

        return matcher.matches();
    }

//    //Bo nie mamy jeszcze katalogu na testy xd
//    public static void main(String[] args) {
//        String testFilePath = "plik.txt";
//        DomainList d = new DomainList(testFilePath);
//        d.addDomain("dupmLings");
//        d.addDomain("cyceRon");
//        d.addDomain(".correct.domain.cyceRon");
//        d.addDomain(".123.143.532.134");
//
//        DomainList d2 = null;
//        try {
//            d2 = (DomainList) d.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        d.removeDomain(".123.143.532.134");
//
//        System.out.println(d2.toString());
//        System.out.println(d.toString());
//
//        File f = new File(testFilePath);
//        if(f.exists() && !f.isDirectory()) {
//            try {
//                d.toFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}

