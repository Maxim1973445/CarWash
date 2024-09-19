package org.example.service;

import org.example.dao.*;

import java.time.LocalDate;
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

    Order getOrderByDate(String dateTime);
}
