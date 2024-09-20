package org.example.service;

import org.example.dao.Order;
import org.example.dao.StationService;

import java.util.List;

public interface StationServiceService {
    org.example.dao.StationService getStationServiceById(long id);
    org.example.dao.StationService createStationService(org.example.dao.StationService stationService);
    org.example.dao.StationService updateStationService(org.example.dao.StationService stationService);
    void deleteStationServiceById(long id);
    List<Order> getOrdersByStationServiceId(long id);
    StationService getServiceById(long i);
}
