package com.example.calculator.controller;

import com.example.calculator.service.impl.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/calculate")
    public String showForm() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result = calculatorService.calculate(num1, num2, operation);
        model.addAttribute("result", result);
        return "calculator";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "calculator";
    }

    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(ArithmeticException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "calculator";
    }

}
