package com.example.sandwich.controller;

import com.example.sandwich.service.SandwichService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {
    @Autowired
    SandwichService sandwichService;
    @GetMapping("/show")
    public String showForm(Model model) {
        model.addAttribute("condiments", sandwichService.getCondiments());
        return "form";
    }

    @PostMapping("/save")
    public String save(@RequestParam(value = "condiment", required = false) String[] condiments, Model model) {
        if (condiments == null || condiments.length == 0) {
            model.addAttribute("error", "Vui lòng chọn ít nhất một gia vị.");
            model.addAttribute("condiments", sandwichService.getCondiments());
            return "form";
        }
        model.addAttribute("selectedCondiments", condiments);
        return "form";
    }
}
