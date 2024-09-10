package org.example.service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.Client;
import org.example.dao.Order;
import org.example.dao.Owner;
import org.example.dao.Station;
import org.example.repository.StationRepository;
import org.example.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StationServiceImpl implements StationService {
    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
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
    public Owner getOwnerByStationId(long id) {
        return stationRepository.findById(id).get().getOwner();
    }

    @Override
    public List<Order> getOrdersByStationId(long id) {
        return stationRepository.findById(id).get().getOrders();
    }

    @Override
    public List<Client> getClientsByStationId(long id) {
        return stationRepository.findById(id).get().getClients();
    }
}
