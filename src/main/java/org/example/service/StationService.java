package org.example.service;

import org.example.dao.Client;
import org.example.dao.Order;
import org.example.dao.Owner;
import org.example.dao.Station;

import java.util.List;

public interface StationService {
    Station createStation(Station station);
    Station getStationById(long id);
    List<Station> getStations();
    void deleteStation(long id);
    void updateStation(Station station);
    Owner getOwnerByStationId(long id);
    List<Order> getOrdersByStationId(long id);
    List<Client> getClientsByStationId(long id);

}
