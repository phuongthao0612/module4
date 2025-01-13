package com.example.mail.service.impl;

import com.example.mail.service.IService;
import org.springframework.stereotype.Service;

@Service
public class MailConfigurationService implements IService {
    private String language = "English";
    private int pageSize = 25;


    @Override
    public String getLanguage() {
        return language;

    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void saveConfiguration(String language, int pageSize) {
        this.language = language;
        this.pageSize = pageSize;
    }
}
