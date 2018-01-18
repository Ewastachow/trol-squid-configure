package trol.domain.filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class FilePathsTest {

    public static void before(){
        FilePaths.DANSGUARDIAN_LISTS_PATH = "src/test/resources/trol/domain/filemanager/test/";
        FilePaths.SQUID_CONFIGURE_PATH = "src/test/resources/trol/domain/filemanager/test/squid.conf";
        try {
            Files.createDirectories(Paths.get("src/test/resources/trol/domain/filemanager/test"));
            Files.createDirectory(Paths.get(FilePaths.DANSGUARDIAN_LISTS_PATH+"phraselists"));
            Files.createDirectory(Paths.get(FilePaths.DANSGUARDIAN_LISTS_PATH+"blacklists"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void after(){
        try {
            Files.walk(Paths.get("src/test/resources/trol/domain/filemanager/test"), FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .peek(System.out::println)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            Files.delete(Paths.get("src/test/resources/trol/domain/filemanager/test"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        FilePaths.DANSGUARDIAN_LISTS_PATH = "/etc/dansguardian/lists/";
        FilePaths.SQUID_CONFIGURE_PATH = "/etc/squid/squid.conf";
    }
}
