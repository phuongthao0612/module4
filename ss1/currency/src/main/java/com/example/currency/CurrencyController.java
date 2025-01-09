package com.example.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/convert")
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;

    @GetMapping("")
    public String showForm() {
        return "currency";
    }

    @PostMapping("")
    public String convert(double usd, Model model) {
        double vnd = currencyService.convertUsdToVnd(usd);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        return "currency";
    }

}
