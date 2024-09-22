package org.example.controllers;

import lombok.extern.java.Log;
import org.example.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log
public class MainController {

    @Autowired
    StationServiceImpl stationService;

    @GetMapping(value = "/")
    public String getMaimPage(Model model, Authentication authentication) {
        if (authentication == null) {
            model.addAttribute("isRegistry", false);
        }
        model.addAttribute("stations", stationService.getStations());
        log.info("Отображение главной страницы");
        return "main";
    }

    @GetMapping(value = "/authorization")
    public String getAuthPage() {
        return "authorization";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage() {
        log.info("Отображение страницы регистрации клиента");
        return "registration";
    }
}
