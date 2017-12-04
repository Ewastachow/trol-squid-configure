package trol.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackListController {

    @RequestMapping("/black-list/show")
    public String showBlackList() {
        return "Greetings from Spring Boot!";
    }
}
