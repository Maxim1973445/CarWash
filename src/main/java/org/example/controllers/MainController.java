package org.example.controllers;

import org.example.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    StationServiceImpl stationService;

    @GetMapping(value = "/")
    public String getMaimPage(Model model, Authentication authentication) {
        if (authentication == null) {
            model.addAttribute("isRegistry", false);
        }
        model.addAttribute("stations", stationService.getStations());
        return "main";
    }

    @GetMapping(value = "/authorization")
    public String getAuthPage(Model model) {
        return "authorization";
    }

    @GetMapping(value = "/registration")
    public String getRegistrationPage(Model model) {
        return "registration";
    }
}
