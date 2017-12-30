package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.model.DomainsList.DomainsList;
import trol.service.AccessControlList.AccessControlListService;

import javax.validation.Valid;

@Controller
public class AccessControlListController {
    @Autowired
    AccessControlListService accessControlListService;

    @GetMapping(value = "/lists")
    public ModelAndView getAccessControlList(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/AccessControlList/list");
        model.addObject(
                "accessControlList",
                accessControlListService.getAccessControlList()
        );
        return model;
    }

    @GetMapping(value = "/lists/add")
    public String getNewDomainsListForm(Model model){
        model.addAttribute("domainsList",new DomainsList());
        return "form";
    }

    @PostMapping(value = "/lists/add")
    public String addNewDomainsList(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "form";
        }
        try {
            accessControlListService.addDomainsList(domainsList);
        } catch (Exception e) {
            //return "error";
        }
        return "redirect:/lists/list/"+domainsList.getName();
    }
}
