package org.example.controllers;

import lombok.extern.java.Log;
import org.example.dao.Order;
import org.example.enums.OrderStatus;
import org.example.service.OrderService;
import org.example.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
@Log
public class StatisticController {

    @Autowired
    OrderService orderService;
    @Autowired
    StationService stationService;

    @PostMapping(value = "/ordersCount")
    public String getOrdersCount(@RequestParam("stationId") String stationId, Model model){
        HashMap<String,Long> map = new HashMap<>();
        Long statId = Long.parseLong(stationId);
        List<Order> orders = orderService.getOrdersByStationId(statId);
        map.put("all", (long) orders.size());
        long cancelledOrdersCount = orders.stream().filter(order->order.getOrderStatus().equals(OrderStatus.CANCELLED)).count();
        long openedOrdersCount = orders.stream().filter(order->order.getOrderStatus().equals(OrderStatus.OPEN)).count();
        long inProgressOrdersCount = orders.stream().filter(order->order.getOrderStatus().equals(OrderStatus.INPROGRESS)).count();
        long completedOrdersCount = orders.stream().filter(order->order.getOrderStatus().equals(OrderStatus.COMPLETED)).count();

        long uniqueClientsCount = orders.stream().map(Order::getPerson).distinct().count();
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        long ordersPerMonth = orders.stream()
                .filter(order->order.getStartTime().getYear()==year&&order.getStartTime().getMonthValue()==month)
                .count();
        model.addAttribute("cancelledOrders", cancelledOrdersCount);
        model.addAttribute("inProgressOrders", inProgressOrdersCount);
        model.addAttribute("completedOrders", completedOrdersCount);
        model.addAttribute("openedOrders", openedOrdersCount);
        model.addAttribute("uniqueClients", uniqueClientsCount);
        model.addAttribute("ordersPerMonth", ordersPerMonth);
        return "statistic";
    }
}
