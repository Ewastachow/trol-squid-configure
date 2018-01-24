package trol.controller.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.model.DomainsList;
import trol.service.domains.DomainsService;

import javax.validation.Valid;

/**
 * MVC controller for domains. Handles requests for list of all domains lists, list addition and deletion.
 */
@Controller
public class DomainsController {
    @Autowired
    private DomainsService domainsService;

    /**
     * Returns list of all blocked domains lists.
     * @return view with all blocked domains lists
     */
    @GetMapping(value = "/domains")
    public ModelAndView getDomains(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/domains/domains");
        model.addObject(
                "domains",
                domainsService.getDomainsLists()
        );
        return model;
    }

    /**
     * Handles get mapping for domain list addition form.
     * @param model model representing form data
     * @return empty model and html view with form
     */
    @GetMapping(value = "/domains/add")
    public String getNewDomainsListForm(Model model){
        model.addAttribute("domainsList",new DomainsList());
        return "/domains/form";
    }

    /**
     * Handles post mapping for domain list addition form.
     * @param domainsList validated model class with data given by user
     * @param bindingResult result of DomainsList validation
     * @return redirect to list editing if addition was successful, or back to form with info about failure
     */
    @PostMapping(value = "/domains/add")
    public String addNewDomainsList(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/domains/form";
        }
        domainsService.addDomainsList(domainsList);
        return "redirect:/domains/list/"+domainsList.getIdDomainsList();
    }

    /**
     * Handles deletion of list selected by id
     * @param id list identifier (from database)
     * @return redirect to list of all domains
     */
    @GetMapping(value = "domains/delete/{id}")
    public String deleteDomainsList(@PathVariable("id") int id){
        domainsService.deleteDomainsList(id);
        return "redirect:/domains";
    }
}
