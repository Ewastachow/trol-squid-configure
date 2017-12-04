package trol;

import trol.terminal_interface.TerminalController;

public class Main {
    public static void main(String[] args){
        System.out.println("Main run");
//        TerminalExecute te = new TerminalExecute();
//        te.test1();
        TerminalController terminalController = new TerminalController(new DomainList("/etc/squid/black-list.acl"));
        while (true) terminalController
                .showMenu()
                .executeAction();
    }

//    public static void main(String[] args){
//        try {
//            restartSquid();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    private void askingLoop(){
//
//
//    }
//
//    private static void restartSquid() throws IOException, InterruptedException {
//        Process proc = Runtime.getRuntime().exec("service squid restart");
//
////        proc.waitFor();
//        // Read the output
//
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//        String line = "";
//        while((line = reader.readLine()) != null) {
//            System.out.print(line + "\n");
//        }
//
//        proc.waitFor();
//
//    }


}
