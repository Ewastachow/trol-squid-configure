package trol.blocking.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TerminalExecute {

    public String executeCommand(String command) throws IOException, InterruptedException {

        Process proc = Runtime.getRuntime().exec(command);

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line;
        String output = "";
        while((line = reader.readLine()) != null) {
            output = output + line + "\n";
        }
        proc.waitFor();
        return output;
    }

}
