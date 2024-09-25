package org.example.service.Impl;

import jakarta.transaction.Transactional;
import org.example.dao.Order;
import org.example.dao.StationService;
import org.example.repository.StationServiceRepository;
import org.example.service.StationServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StationServiceServiceImpl implements StationServiceService {


    private final StationServiceRepository stationServiceRepository;

    @Autowired
    public StationServiceServiceImpl(StationServiceRepository stationServiceRepository) {
        this.stationServiceRepository = stationServiceRepository;
    }

    @Override
    public StationService getStationServiceById(long id) {
        return stationServiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrdersByStationServiceId(long id) {
        return stationServiceRepository.findById(id).get().getOrders();
    }

    @Override
    public StationService getServiceById(long id) {
        return stationServiceRepository.findById(id).orElse(null);
    }

    @Override
    public StationService createStationService(StationService stationService) {
        return stationServiceRepository.save(stationService);
    }

    @Override
    public StationService updateStationService(StationService stationService) {
        if (stationServiceRepository.existsById(stationService.getId())) {
            return stationServiceRepository.save(stationService);
        }
        return null;
    }

    @Override
    public void deleteStationServiceById(long id) {
        if (stationServiceRepository.existsById(id)) {
            stationServiceRepository.deleteById(id);
        }
    }

    public Long count() {
        return stationServiceRepository.count();
    }

    public StationService factoryService(String value) {
        Long count = stationServiceRepository.count();
        switch (value) {
            case "wash":
                return new StationService(
                        ++count,
                        "Мойка",
                        1000,
                        null
                );
            case "polish":
                return new StationService(
                        ++count,
                        "Мойка",
                        5000,
                        null
                );
            case "cleaning":
                return new StationService(
                        ++count,
                        "Мойка",
                        2400,
                        null
                );
            default:
                return null;
        }
    }

}
