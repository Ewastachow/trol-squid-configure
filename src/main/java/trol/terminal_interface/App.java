package trol.terminal_interface;

import trol.model.filter.domain_list.DomainList;

public class App {

    static TerminalController controller;

    public static void run(){
        controller = new TerminalController(new DomainList("/etc/squid/black-list.acl"));
        while (true) controller
                .showMenu()
                .executeAction();
    }
}
