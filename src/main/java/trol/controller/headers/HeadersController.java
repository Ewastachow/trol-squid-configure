package trol.controller.headers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.model.TransmissionType;
import trol.service.headers.HeadersService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HeadersController {
    @Autowired
    HeadersService headersService;

    @GetMapping(value = "/headers")
    public ModelAndView getTransmissionTypes(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/headers/headers");
        model.addObject(
                "headers",
                headersService.getAllTransmissionTypes()
        );
        return model;
    }

    @GetMapping(value = "/headers/edit/{id}")
    public ModelAndView getTransmissionType(@PathVariable("id") int id){
        ModelAndView model = new ModelAndView();
        model.setViewName("/headers/edit");
        model.addObject(
                "header",
                headersService.getTransmissionType(id)
        );
        return model;
    }


    @PostMapping(value = "/headers/edit/{id}")
    public String updateTransmissionType(@Valid TransmissionType transmissionType, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/headers/headers";
        }
        try {
            headersService.updateTransmissionType(transmissionType);
        } catch (Exception e) {
            //bindingResult.addError(new ObjectError(""));
            return "/headers/headers";
        }
        return "redirect:/headers";
    }

}
