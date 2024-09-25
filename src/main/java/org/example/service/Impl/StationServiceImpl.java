package org.example.service.Impl;


import org.example.dao.Car;
import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.repository.OrderRepository;
import org.example.repository.StationRepository;
import org.example.service.OrderService;
import org.example.service.StationService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final UserService userService;

    @Autowired
    public StationServiceImpl(StationRepository stationRepository, UserServiceImpl userService) {
        this.stationRepository = stationRepository;
        this.userService = userService;

    }

    @Override
    public Station createStation(Station station) {
        if (station.getPerson() == null)
            throw new IllegalArgumentException("Person is null");
        if (station.getPerson().getId() == null) {
            Person pers = userService.createUser(station.getPerson());
            station.setPerson(pers);
        }
        return stationRepository.save(station);
    }

    @Override
    public Station getStationById(long id) {
        return stationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Station> getStations() {
        return stationRepository.findAll();
    }

    @Override
    public void deleteStation(long id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void updateStation(Station station) {
        stationRepository.save(station);

    }

    @Override
    public List<Order> getOrdersByStationId(long id) {
        return stationRepository.findById(id).get().getOrders();
    }


    @Override
    public List<Person> getClientsByStationId(long id) {
        return stationRepository.findById(id).get().getOrders().stream().map(Order::getPerson).toList();
    }

}
