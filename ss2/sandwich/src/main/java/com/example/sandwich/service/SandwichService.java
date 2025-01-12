package com.example.sandwich.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SandwichService {
    public List<String> getCondiments() {
        List<String> condiments = new ArrayList<>();
        condiments.add("Lettuce");
        condiments.add("Tomato");
        condiments.add("Mustard");
        condiments.add("Sprouts");
        return condiments;
    }
}
