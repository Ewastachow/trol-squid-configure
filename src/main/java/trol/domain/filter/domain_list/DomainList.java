package trol.domain.filter.domain_list;

import trol.exceptions.IncorrectDomainException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainList implements Cloneable{

    private List<String> domainList;

    final private Path path;

    public DomainList(List<String> readyList, String path) throws IOException {
        this.path = Paths.get(path);
        this.domainList = readyList;
        createPathIfNotExist();
        toFile();
    }

    public DomainList(String path) throws IOException {
        this.path = Paths.get(path);
        createPathIfNotExist();
        fromFile();
    }

    public List<String> getDomainList() throws IOException {
        fromFile();
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
        domainList.addAll(Arrays.asList(lines));
        return this;
    }

    public DomainList toFile() throws IOException {
        createPathIfNotExist();
        Files.write(path, domainList, Charset.forName("UTF-8"), StandardOpenOption.TRUNCATE_EXISTING);
        return this;
    }

    public DomainList fromFile() throws IOException {
        createPathIfNotExist();
        domainList = Files.readAllLines(path, Charset.forName("UTF-8"));
        return this;
    }

    public DomainList addDomain(String domain) throws IOException, IncorrectDomainException {
        if(!isCorrectDomain(domain))
            throw new IncorrectDomainException("Wrong domain string, cant add!");
        domainList.add(domain);
        toFile();
        return this;
    }

    public DomainList removeDomain(String domain) throws IOException, IncorrectDomainException {
        if(!isCorrectDomain(domain))
            throw new IncorrectDomainException("Wrong domain string, cant remove!");
        domainList.remove(domain);
        toFile();
        return this;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        List<String> domainsCopy = new ArrayList<>();
        domainsCopy.addAll(domainList);
        try {
            return new DomainList(domainsCopy,path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isCorrectDomain(String string) {
        String patternString = "^\\.[a-zA-Z0-9\\.]+[a-zA-Z0-9]$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    private void createPathIfNotExist() throws IOException {
        if(!Files.exists(path, LinkOption.NOFOLLOW_LINKS))
            Files.createFile(path);
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

