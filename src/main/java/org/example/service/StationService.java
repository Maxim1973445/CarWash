package org.example.service;

import org.example.dao.*;

import java.util.List;

public interface StationService {
    Station createStation(Station station);
    Station getStationById(long id);
    List<Station> getStations();
    void deleteStation(long id);
    void updateStation(Station station);
    List<Order> getOrdersByStationId(long id);
    List<Person> getClientsByStationId(long id);
}
