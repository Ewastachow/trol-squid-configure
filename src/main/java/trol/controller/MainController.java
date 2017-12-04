package trol.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
    @RequestMapping("/")
    String index() {
        return "index";
    }
}
