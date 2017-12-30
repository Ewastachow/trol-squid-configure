package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.exceptions.DomainsListNotFoundException;
import trol.exceptions.DomainsListUpdateException;
import trol.model.DomainsList.DomainInList;
import trol.model.DomainsList.DomainsList;
import trol.model.DomainsList.DomainsListUpdate;
import trol.model.UpdateResult;
import trol.service.DomainsList.DomainsListService;

import javax.validation.Valid;

@Controller
public class DomainsListController {
    @Autowired
    private DomainsListService domainsListService;

    @GetMapping(value = "/lists/list/{listName}")
    public ModelAndView getDomainsList(@PathVariable String listName){
        ModelAndView model;
        try {
            DomainsList list = domainsListService.getList(listName);
            model = new ModelAndView();
            model.addObject("domainsList",list);
            model.setViewName("domainsList");
        } catch (DomainsListNotFoundException e) {
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/lists/list/{listName}")
    public String editDomainsListHeader(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "domainsList";
        }
        domainsListService.editListHeader(domainsList);
        return "redirect:/lists/list/"+domainsList.getName();
    }

    @DeleteMapping(value = "/lists/list/edit")
    public @ResponseBody UpdateResult deleteDomainFromList(@RequestBody DomainInList domainInList){
        UpdateResult result = new UpdateResult();
        try {
            domainsListService.deleteDomainInList(
                    domainInList.getListName(),
                    domainInList.getDomain()
            );
            result.success();
        } catch (DomainsListUpdateException e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "/lists/list/edit")
    public @ResponseBody UpdateResult updateDomainInList(@RequestBody DomainsListUpdate update){
        UpdateResult result = new UpdateResult();
        try {
            domainsListService.editDomainInList(
                    update.getListName(),
                    update.getOldDomain(),
                    update.getNewDomain()
                    );
            result.success();
        } catch (DomainsListUpdateException e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PostMapping(value = "/lists/list/edit")
    public @ResponseBody UpdateResult addDomainToList(@RequestBody @Valid DomainInList domainInList, BindingResult bindingResult){
        UpdateResult updateResult = new UpdateResult();
        if (bindingResult.hasErrors()){
            updateResult.fail();
            updateResult.setMessage("Bad domain name");
            return updateResult;
        }
        try {
            domainsListService.addDomainInList(
                    domainInList.getListName(),
                    domainInList.getDomain()
            );
            updateResult.success();
        } catch (DomainsListUpdateException e) {
            updateResult.setMessage(e.getMessage());
            updateResult.fail();
        }
        return updateResult;
    }
}
