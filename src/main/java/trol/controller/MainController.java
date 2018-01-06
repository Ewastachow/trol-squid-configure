package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.filemanager.FileController;
import trol.domain.filemanager.SaveState;
import trol.model.PasswordUpdate;

import javax.validation.Valid;

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
    }

    @GetMapping("/save/state")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody int getSaveState(){
        SaveState saveState = fileController.getState();
        return saveState == SaveState.FREE ? 1 : 0;
    }

    @GetMapping("/settings")
    public String getChangePasswordForm(Model model){
        model.addAttribute("settings", new PasswordUpdate());
        return "/settings";
    }

    @PostMapping("/settings")
    public String changePassword(@Valid PasswordUpdate settings, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/settings";
        }
        if (settings.getNewPasswordFirst() != settings.getNewPasswordSecond()){
            bindingResult.rejectValue("newPasswordSecond", "Passwords must be equal!");
            return "/settings";
        }
        if (settings.getOldPassword() != "admin"){
            bindingResult.rejectValue("oldPassword", "Wrong password!");
            return "/settings";
        }
        return "redirect:/logout?changedpassword";
    }
}
