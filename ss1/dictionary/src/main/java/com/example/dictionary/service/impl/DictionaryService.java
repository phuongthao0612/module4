package com.example.dictionary.service.impl;

import com.example.dictionary.repository.DictionaryRepository;
import com.example.dictionary.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService implements IService {

    @Autowired
    private DictionaryRepository dictionaryRepository;

    @Override
    public String searchEnglish(String word) {
        String vie = dictionaryRepository.searchEnglish(word);
        if (vie == null) {
            return "Không tìm thấy từ";
        }
        return vie;
    }
}
