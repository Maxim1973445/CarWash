package org.example.service.Impl;


import org.example.dao.*;
import org.example.enums.CarType;
import org.example.repository.CarRepository;
import org.example.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CarServiceImpl implements CarService {

    private static final Logger log = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    @Override
    public Car getCarById(long id) {
        return carRepository.findCarById(id).orElse(null);
    }

    @Override
    public Car getCarByCarNumber(String number) {
        return carRepository.findCarByCarNumber(number).orElse(null);
    }

    public List<Car> getCarsByCarType(CarType type) {
        return carRepository.findCarsByCarType(type);
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car createCar(Car car) {
        try {
            if (car.getCarNumber() == null || car.getCarType() == null||car.getPerson() == null)
                throw new IllegalArgumentException("Car number, type and person are required");
            Car findCar = carRepository.findCarByCarNumber(car.getCarNumber()).orElse(null);
            if (findCar != null) {
                return null;
            }
            return carRepository.save(car);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean updateCar(Car car) {
        boolean result = false;
        try {
            Car findCar = carRepository.findCarByCarNumber(car.getCarNumber()).orElse(null);
            if (findCar!= null) {
                carRepository.save(car);
                result = true;
            }
        }catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public void deleteCar(long id) {
        if(carRepository.findCarById(id).isEmpty())
            throw new IllegalArgumentException("Автомобиля с данным номером нет в БД");
        carRepository.deleteById(id);
    }
}
