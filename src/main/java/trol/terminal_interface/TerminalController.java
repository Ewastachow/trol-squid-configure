package trol.terminal_interface;

import trol.filter.domain_list.DomainList;

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
        System.out.println("Please type domain URL:");
        String answer = scanner.nextLine();
        System.out.println(
                blackList.add(answer) ? "Added" : "Bad domain URL"
        );
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
                    break;
                case 3:
                    showBlackList();
                    break;
            }
        return this;
    }
}
