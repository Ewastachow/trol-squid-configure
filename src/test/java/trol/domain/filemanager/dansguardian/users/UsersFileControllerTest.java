package trol.domain.filemanager.dansguardian.users;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import trol.domain.filemanager.FilePathsTest;

import static org.junit.Assert.*;

public class UsersFileControllerTest {
    @Before
    public void setUp() throws Exception {
        FilePathsTest.before();
    }

    @After
    public void tearDown() throws Exception {
        FilePathsTest.after();
    }
    @Test
    public void saveFile() throws Exception {
        Assert.assertEquals(true,true);

    }

}