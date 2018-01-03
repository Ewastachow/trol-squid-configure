package trol.controller.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.domain.trol_api.exception.UnsuccessfulModificationException;
import trol.domain.trol_api.model.Word;
import trol.domain.trol_api.model.WordsList;
import trol.model.UpdateResult;
import trol.model.words.Words;
import trol.service.words.WordsService;

import javax.validation.Valid;

@Controller
public class WordsController {
    @Autowired
    private WordsService wordsService;

    @GetMapping(value = "/words/list/{id}")
    public ModelAndView getWordsList(@PathVariable("id") int id){
        ModelAndView model;
        try {
            WordsList list = wordsService.getWordsList(id);
            model = new ModelAndView();
            model.addObject("wordsList",list);
            model.setViewName("/words/wordslist");
        } catch (Exception e) {
            e.printStackTrace();
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/words/list/{id}")
    public String editListProperties(@Valid @ModelAttribute("wordsList") WordsList wordsList,
                BindingResult bindingResult, Model model, @PathVariable("id") int id
        ){
        WordsList list = wordsService.getWordsList(id);
        wordsList.setWordsSet(list.getWordsSet());
        model.addAttribute(
                "newword",
                new Words()
        );
        if (bindingResult.hasErrors()){
            return "/words/wordslist";
        }
        try {
            wordsService.updateWordsListProperties(wordsList);
        } catch (UnsuccessfulModificationException e) {
            //bindingResult.addError(new ObjectError(""));
            return "/words/wordslist";
        }
        return "redirect:/words";
    }

    @DeleteMapping(value = "/words/list/{id}/edit/{wordId}")
    public @ResponseBody UpdateResult deleteWordFromList(
            @PathVariable("id") int listId,
            @PathVariable("wordId") int wordId){
        UpdateResult result = new UpdateResult();
        try {
            Word word = new Word();
            word.setIdWord(wordId);
            word.setIdWordsList(listId);
            wordsService.deleteWord(word);
            result.success();
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "/words/list/{id}/edit/{wordId}")
    public @ResponseBody UpdateResult updateWordInList(@RequestBody String wordString,
                                                       @PathVariable("wordId") int wordId){
        UpdateResult result = new UpdateResult();
        try {
            Word word = new Word();
            word.setIdWord(wordId);
            word.setWordString(wordString);
            wordsService.updateWordInList(word);
            result.success();
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PostMapping(value = "/words/list/{id}/edit")
    public String addWordToList(
            @Valid @ModelAttribute("newword") Word newword, BindingResult bindingResult,
            Model model, @PathVariable("id") int listId
            ){
        WordsList wordsList = wordsService.getWordsList(listId);
        model.addAttribute("wordsList",wordsList);
        if (bindingResult.hasErrors()){
            return "/words/wordslist";
        }
        newword.setIdWordsList(listId);
        wordsService.addWordToWordsList(newword);
        return "redirect:/words/list/"+listId;
    }
}
