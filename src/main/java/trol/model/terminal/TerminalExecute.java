package trol.model.terminal;

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

    public void test1(){
        //TODO: To remove
        String output = "Lama3";
        try {
            output = executeCommand("service squid restart");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lama1");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Lama2");
        }
        System.out.print(output);
    }


}
