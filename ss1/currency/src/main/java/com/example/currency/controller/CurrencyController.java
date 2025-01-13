package com.example.currency.controller;

import com.example.currency.service.impl.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String convert(@RequestParam("usd") double usd, Model model) {
        if (usd < 0) {
            throw new IllegalArgumentException("Giá trị USD không thể nhỏ hơn 0.");
        }
        double vnd = currencyService.convertUsdToVnd(usd);
        model.addAttribute("usd", usd);
        model.addAttribute("vnd", vnd);
        return "currency";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "currency";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "currency";
    }


}
