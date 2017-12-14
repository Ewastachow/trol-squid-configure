package trol.domain.squid.util;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void createLineListFromFileNotExistFile() throws Exception {

        try {
            List<String> tokens = FileHelper.
                    createLineListFromFile("src/test/resources/trol.util/testFileToLineListNotExist");
            fail("Should throw an exception");
        } catch (Exception e) {
            //TODO: Implement
    }
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
    public void createWordsListFromLine() throws Exception {

    }

}