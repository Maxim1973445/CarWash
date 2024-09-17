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
    public Car getCarByCarNumber(String number) {
        return carRepository.findCarByCarNumber(number);
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
        Car findCar = carRepository.findCarByCarNumber(car.getCarNumber());
        if(car.getPerson()==null)
            return null;
        if(findCar != null){
            return findCar;
        }
        return carRepository.save(car);
    }

    @Override
    public Car updateCar(Car car) {
        if(carRepository.findCarByCarNumber(car.getCarNumber())!=null) {
            return carRepository.save(car);
        }
        return null;
    }

    @Override
    public void deleteCar(long id) {
        if(carRepository.findCarById(id).isEmpty())
            throw new IllegalArgumentException("Автомобиля с данным номером нет в БД");
        carRepository.deleteById(id);
    }
}
