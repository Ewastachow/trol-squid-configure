package trol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import trol.model.DomainsListOld;
import trol.model.ListUpdateOld;
import trol.service.DomainsService;
import javax.validation.Valid;

@Controller
public class BlackListController {

    @Autowired
    private DomainsService domainsService;

    @RequestMapping(value={"/blacklist"}, method = RequestMethod.GET)
    public ModelAndView getBlackList() {
        ModelAndView model = new ModelAndView();
        model.addObject("blacklist",
                new DomainsListOld(domainsService.getDomainsList()));
        model.setViewName("blacklist");
        return model;
    }

    @RequestMapping(value={"/blacklist"}, method = RequestMethod.POST)
    public String addToBlackList(@Valid DomainsListOld blacklist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            //TODO add message - in validator
            return "redirect:/blacklist";
        }
        domainsService.addDomain(blacklist.getNewDomain());
        //TODO success message
        return "redirect:/blacklist";
    }

    @RequestMapping(value = {"/blacklist"}, method = RequestMethod.DELETE)
    public String delete(@RequestBody String domain){
        domainsService.deleteDomain(domain);
        return "index";
    }

    @RequestMapping(value = {"/blacklist"}, method = RequestMethod.PUT)
    public String put(@RequestBody ListUpdateOld update){
        domainsService.replaceDomain(update.getOldValue(),update.getNewValue());
        return "index";
    }
}
