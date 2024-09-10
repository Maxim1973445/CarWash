package org.example.service.Impl;

import org.example.dao.Order;
import org.example.dao.StationService;
import org.example.repository.StationServiceRepository;
import org.example.service.StationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StationServiceServiceImpl implements StationServiceService {

    @Autowired
    private StationServiceRepository stationServiceRepository;

    @Override
    public StationService getStationServiceById(long id) {
        return stationServiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByStationServiceId(long id) {
        return stationServiceRepository.findById(id).get().getOrders();
    }

    @Override
    public StationService createStationService(StationService stationService) {
        return stationServiceRepository.save(stationService);
    }

    @Override
    public StationService updateStationService(StationService stationService) {
        return stationServiceRepository.save(stationService);
    }

    @Override
    public void deleteStationServiceById(long id) {
        stationServiceRepository.deleteById(id);
    }

}
