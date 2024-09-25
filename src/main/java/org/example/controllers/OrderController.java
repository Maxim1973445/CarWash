package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.example.dao.Order;
import org.example.dao.Station;
import org.example.service.Impl.OrderServiceImpl;
import org.example.service.Impl.StationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private StationServiceImpl stationService;

    @GetMapping(value = "/stationList")
    public String getOrders(Model model, HttpServletRequest request) {
        prepareData(model, request);
        return "stationList";
    }

    @PostMapping(value = "/stationList/updateStatus")
    public String updateOrder(Model model, HttpServletRequest request) {
        Order order = orderService.getOrderById(Long.parseLong(request.getParameter("orderId")));
        order.setOrderStatus(orderService.getOrderStatus(request.getParameter("status")));
        orderService.updateOrder(order);
        prepareData(model, request);
        return "stationList";
    }

    private void prepareData(Model model, HttpServletRequest request) {
        Station station = stationService.getStationById((Long.parseLong(request.getParameter("stationId"))));
        List<Order> orderList = orderService.getAllOrders().stream()
                .filter(e -> e.getStation().getId().equals(station.getId())).toList();
        if (orderList.isEmpty()) {
            model.addAttribute("ordersIsEmpty", true);
        } else {
            model.addAttribute("ordersIsEmpty", false);
        }
        model.addAttribute("station", station);
        model.addAttribute("orderList", orderList);
    }
}
