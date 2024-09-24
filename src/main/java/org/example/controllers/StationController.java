package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StationController {

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping(value = "/stationlist")
    public String getStationList(Model model, HttpServletRequest request) {
        model.addAttribute("station", stationService.getStationById(Integer.parseInt(request.getParameter("id"))));
        return "stationList";
    }
}
