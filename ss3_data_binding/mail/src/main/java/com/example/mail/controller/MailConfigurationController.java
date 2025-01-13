package com.example.mail.controller;

import com.example.mail.model.MailConfiguration;
import com.example.mail.service.impl.MailConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class MailConfigurationController {
    @Autowired
    private MailConfigurationService mailConfigurationService;

    @GetMapping("/configuration")
    public String showForm(Model model) {
        String language = mailConfigurationService.getLanguage();
        int pageSize = mailConfigurationService.getPageSize();
        MailConfiguration mailConfiguration = new MailConfiguration(language, pageSize);
        model.addAttribute("mailConfiguration", mailConfiguration);
        model.addAttribute("languages", new String[]{"English", "Vietnamese", "Japanese", "Chinese"});
        model.addAttribute("pageSizes", new int[]{5, 10, 15, 25, 50, 100});
        return "form";
    }

    @PostMapping("/configuration")
    public String update(@ModelAttribute("mailConfiguration") MailConfiguration mailConfiguration, Model model) {
        mailConfigurationService.saveConfiguration(mailConfiguration.getLanguage(), mailConfiguration.getPageSize());
        model.addAttribute("message", "Configuration updated successfully!");
        model.addAttribute("mailConfiguration", mailConfiguration);
        model.addAttribute("language", mailConfigurationService.getLanguage());
        model.addAttribute("pageSize", mailConfigurationService.getPageSize());
        return "form";
    }
}
