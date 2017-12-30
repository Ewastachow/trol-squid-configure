package trol.domain.squid.util;


import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Szymon on 14.12.2017.
 */
public class FileHelperTest {

    @Test
    public void createLineListFromFile() throws Exception {
        List<String> tokens = FileHelper.createLineListFromFile("src/test/resources/trol.util/testFileToLineList");

        Assert.assertEquals(15,tokens.size());
        Assert.assertEquals("acl SSL_ports port 443",tokens.get(0));
        Assert.assertEquals("http_port 3128",tokens.get(7));
        Assert.assertEquals("refresh_pattern .\t\t0\t20%\t4320",tokens.get(14));
    }

    @Test (expected = IOException.class)
    public void createLineListFromFileNotExistFile() throws Exception {
        List<String> tokens = FileHelper.
                createLineListFromFile("src/test/resources/trol.util/testFileToLineListNotExist");
    }

    @Test
    public void createLineListFromFileWithoutBlank() throws Exception {
        List<String> tokens = FileHelper.createLineListFromFileWithoutBlank("src/test/resources/trol.util/testFileToLineList");

        Assert.assertEquals(13,tokens.size());
        Assert.assertEquals("acl SSL_ports port 443",tokens.get(0));
        Assert.assertEquals("http_port 3128",tokens.get(6));
        Assert.assertEquals("refresh_pattern .\t\t0\t20%\t4320",tokens.get(12));
    }

    @Test
    public void createWordsListFromLine() {
        List<String> test1 = FileHelper.createWordsListFromLine("acl Safe_ports port 80\t\t# http");
        Assert.assertEquals(6,test1.size());
        Assert.assertEquals("acl",test1.get(0));
        Assert.assertEquals("Safe_ports",test1.get(1));
        Assert.assertEquals("port",test1.get(2));
        Assert.assertEquals("80",test1.get(3));
        Assert.assertEquals("#",test1.get(4));
        Assert.assertEquals("http",test1.get(5));
    }

    @Test
    public void createWordsListFromWhiteSpaceLine() {
        List<String> test1 = FileHelper.createWordsListFromLine("  \t\t  \t \r");
        Assert.assertEquals(true,test1.isEmpty());
    }

    @Test
    public void createStringFromWordsList() {
        List<String> testList = new ArrayList<String>();
        testList.add("acl");
        testList.add("Safe_ports");
        testList.add("port");
        testList.add("80");
        testList.add("#");
        testList.add("http");
        String result = FileHelper.createStringFromWordsList(testList);
        Assert.assertEquals("acl Safe_ports port 80 # http",result);
    }

    @Test
    public void createStringFromWordsListEmptyList() {
        List<String> testList = new ArrayList<String>();
        Assert.assertTrue(testList.isEmpty());
        String result = FileHelper.createStringFromWordsList(testList);
        Assert.assertEquals("",result);
    }

    @Test
    public void createStringFromNull() {
        String result = FileHelper.createStringFromWordsList(null);
        Assert.assertEquals("",result);
    }

    @Test
    public void removeQuotationMarks() {
        String path = "\"some/path/file\"";
        Assert.assertEquals("some/path/file",FileHelper.removeQuotationMarks(path));
    }

    @Test
    public void removeQuotationMarksEmptyString() {
        String path = "";
        Assert.assertEquals("",FileHelper.removeQuotationMarks(path));
    }

    @Test (expected = NullPointerException.class)
    public void removeQuotationMarksForNull() {
        FileHelper.removeQuotationMarks(null);
    }

    @Test
    public void saveStringAsFileCreation() throws IOException {
        Path p = Paths.get("testsaveStringAsFile.txt");
        FileHelper.saveStringAsFile(p,"String\nto\ntest");
        List<String> l = Files.readAllLines(p);
        Assert.assertEquals(3,l.size());
        Assert.assertEquals("String",l.get(0));
        Assert.assertEquals("to",l.get(1));
        Assert.assertEquals("test",l.get(2));
        Files.delete(p);
    }

    @Test
    public void saveStringAsFileTruncate() throws IOException {
        Path p = Paths.get("testsaveStringAsFile.txt");
        List<String> l = Arrays.asList("Should","Not","Exist");
        Files.write(p,l);
        l = Files.readAllLines(p);
        Assert.assertEquals(3,l.size());
        Assert.assertEquals("Should",l.get(0));
        Assert.assertEquals("Not",l.get(1));
        Assert.assertEquals("Exist",l.get(2));

        FileHelper.saveStringAsFile(p,"String\nto\ntest");
        l = Files.readAllLines(p);

        Assert.assertEquals(3,l.size());
        Assert.assertEquals("String",l.get(0));
        Assert.assertEquals("to",l.get(1));
        Assert.assertEquals("test",l.get(2));
        Files.delete(p);
    }

    @Test
    public void saveStringListAsFileCreation() throws IOException {
        Path p = Paths.get("testsaveStringAsFile.txt");
        List<String> strings = Arrays.asList("String","to","test");
        FileHelper.saveStringListAsFile(p,strings);
        List<String> l = Files.readAllLines(p);
        Assert.assertEquals(3,l.size());
        Assert.assertEquals("String",l.get(0));
        Assert.assertEquals("to",l.get(1));
        Assert.assertEquals("test",l.get(2));
        Files.delete(p);
    }

    @Test
    public void saveStringListAsFileTruncate() throws IOException {
        Path p = Paths.get("testsaveStringAsFile.txt");
        List<String> l = Arrays.asList("Should","Not","Exist");
        Files.write(p,l);
        l = Files.readAllLines(p);
        Assert.assertEquals(3,l.size());
        Assert.assertEquals("Should",l.get(0));
        Assert.assertEquals("Not",l.get(1));
        Assert.assertEquals("Exist",l.get(2));

        List<String> strings = Arrays.asList("String","to","test");
        FileHelper.saveStringListAsFile(p,strings);
        l = Files.readAllLines(p);

        Assert.assertEquals(3,l.size());
        Assert.assertEquals("String",l.get(0));
        Assert.assertEquals("to",l.get(1));
        Assert.assertEquals("test",l.get(2));
        Files.delete(p);

    }

    @Test
    public void getPathStringFromIncludeLineCorrect() {
        Assert.assertEquals("/etc/some/file",
                FileHelper.getPathStringFromIncludeLine(".Include</etc/some/file>"));
    }

    @Test
    public void getPathStringFromIncludeLineIncorrectBegin() {
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine("Include</etc/some/file>"));
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine(".include</etc/some/file>"));
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine(".Includ</etc/some/file>"));
    }

    @Test
    public void getPathStringFromIncludeLineIncorrectBrackets() {
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine(".Include>/etc/some/file<"));
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine(".Include/etc/some/file>"));
        Assert.assertEquals(null,
                FileHelper.getPathStringFromIncludeLine(".Include</etc/some/file"));
    }

    @Test
    public void readLastNLines() {
        //TODO: implement
    }
}