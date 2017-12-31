package trol.controller.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.model.words.Words;
import trol.service.words.WordsListService;

import javax.validation.Valid;

@Controller
public class WordsListController {

    @Autowired
    private WordsListService wordsListService;

    @GetMapping(value = "/words")
    public ModelAndView getWordsList(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/words/list");
        model.addObject("words",wordsListService.getWordsList());
        return model;
    }

    @GetMapping(value = "/words/add")
    public String getNewWordsForm(Model model){
        model.addAttribute("words",new Words());
        return "/words/form";
    }

    @PostMapping(value = "/words/add")
    public String addNewWords(@Valid Words words, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/words/form";
        }
        try {
            wordsListService.addWords(words);
        } catch (Exception e) {
            //return "error";
        }
        return "redirect:/words/list/"+words.getName();
    }


}
