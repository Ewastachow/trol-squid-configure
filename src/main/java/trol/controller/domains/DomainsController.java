package trol.controller.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.model.DomainsList;
import trol.service.domains.DomainsService;

import javax.validation.Valid;

@Controller
public class DomainsController {
    @Autowired
    DomainsService domainsService;

    @GetMapping(value = "/domains")
    public ModelAndView getDomains(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/domains/domains");
        model.addObject(
                "domains",
                domainsService.getDomainsListsList()
        );
        return model;
    }

    @GetMapping(value = "/domains/add")
    public String getNewDomainsListForm(Model model){
        model.addAttribute("domainsList",new DomainsList());
        return "/domains/form";
    }

    @PostMapping(value = "/domains/add")
    public String addNewDomainsList(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/domains/form";
        }
        try {
            domainsService.createNewDomainsList(domainsList);
        } catch (Exception e) {
            //return "error";
        }
        return "redirect:/domains/list/"+domainsList.getIdDomainsList();
    }
}
