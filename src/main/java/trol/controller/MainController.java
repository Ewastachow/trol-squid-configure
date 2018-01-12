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
import trol.dao.appusers.AppUsersDAO;
import trol.domain.filemanager.FileController;
import trol.domain.filemanager.SaveState;
import trol.model.PasswordUpdate;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    private FileController fileController;
    @Autowired
    private AppUsersDAO appUsersDAO;

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
        model.addAttribute("passwordUpdate", new PasswordUpdate());
        return "/settings";
    }

    @PostMapping("/settings")
    public String changePassword(
            @Valid PasswordUpdate passwordUpdate, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "/settings";
        }
        if (!passwordUpdate.getNewPasswordFirst().equals(passwordUpdate.getNewPasswordSecond())){
            bindingResult.rejectValue("newPasswordSecond", "err_not_equal" ,"Passwords must be equal!");
            return "/settings";
        }
        String username = principal.getName();
        String actualPassword = appUsersDAO.getAppUserByName(username).getPassword();
        if (!passwordUpdate.getOldPassword().equals(actualPassword)){
            bindingResult.rejectValue("oldPassword","err_bad_password" ,"Wrong password!");
            return "/settings";
        }
        appUsersDAO.updateAppUserPassword(username, passwordUpdate.getNewPasswordFirst());
        return "redirect:/logout?changedpassword";
    }
}
