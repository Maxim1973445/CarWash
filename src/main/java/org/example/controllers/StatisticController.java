package org.example.controllers;

import lombok.extern.java.Log;
import org.example.enums.OrderStatus;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@Log
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public String staristic(Model model) {
        return "statistic";
    }

    @PostMapping(value = "/ordersCount")
    public String getStatisticByStation(@RequestParam("stationid") String stationId,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        @RequestParam("status") String status,
                                        Model model) {
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        log.info("Получение количества заказов по станции");
        if (stationId == null) {
            return "statistic";
        }
        Long statId = Long.parseLong(stationId);
        LocalDate startDateTime = LocalDate.parse(startDate);
        LocalDate endDateTime = LocalDate.parse(endDate);
        if (startDateTime.isAfter(endDateTime)) {
            model.addAttribute("message", "Дата начала выбора не может быть позже даты окончания!");
            return "statistic";
        }
        startTime = LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), startDateTime.getDayOfMonth(), 0, 0, 0);
        endTime = LocalDateTime.of(endDateTime.getYear(), endDateTime.getMonth(), endDateTime.getDayOfMonth(), 23, 59, 59);
        int orders = 0;
        if (status.equals("ALL")) {
            orders = orderService.getOrdersByStationBetween(statId, startTime, endTime).size();
        } else {
            OrderStatus orderStatus = OrderStatus.valueOf(status);
            orders = orderService.getOrdersByStationWithStatusBetween(statId, startTime, endTime, orderStatus).size();
        }
        model.addAttribute("ordersCount", orders);
        log.info("Переход в личный кабинет клиента");
        return "statistic";
    }

    @PostMapping(value = "/clientsByStationBetween")
    public String getClientsStatistic(@RequestParam("stationid") String stationId,
                                      @RequestParam("personid") String personId,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate,
                                      Model model) {
        if (stationId == null) {
            return "statistic";
        }
        Long statId = Long.parseLong(stationId);
        LocalDate startDateTime = LocalDate.parse(startDate);
        LocalDate endDateTime = LocalDate.parse(endDate);
        Long persId = Long.parseLong(personId);
        if (startDateTime.isAfter(endDateTime)) {
            model.addAttribute("message", "Дата начала выбора не может быть позже даты окончания!");
            return "statistic";
        }
        LocalDateTime startTime = LocalDateTime.of(startDateTime.getYear(),
                startDateTime.getMonth(),
                startDateTime.getDayOfMonth(),
                0, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(
                endDateTime.getYear(),
                endDateTime.getMonth(),
                endDateTime.getDayOfMonth(),
                23, 59, 59);

        long clientsCount = orderService.getClientsByStationBetween(statId, startTime, endTime).size();
        model.addAttribute("clientsCount", clientsCount);
        return "statistic";
    }
}
