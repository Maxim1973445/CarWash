package org.example.service.Impl;


import jakarta.transaction.Transactional;
import org.example.dao.Car;
import org.example.enums.CarType;
import org.example.enums.LogStatus;
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

    @Autowired
    private LogServiceImpl logService;

    @Override
    public Car getCarById(long id) {
        Car car = carRepository.findCarById(id).orElse(null);
        if (car == null) {
            String message = "Автомобиль с id: " + id + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "поиск автомобиля");
        }
        return car;
    }

    @Override
    public Car getCarByCarNumber(String number) {
        Car car = carRepository.findCarByCarNumber(number).orElse(null);
        if (car == null) {
            String message = "Автомобиль с number: " + number + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "поиск автомобиля");
        }
        return car;
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
            if (car.getCarNumber() == null || car.getCarType() == null) {
                String message = "Номер машины или тип кузова не заполнен";
                log.error(message);
                logService.writeLog(message, LogStatus.ERROR, "создание автомобиля");
                throw new IllegalArgumentException("Car number and type are required");
            }
            Car findCar = carRepository.findCarByCarNumber(car.getCarNumber()).orElse(null);
            if (car.getPerson() == null) {
                String message = "Не заполнен владелец автомобиля";
                log.error(message);
                logService.writeLog(message, LogStatus.ERROR, "создание автомобиля");
                return null;
            }
            if (findCar != null) {
                String message = "Автомобиль номером: " + car.getCarNumber() + " уже существует в базе";
                log.error(message);
                logService.writeLog(message, LogStatus.ERROR, "создание автомобиля");
                return findCar;
            }
            return carRepository.save(car);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            logService.writeLog(e.getMessage(), LogStatus.ERROR, "создание автомобиля");
            return null;
        }
    }

    @Override
    public Boolean updateCar(Car car) {
        boolean result = false;
        try {
            Car findCar = carRepository.findCarByCarNumber(car.getCarNumber()).orElse(null);
            if (findCar != null) {
                carRepository.save(car);
                result = true;
            } else {
                String message = "Автомобиль номером: " + car.getCarNumber() + " не существует";
                log.error(message);
                logService.writeLog(message, LogStatus.ERROR, "создание автомобиля");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            logService.writeLog(e.getMessage(), LogStatus.ERROR, "создание автомобиля");
        }
        return result;
    }

    @Override
    public void deleteCar(long id) {
        if (carRepository.findCarById(id).isEmpty()) {
            String message = "Автомобиль с id: " + id + " не существует";
            log.error(message);
            logService.writeLog(message, LogStatus.ERROR, "создание автомобиля");
            throw new IllegalArgumentException("Автомобиля с данным номером нет в БД");
        }
        carRepository.deleteById(id);
    }

    public Long count() {
        return carRepository.count();
    }

    public CarType getType(String value) {
        switch (value) {
            case "SEDAN":
                return CarType.SEDAN;
            case "SUV":
                return CarType.SUV;
            case "CROSSOVER":
                return CarType.CROSSOVER;
            case "HATCHBACK":
                return CarType.HATCHBACK;
            case "COMBY":
                return CarType.COMBY;
            case "WAGON":
                return CarType.WAGON;
            case "CONVERTIBLE":
                return CarType.CONVERTIBLE;
            case "MINIVAN":
                return CarType.MINIVAN;
            default:
                return null;
        }
    }
}
