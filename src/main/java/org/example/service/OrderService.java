package org.example.service;

import org.example.dao.*;
import org.example.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Boolean updateOrder(Order order) throws Exception;
    void deleteOrder(long orderId);
    List<Order> getAllOrders();
    Order getOrderById(long orderId);
    Person getPersonByOrderId(long orderId);
    Station getStationByOrderId(long orderId);
    List<Order> getOrdersForStationToDay(Long stationId, LocalDate date);

    List<Order> getOrdersByDate(String dateTime);
    List<Order> getOrdersByStationWithStatusBetween(
            Long statId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            OrderStatus orderStatus);

    List<Order> getOrdersByStationBetween(Long statId, LocalDateTime startTime, LocalDateTime endTime);
    List<Person> getClientsByStationBetween(Long statId, LocalDateTime startTime, LocalDateTime endTime);

    List<Order> getOrdersByStationId(Long stationId);
}
