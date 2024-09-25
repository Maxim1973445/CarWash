package org.example.service.Impl;


import lombok.extern.java.Log;
import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.enums.LogStatus;
import org.example.enums.OrderStatus;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Stream;

@Service
@Transactional
@Log
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    LogServiceImpl logService;

    @Override
    public Order createOrder(Order order) {
        if (order.getCar() == null || order.getStation() == null || order.getService() == null) {
            String message = "У заявки не заполнен один или несколько атрибутов";
            log.warning(message);
            logService.writeLog(message, LogStatus.ERROR, "создание заявки");
            throw new IllegalArgumentException("required parameters missing");
        }
        try {
            return orderRepository.save(order);
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @Override
    public Boolean updateOrder(Order order) {
        if (orderRepository.existsById(order.getId())) {
            orderRepository.save(order);
            return true;
        }
        String message = "Заявка с id: " + order.getId() + " не найдена";
        log.warning(message);
        logService.writeLog(message, LogStatus.ERROR, "поиск заявки");
        return false;
    }

    @Override
    public void deleteOrder(long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            String message = "Заявка с id: " + orderId + " не найдена";
            log.warning(message);
            logService.writeLog(message, LogStatus.ERROR, "поиск заявки");
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        String message = "Заявка с id: " + orderId + " не найдена";
        log.warning(message);
        logService.writeLog(message, LogStatus.ERROR, "поиск заявки");
        return order;
    }

    @Override
    public Person getPersonByOrderId(long orderId) {
        if (orderRepository.existsById(orderId)) {
            return orderRepository.findById(orderId).get().getCar().getPerson();
        }
        String message = "У заявки с id: " + orderId + " не заполнен заказчик";
        log.warning(message);
        logService.writeLog(message, LogStatus.WARN, "поиск заявки");
        return null;
    }

    @Override
    public Station getStationByOrderId(long orderId) {
        if (orderRepository.existsById(orderId)) {
            return orderRepository.findById(orderId).get().getStation();
        }
        return null;
    }

    @Override
    public List<Order> getOrdersForStationToDay(Long stationId, LocalDate date) {
        return orderRepository.findOrdersByStationToDay(stationId, date);
    }

    @Override
    public List<Order> getOrdersByDate(String dateTime) {
        LocalDate date = LocalDate.parse(dateTime);
        LocalDateTime startDate = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),0,0,0);
        LocalDateTime endDate = LocalDateTime.of(date.getYear(),date.getMonth(),date.getDayOfMonth(),23,59,59);


        return orderRepository.findAll()
                .stream()
                .filter(order->order.getStartTime().isAfter(startDate)&&order.getStartTime().isBefore(endDate))
                .toList();
    }

    @Override
    public List<Order> getOrdersByStationWithStatusBetween(
            Long statId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            OrderStatus orderStatus) {
        Stream<Order> orders = orderRepository.getOrdersByStationId(statId)
                .stream()
                .filter(order -> order.getStartTime().isAfter(startTime) && order.getStartTime().isBefore(endTime));
        if (orderStatus != null)
            orders = orders.filter(order -> order.getOrderStatus().equals(orderStatus));
        return orders.toList();
    }

    @Override
    public List<Order> getOrdersByStationBetween(Long stationId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        List<Order> ordersBetweenDate = new ArrayList<>();
        List<Order> orders = orderRepository.getOrdersByStationId(stationId);
        for (Order order : orders) {
            if (order.getStartTime().isAfter(startDateTime) && order.getStartTime().isBefore(endDateTime)) {
                ordersBetweenDate.add(order);
            }
        }
        return ordersBetweenDate;
    }

    @Override
    public List<Person> getClientsByStationBetween(Long stationId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Person> distinctOrdersBetween = new ArrayList<>();
        List<Order> orders = orderRepository.getOrdersByStationId(stationId);
        for (Order order : orders) {
            if (order.getStartTime().isAfter(startTime) && order.getStartTime().isBefore(endTime)) {
                if (!distinctOrdersBetween.contains(order.getPerson()))
                    distinctOrdersBetween.add(order.getPerson());
            }
        }
        return distinctOrdersBetween;
    }

    public Long count(){
        return orderRepository.count();
    }
}
