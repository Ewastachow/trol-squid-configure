package trol.domain.squid.util;

import org.junit.*;
import java.io.IOException;
import java.util.ArrayList;
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
}