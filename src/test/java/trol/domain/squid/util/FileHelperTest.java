package trol.domain.squid.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        Assert.assertEquals("acl SSL_ports port 443",tokens.get(0));
        Assert.assertEquals("acl Safe_ports port 777\t\t# multiling http",tokens.get(10));
    }

    @Test
    public void createWordsListFromLine() throws Exception {

    }

}