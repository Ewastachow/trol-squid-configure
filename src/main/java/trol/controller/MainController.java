package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import trol.dao.appusers.AppUsersDAO;
import trol.domain.filemanager.FileController;
import trol.domain.filemanager.SaveState;
import trol.model.helpers.PasswordUpdate;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Main controller class of application. Handles main page requests, login page,
 * saving blocking settings and user account settings.
 * Uses DAO for Users table to manage account settings.
 * Uses FileController to flush data stored in database to blocking module in order to update blocking rules.
 */
@Controller
public class MainController {
    @Autowired
    private FileController fileController;
    @Autowired
    private AppUsersDAO appUsersDAO;

    /**
     * Handles request for main page of application. Returns appropriate view.
     * @return index html view
     */
    @RequestMapping("/")
     public String index(){
        return "/index";
    }

    /**
     * Returns login page of application.
     * @return login page html
     */
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * Saves application configuration. Blocking rules are updated.
     */
    @GetMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public void saveConfiguration(){
        fileController.saveConfiguration();
    }

    /**
     * Returns status of application saving module. If it's currently saving configuration and restarting
     * then BUSY status is returned. If it's idling and waiting for save request, then FREE status is returned.
     * @return current status of cnfiguration saving module
     */
    @GetMapping("/save/state")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody int getSaveState(){
        SaveState saveState = fileController.getState();
        return saveState == SaveState.FREE ? 1 : 0;
    }

    /**
     * Returns form for changing user password. To change password it is necessary to give an old password
     * and type twice the new password.
     * @param model empty data model for setting new password
     * @return password change html form
     */
    @GetMapping("/settings")
    public String getChangePasswordForm(Model model){
        model.addAttribute("passwordUpdate", new PasswordUpdate());
        return "/settings";
    }

    /**
     * Validates data given by user and saves new password if everything is valid.
     * @param passwordUpdate structure containing old password typed by user and new password typed twice
     * @param bindingResult result of validation
     * @param principal user, who is sending this request
     * @return logout and redirect to login page if data was valid, highlight errors in form otherwise
     */
    @PostMapping("/settings")
    public String changePassword(
            @Valid PasswordUpdate passwordUpdate, BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors()){
            return "/settings";
        }
        // if new passwords are not equal, then return error
        if (!passwordUpdate.getNewPasswordFirst().equals(passwordUpdate.getNewPasswordSecond())){
            bindingResult.rejectValue(
                    "newPasswordSecond",
                    "err_not_equal" ,
                    "Passwords must be equal!");
            return "/settings";
        }
        String username = principal.getName();
        String actualPassword = appUsersDAO.getAppUserByName(username).getPassword();
        // if old password and password given by user are not equal, then return error
        if (!passwordUpdate.getOldPassword().equals(actualPassword)){
            bindingResult.rejectValue(
                    "oldPassword",
                    "err_bad_password" ,
                    "Wrong password!");
            return "/settings";
        }
        appUsersDAO.updateAppUserPassword(username, passwordUpdate.getNewPasswordFirst());
        return "redirect:/logout?changedpassword";
    }
}
