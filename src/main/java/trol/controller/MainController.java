package trol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping("/")
     ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @RequestMapping("/login")
    String login(){
        return "/login";
    }
}
