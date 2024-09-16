package org.example.repository;

import org.example.dao.Car;
import org.example.dao.Order;
import org.example.dao.Station;
import org.example.enums.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    Optional<Car> findCarById(long id);
    Car findCarByCarNumber(String carNumber);
    List<Car> findCarByCarType(CarType type);
}
