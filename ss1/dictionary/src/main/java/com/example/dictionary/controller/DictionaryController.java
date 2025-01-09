package com.example.dictionary.controller;

import com.example.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/translate")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;
    @GetMapping("")
    public String dictionary(String word, Model model) {
        if (word != null){
            String vie = dictionaryService.searchEnglish(word);
            model.addAttribute("word", word);
            model.addAttribute("vie", vie);
        }
        return "dictionary";
    }
}
