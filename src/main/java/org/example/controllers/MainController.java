package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import org.example.dao.*;
import org.example.enums.OrderStatus;
import org.example.service.Impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log
public class MainController {

    @Autowired
    private StationServiceImpl stationService;

    @Autowired
    private SlotServiceImpl slotService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CarServiceImpl carService;

    @Autowired
    private StationServiceServiceImpl stationServiceService;

    @GetMapping(value = "/")
    public String getMaimPage(Model model, Authentication authentication) {
        LocalDate today = LocalDate.now();
        if (authentication == null) {
            model.addAttribute("isRegistry", false);
        } else {
            model.addAttribute("isRegistry", true);
        }
        model.addAttribute("stations", stationService.getStations());
        model.addAttribute("date", today);
        model.addAttribute("", slotService.getEmptySlots(123, today));
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

    @PostMapping(value = "/changeDate")
    public String changeDate(HttpServletRequest request, Model model) {
        Person client = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Car> cars = carService.getAllCars().stream().filter(e -> e.getPerson().getId().equals(client.getId())).toList();
        List<LocalDateTime> slots = slotService.getEmptySlots(
                Long.parseLong(request.getParameter("stationId")),
                LocalDate.parse(request.getParameter("date")));
        model.addAttribute("station", stationService.getStationById(Long.parseLong(request.getParameter("stationId"))));
        model.addAttribute("slots", slots);
        model.addAttribute("cars", cars);
        log.info("Отображение страницы записи");
        return "registerPage";
    }

    @PostMapping(value = "/sendRegister")
    public String sendRegister(HttpServletRequest request, Model model) {
        Person client = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        String x = request.getParameter("date");
        LocalDateTime date = LocalDateTime.parse(x);
        Car car = carService.getCarById(Long.parseLong(request.getParameter("car")));
        StationService service = stationServiceService.factoryService(request.getParameter("service"));
        Station station = stationService.getStationById(Long.parseLong(request.getParameter("stationId")));
        long count = orderService.count();
        orderService.createOrder(
                new Order(
                        ++count,
                        date,
                        date.plusHours(1),
                        OrderStatus.OPEN,
                        date,
                        car,
                        client,
                        service,
                        station
                )
        );
        return "redirect:/";
    }

    @PostMapping(value = "/deleteOrder")
    public String deleteOrder(HttpServletRequest request) {
        orderService.deleteOrder(Long.parseLong(request.getParameter("orderId")));
        return "redirect:/clientaccount";
    }
}
