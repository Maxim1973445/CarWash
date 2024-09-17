package org.example.service.Impl;


import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        if(orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        }
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    @Override
    public Person getPersonByOrderId(long orderId) {
        if(orderRepository.existsById(orderId)) {
            return orderRepository.findById(orderId).get().getPerson();
        }
        return null;
    }

    @Override
    public Station getStationByOrderId(long orderId) {
        if(orderRepository.existsById(orderId)) {
            return orderRepository.findById(orderId).get().getStation();
        }
        return null;
    }
}
