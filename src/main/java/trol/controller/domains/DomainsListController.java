package trol.controller.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Domain;
import trol.domain.trol_api.model.DomainsList;
import trol.domain.trol_api.model.Mode;
import trol.model.UpdateResult;
import trol.service.domains.DomainsService;

import javax.validation.Valid;

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
        } catch (Exception e) {
            e.printStackTrace();
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/domains/list/{id}")
    public String editListProperties(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/domains/domainslist";
        }
        try {
            domainsService.updateDomainsListProperties(domainsList);
        } catch (UnsuccessfulModificationException e) {
            //bindingResult.addError(new ObjectError(""));
            return "/domains/domainslist";
        }
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
        return result;
    }

    @PostMapping(value = "/domains/list/{id}/edit")
    public @ResponseBody UpdateResult addDomainToList(@RequestBody String domainString,
                                                      @PathVariable("id") int listId){
        UpdateResult updateResult = new UpdateResult();
        try {
            Domain domain = new Domain();
            domain.setDomainString(domainString);
            domain.setIdDomainsList(listId);
            domainsService.addDomainToDomainsList(domain);
            updateResult.success();
        } catch (Exception e) {
            updateResult.setMessage(e.getMessage());
            updateResult.fail();
        }
        return updateResult;
    }
}
