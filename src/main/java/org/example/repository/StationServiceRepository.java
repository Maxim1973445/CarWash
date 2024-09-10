package org.example.repository;

import org.example.dao.StationService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationServiceRepository extends JpaRepository<StationService,Long> {
    Optional<StationService> findStationServiceByName(String stationName);

}
