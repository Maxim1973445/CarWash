package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dao.Order;
import org.example.dao.Station;
import org.example.service.Impl.StationServiceImpl;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping(value = "/order")
    public String getOrders(Model model, HttpServletRequest request) {
        Station station = stationService.getStationById((Long.parseLong(request.getParameter("stationId"))));
        List<Order> orderList = orderService.getAllOrders().stream()
                .filter(e -> e.getStation().getId().equals(station.getId())).toList();
        model.addAttribute("station", station);
        model.addAttribute("orderList", orderList);
        return "order";
    }

    @PostMapping(value = "/order/update")
    public String updateOrder(Model model, HttpServletRequest request) {

        return "redirect:/order";
    }
}
