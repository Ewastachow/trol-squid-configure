package trol.controller.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.model.Domain;
import trol.model.DomainsList;
import trol.model.helpers.UpdateResult;
import trol.service.domains.DomainsService;
import trol.validation.TrolDomainValidator;

import javax.validation.Valid;

/**
 * MVC controller for lists of domains. Handles CRUD operations on domains.
 */
@Controller
public class DomainsListController {

    @Autowired
    private DomainsService domainsService;

    @GetMapping(value = "/domains/list/{id}")
    public ModelAndView getDomainsList(@PathVariable("id") int id){
        ModelAndView model;
        try {
            DomainsList list = domainsService.getDomainsList(id);
            model = new ModelAndView();
            model.addObject("domainsList",list);
            model.setViewName("/domains/domainslist");
            model.addObject(
                    "newdomain",
                    new Domain()
            );
        } catch (Exception e) {
            e.printStackTrace();
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/domains/list/{id}")
    public String editListProperties(@Valid @ModelAttribute("domainsList") DomainsList domainsList,
                                     BindingResult bindingResult, Model model, @PathVariable("id") int id){
        DomainsList list = domainsService.getDomainsList(id);
        domainsList.setDomainsSet(list.getDomainsSet());
        model.addAttribute(
                "newdomain",
                new Domain()
        );
        if (bindingResult.hasErrors()){
            return "/domains/domainslist";
        }
        domainsService.updateDomainsListProperties(domainsList);
        return "redirect:/domains";
    }

    @DeleteMapping(value = "/domains/list/{id}/edit/{domainId}")
    public @ResponseBody UpdateResult deleteDomainFromList(
            @PathVariable("id") int listId,
            @PathVariable("domainId") int domainId){
        UpdateResult result = new UpdateResult();
        try {
            Domain domain = new Domain();
            domain.setIdDomain(domainId);
            domain.setIdDomainsList(listId);
            domainsService.deleteDomain(domain);
            result.success();
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "/domains/list/{id}/edit/{domainId}")
    public @ResponseBody UpdateResult updateDomainInList(@RequestBody String domainString,
                                                         @PathVariable("domainId") int domainId){
        UpdateResult result = new UpdateResult();
        TrolDomainValidator validator = new TrolDomainValidator();
        if (validator.isValid(domainString,null)){
            try {
                Domain domain = new Domain();
                domain.setIdDomain(domainId);
                domain.setDomainString(domainString);
                domainsService.updateDomainInList(domain);
                result.success();
            } catch (Exception e) {
                result.setMessage(e.getMessage());
                result.fail();
            }
        }else {
            result.fail("Incorrect domain");
        }
        return result;
    }

    @PostMapping(value = "/domains/list/{id}/edit")
    public String addDomain(
            @Valid @ModelAttribute("newdomain") Domain newdomain, BindingResult bindingResult,
            Model model, @PathVariable("id") int listId
    ){
        DomainsList list = domainsService.getDomainsList(listId);
        model.addAttribute("domainsList",list);
        if (bindingResult.hasErrors()){
            return "/domains/domainslist";
        }
        newdomain.setIdDomainsList(listId);
        domainsService.addDomainToDomainsList(newdomain);
        return "redirect:/domains/list/"+listId;
    }
}
