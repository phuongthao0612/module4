package com.example.currency;

import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    public double convertUsdToVnd(double usd) {
        return usd * 24000;
    }
}
