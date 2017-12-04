package trol.terminal_interface;

import trol.filter.domain_list.DomainList;
import trol.terminal.TerminalExecute;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TerminalController {

    private DomainList blackList;
    private final Scanner scanner;
    private final List<String> menu = Arrays.asList(
            "1. Show blacklist",
            "2. Add to black list",
            "3. Remove from black list"
    );

    public TerminalController(DomainList blackList){
        this.blackList = blackList;
        this.blackList.fromFile();
        scanner=new Scanner(System.in);
    }

    private boolean askForConfirmation(){
        System.out.println("[Y/N]");
        String answer = scanner.nextLine();
        return answer.matches("(?i)y(es)?");
    }

    private int getMenuNumber(){
        String answer = scanner.nextLine().trim();
        int menuItem=0;
        if (!answer.isEmpty())
            try{
                menuItem = Integer.parseInt(answer);
            }
            catch (Exception e){
                System.out.println("Bad number format!");
            }
        return menuItem;
    }

    public TerminalController addToBlackList(){
        System.out.println("Please type domain URL to add:");
        String answer = scanner.nextLine();
        try {
            System.out.println(
                    blackList.addDomain(answer) ? "Added" : "Bad domain URL"
            );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant write to file");
        }
        return this;
    }

    public TerminalController removeFromBlackList(){
        System.out.println("Please type domain URL to remove:");
        String answer = scanner.nextLine();
        try {
            System.out.println(
                    blackList.removeDomain(answer) ? "Removed" : "Bad domain URL or domain not in list"
            );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant write to file");
        }
        return this;
    }

    public TerminalController showBlackList(){
        blackList.getDomainList().forEach(System.out::println);
        return this;
    }

    public TerminalController showMenu(){
        menu.forEach(System.out::println);
        return this;
    }

    public TerminalController executeAction(){
        System.out.println("Type a number to make an action");
            switch (getMenuNumber()){
                case 1:
                    showBlackList();
                    break;
                case 2:
                    addToBlackList();
                    resetSquid();
                    break;
                case 3:
                    removeFromBlackList();
                    resetSquid();
                    break;
            }
        return this;
    }

    private void resetSquid(){
        TerminalExecute exec = new TerminalExecute();
        try {
//            String output = exec.executeCommand("service squid restart");
            String output = exec.executeCommand("systemctl restart squid.service");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Error 0001");
        }
        System.out.println("Server reseted succesfully");
    }
}
