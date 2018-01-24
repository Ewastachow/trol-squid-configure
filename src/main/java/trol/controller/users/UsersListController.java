package trol.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.model.User;
import trol.service.users.UsersService;

import javax.validation.Valid;

@Controller
public class UsersListController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/users")
    public ModelAndView getUsers(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/users/userslist");
        model.addObject(
                "users",
                usersService.getUsersList()
        );
        return model;
    }

    @GetMapping(value = "/users/add")
    public String getNewUserForm(Model model){
        model.addAttribute("user",new User());
        return "/users/userform";
    }

    @PostMapping(value = "/users/add")
    public String addNewUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/users/userform";
        }
        try {
            usersService.addUser(user);
        } catch (Exception e) {
            //return "error";
        }
        return "redirect:/users";
    }

    @GetMapping(value = "users/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        try {
            usersService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/users";
    }
}
