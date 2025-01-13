package com.example.mail.model;

public class MailConfiguration {
    private String language;
    private int pageSize;
    public MailConfiguration() {

    }
    public MailConfiguration(String language, int pageSize) {
        this.language = "English";
        this.pageSize = 25;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
