package trol.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {
    @RequestMapping("/")
    String index(ModelMap model) {
        model.addAttribute("fullName", "Lama");
        return "index";
    }

    @RequestMapping("/todo")
    String todo_index(ModelMap model) {
        return "public/todo/indexToDo";
    }
}
