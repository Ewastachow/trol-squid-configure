package trol.controller.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.model.User;
import trol.service.users.UsersService;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UsersService usersService;

    @GetMapping(value = "/users/user/{id}")
    public ModelAndView getUser(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("/users/user");
        model.addObject(
                "user",
                usersService.getUser(id)
        );
        return model;
    }

    @PostMapping(value = "/users/user/{id}")
    public String updateUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/users/user";
        }
        try {
            usersService.updateUser(user);
        } catch (Exception e) {
            //bindingResult.addError(new ObjectError(""));
            return "/users/user";
        }
        return "redirect:/users";
    }
}
