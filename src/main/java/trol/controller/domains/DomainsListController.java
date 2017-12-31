package trol.controller.domains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.exceptions.DomainsListUpdateException;
import trol.model.Update;
import trol.model.domains.DomainInList;
import trol.model.domains.DomainsList;
import trol.model.UpdateResult;
import trol.service.domains.DomainsService;
import trol.service.domains.DomainsListService;

import javax.validation.Valid;

@Controller
public class DomainsListController {
    @Autowired
    private DomainsListService domainsListService;
    @Autowired
    private DomainsService domainsService;

    @GetMapping(value = "/domains/list/{id}")
    public ModelAndView getDomainsList(@PathVariable long id){
        ModelAndView model;
        try {
            DomainsList list = domainsService.getDomainsList(id);
            model = new ModelAndView();
            model.addObject("domainsList",list);
            model.setViewName("/domains/domainslist");
        } catch (Exception e) {
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/domains/list/{id}")
    public String editListHeader(@Valid DomainsList domainsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/domains/domainslist";
        }
        domainsListService.updateListHeader(domainsList);
        return "redirect:/domains/list/"+domainsList.getInfo().getId();
    }

    @DeleteMapping(value = "/domains/list/{id}/edit")
    public @ResponseBody UpdateResult deleteDomainFromList(@RequestBody String domain, @PathVariable long id){
        UpdateResult result = new UpdateResult();
        try {
            domainsListService.deleteDomainInList(
                    id,
                    domain
            );
            result.success();
        } catch (DomainsListUpdateException e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "/domains/list/{id}/edit")
    public @ResponseBody UpdateResult updateDomainInList(@RequestBody Update update, @PathVariable long id){
        UpdateResult result = new UpdateResult();
        try {
            domainsListService.editDomainInList(
                    id,
                    (String) update.getOldValue(),
                    (String) update.getNewValue()
                    );
            result.success();
        } catch (DomainsListUpdateException e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PostMapping(value = "/domains/list/{id}/edit")
    public @ResponseBody UpdateResult addDomainToList(@RequestBody String domain, @PathVariable long id){
        UpdateResult updateResult = new UpdateResult();
        try {
            domainsListService.addDomainInList(id, domain);
            updateResult.success();
        } catch (DomainsListUpdateException e) {
            updateResult.setMessage(e.getMessage());
            updateResult.fail();
        }
        return updateResult;
    }
}
