package trol.domain.filemanager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FileControllerTest {

    @Before
    public void setUp() throws Exception {
        FilePathsTest.before();
    }

    @After
    public void tearDown() throws Exception {
        FilePathsTest.after();
    }

//    @Test
//    public void saveConfigurationTest1() throws Exception {
//        FileController fileController = new FileController();
//        fileController.saveConfiguration();
//        Assert.assertTrue("Checks if squid config file has been created",Files.exists(Paths.get(FilePaths.SQUID_CONFIGURE_PATH)));
//    }

}