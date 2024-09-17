package org.example.service;

import org.example.dao.Car;
import org.example.enums.CarType;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CarService {
    Car getCarById(long id);
    Car getCarByCarNumber(String carNumber) throws ChangeSetPersister.NotFoundException;
    List<Car> getCarsByCarType(CarType carType);
    List<Car> getAllCars();
    Car createCar(Car Car);
    Car updateCar(Car Car);
    void deleteCar(long id);


}
