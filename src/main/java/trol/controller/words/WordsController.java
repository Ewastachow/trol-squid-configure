package trol.controller.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import trol.model.ListUpdateOld;
import trol.model.UpdateResult;
import trol.model.words.WordInList;
import trol.model.words.Words;
import trol.service.words.WordsListService;
import trol.service.words.WordsService;

import javax.validation.Valid;

@Controller
public class WordsController {
    @Autowired
    private WordsService wordsService;

    @Autowired
    private WordsListService wordsListService;

    @GetMapping(value = "/words/list/{listName}")
    public ModelAndView getWordsList(@PathVariable String listName){
        ModelAndView model;
        try {
            Words words = wordsListService.getWords(listName);
            model = new ModelAndView();
            model.addObject("words",words);
            model.setViewName("/words/words");
        } catch (Exception e) {
            model = new ModelAndView("redirect:/error.html");
        }
        return model;
    }

    @PostMapping(value = "/words/list/{listName}")
    public String editWordsListHeader(@Valid Words words, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "words";
        }
        wordsService.editListHeader(words);
        return "redirect:/words/list/"+words.getName();
    }

    @DeleteMapping(value = "/words/list/edit")
    public @ResponseBody
    UpdateResult deleteWordFromList(@RequestBody WordInList wordInList){
        UpdateResult result = new UpdateResult();
        try {
            wordsService.deleteWordInList(
                    wordInList.getListName(),
                    wordInList.getWord()
            );
            result.success();
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PutMapping(value = "/words/list/edit")
    public @ResponseBody UpdateResult updateWordInList(@RequestBody ListUpdateOld update){
        UpdateResult result = new UpdateResult();
        try {
            wordsService.editWordInList(
                    update.getListName(),
                    update.getOldValue(),
                    update.getNewValue()
            );
            result.success();
        } catch (Exception e) {
            result.setMessage(e.getMessage());
            result.fail();
        }
        return result;
    }

    @PostMapping(value = "/words/list/edit")
    public @ResponseBody UpdateResult addDomainToList(@RequestBody @Valid WordInList wordInList, BindingResult bindingResult){
        UpdateResult updateResult = new UpdateResult();
        if (bindingResult.hasErrors()){
            updateResult.fail();
            updateResult.setMessage("Error");
            return updateResult;
        }
        try {
            wordsService.addWordToList(
                    wordInList.getListName(),
                    wordInList.getWord()
            );
            updateResult.success();
        } catch (Exception e) {
            updateResult.setMessage(e.getMessage());
            updateResult.fail();
        }
        return updateResult;
    }
}
