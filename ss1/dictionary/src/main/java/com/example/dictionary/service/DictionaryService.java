package com.example.dictionary.service;

import com.example.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    @Autowired
    private DictionaryRepository dictionaryRepository;
    public String searchEnglish(String word) {
        String vie = dictionaryRepository.searchEnglish(word);
        if (vie == null) {
            return "Không tìm thấy từ";
        }
        return vie;
    }
}
