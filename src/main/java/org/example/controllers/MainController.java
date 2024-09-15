package org.example.controllers;

import org.example.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    StationServiceImpl stationService;

    @GetMapping(value = "/")
    public String getMaimPage(Model model) {
        boolean isAuth = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        model.addAttribute("isRegistry", isAuth);
        model.addAttribute("stations", stationService.getStations());
        return "main";
    }

    @GetMapping(value = "/auth")
    public String getAuthPage(Model model) {
        return "redirect:authorization";
    }
}
