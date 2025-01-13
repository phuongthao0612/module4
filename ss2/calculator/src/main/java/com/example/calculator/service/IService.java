package com.example.calculator.service;

public interface IService {
    double calculate(double num1, double num2, String operation) throws ArithmeticException, IllegalArgumentException;
}
