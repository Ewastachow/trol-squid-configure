package trol.domain.squid;

import java.io.IOException;

public class LocalMainForTesting {
    public static void main(String[] args) {
        System.out.print("LamaTest");
        try {
            SquidConf testFile = new SquidConf("A:\\Clouds\\Dropbox\\Semestr-5\\iO\\Project\\trol-squid-configure\\src\\main\\resources\\trol.domain.squid\\squidTest.conf");

            System.out.print("LamaTest");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Blad odczytu pliku");
        }
        System.out.print("LamaTest");

    }
}
