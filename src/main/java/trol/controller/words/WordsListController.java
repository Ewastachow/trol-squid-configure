package trol.controller.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.model.WordsList;
import trol.service.words.WordsService;

import javax.validation.Valid;

@Controller
public class WordsListController {

    @Autowired
    WordsService wordsService;

    @GetMapping(value = "/words")
    public ModelAndView getWords(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/words/words");
        model.addObject(
                "words",
                wordsService.getWordsLists()
        );
        return model;
    }

    @GetMapping(value = "/words/add")
    public String getNewWordsListForm(Model model){
        model.addAttribute("wordsList",new WordsList());
        return "/words/form";
    }

    @PostMapping(value = "/words/add")
    public String addNewWordsList(@Valid WordsList wordsList, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/words/form";
        }
        try {
            wordsService.addWordsList(wordsList);
        } catch (Exception e) {
            //return "error";
        }
        return "redirect:/words/list/"+wordsList.getIdWordsList();
    }

    @GetMapping(value = "words/delete/{id}")
    public String deleteWordsList(@PathVariable("id") int id){
        try {
            wordsService.deleteWordsList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/words";
    }

}
