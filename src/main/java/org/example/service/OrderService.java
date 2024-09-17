package org.example.service;

import org.example.dao.*;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrder(long orderId);
    List<Order> getAllOrders();
    Order getOrderById(long orderId);
    Person getPersonByOrderId(long orderId);
    Station getStationByOrderId(long orderId);
}
