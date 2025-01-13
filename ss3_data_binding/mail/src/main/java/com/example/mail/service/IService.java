package com.example.mail.service;

public interface IService {
    String getLanguage();

    int getPageSize();

    void saveConfiguration(String language, int pageSize);
}
