package com.example.calculator.service.impl;

import com.example.calculator.service.IService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements IService {

    @Override
    public double calculate(double num1, double num2, String operation) throws ArithmeticException, IllegalArgumentException {
        switch (operation) {
            case "add":
                return num1 + num2;
            case "subtract":
                return num1 - num2;
            case "multiply":
                return num1 * num2;
            case "divide":
                if (num2 == 0) {
                    throw new ArithmeticException("Không thể chia cho 0!");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Phép toán không hợp lệ");
        }
    }

}
