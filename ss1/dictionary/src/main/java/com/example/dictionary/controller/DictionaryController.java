package com.example.dictionary.controller;

import com.example.dictionary.service.impl.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/translate")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("")
    public String dictionary(@RequestParam(value = "word", defaultValue = "") String word, Model model) {
        if (word.isEmpty()) {
            model.addAttribute("vie", "Vui lòng nhập từ cần tra");
        } else {
            String vie = dictionaryService.searchEnglish(word);
            model.addAttribute("vie", vie);
        }
        model.addAttribute("word", word);
        return "dictionary";
    }
}
