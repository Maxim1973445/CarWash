package org.example.service.Impl;


import org.example.dao.*;
import org.example.enums.CarType;
import org.example.repository.CarRepository;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class CarServiceImpl implements CarService {

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
    public Car getCarByCarNumber(String number) throws ChangeSetPersister.NotFoundException {
        return carRepository.findCarByCarNumber(number);
    }

    public List<Car> getCarsByCarType(CarType type) {
        
        return carRepository.findCarByCarType(type);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car createCar(Car Car) {
        return carRepository.save(Car);
    }

    @Override
    public Car updateCar(Car Car) {
        return carRepository.save(Car);
    }

    @Override
    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
