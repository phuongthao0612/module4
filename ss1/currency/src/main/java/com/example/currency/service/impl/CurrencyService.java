package com.example.currency.service.impl;

import com.example.currency.service.IService;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService implements IService {
    @Override
    public double convertUsdToVnd(double usd) {
        return usd * 24000;
    }
}
