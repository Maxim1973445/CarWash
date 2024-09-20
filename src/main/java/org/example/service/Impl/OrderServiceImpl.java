package org.example.service.Impl;


import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
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
        if(order.getCar()==null||order.getStation()==null||order.getService()==null){
            throw new IllegalArgumentException("required parameters missing");
        }
        try {
            return orderRepository.save(order);
        }catch(RuntimeException ex){
            throw ex;
        }
    }

    @Override
    public Boolean updateOrder(Order order) throws Exception {
        if (orderRepository.existsById(order.getId())) {
            orderRepository.save(order);
            return true;
        }
        return false;
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
            return orderRepository.findById(orderId).get().getCar().getPerson();
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
    @Override
    public List<Order> getOrdersForStationToDay(Long stationId, LocalDate date) {
            return orderRepository.findOrdersByStationToDay(stationId,date);
    }

    @Override
    public Order getOrderByDate(String dateTime) {
        return null;
    }
}
