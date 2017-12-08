package trol.terminal_interface;

import trol.domain.filter.domain_list.DomainList;

import java.io.IOException;

public class App {

    static TerminalController controller;

    public static void run(){
        try {
            controller = new TerminalController(new DomainList("/etc/squid/black-list.acl"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) controller
                .showMenu()
                .executeAction();
    }
}
