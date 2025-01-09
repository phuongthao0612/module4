package com.example.dictionary.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryRepository {

    private static Map<String, String> dictionary = new HashMap<>();

    static {
        dictionary.put("apple", "quả táo");
        dictionary.put("cat", "con mèo");
        dictionary.put("book", "quyển sách");
        dictionary.put("spring", "mùa xuân");
    }

    public String searchEnglish(String word) {
        return dictionary.get(word.toLowerCase());
    }
}
