package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.filemanager.FileController;

@Controller
public class MainController {
    @Autowired
    private FileController fileController;

    @RequestMapping("/")
     ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        return model;
    }

    @RequestMapping("/login")
    String login(){
        return "/login";
    }

    @GetMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveConfiguration(){
        fileController.saveConfiguration();
        System.out.println("dostalem clicka stan:"+ fileController.getState());
    }
}
