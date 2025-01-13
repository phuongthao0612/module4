package com.example.sandwich.service.impl;

import com.example.sandwich.service.IService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SandwichService implements IService {

    @Override
    public List<String> getCondiments() {
        List<String> condiments = new ArrayList<>();
        condiments.add("Lettuce");
        condiments.add("Tomato");
        condiments.add("Mustard");
        condiments.add("Sprouts");
        return condiments;
    }
}
