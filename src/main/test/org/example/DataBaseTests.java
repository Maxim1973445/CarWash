package org.example;

import org.example.dao.*;
import org.example.enums.CarType;
import org.example.enums.OrderStatus;
import org.example.enums.RoleType;
import org.example.repository.PersonRepository;
import org.example.service.Impl.*;
import org.example.service.SlotService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class DataBaseTests {
    @Autowired
    private CarServiceImpl carServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private StationServiceServiceImpl stationServiceServiceImpl;
    @Autowired
    private StationServiceImpl stationServiceImpl;
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SlotService slotService;


    @Test
    public void getPersonsTest() {
        List<Person> persons = userServiceImpl.getAllUsers();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
    @Test
    public void insertCarTest() {
        Car car = new Car();
        car.setCarNumber("343");
        car.setCarType(CarType.CROSSOVER);
        car.setMake("KIA");
        car.setModel("k5");
        Person person = new Person();
        person.setLogin("vanya");
        person.setPassword("124");
        person.setFirstName("Ivan");
        person.setLastName("Ivanov");
        person.setEmail("vanya@gmail.com");
        person.setDateOfBirth(new Date(1990,4,12));
        person.setRole(RoleType.ADMIN);
        Person user = userServiceImpl.getUserByEmail(person.getEmail());
        if(user ==null)
            user = userServiceImpl.createUser(person);
        car.setPerson(user);
        carServiceImpl.createCar(car);
    }
    @Test
    public void getPersonsByStationIdTest() {
        Car car = new Car();
        car.setCarNumber("351");
        car.setCarType(CarType.CONVERTIBLE);
        car.setMake("KIA");
        car.setModel("stinger");
        carServiceImpl.createCar(car);

    }
    @Test
    public void insertStationTest(){
        Person person = new Person();
        person.setLogin("iliya");
        person.setPassword("123");
        person.setFirstName("Iliya");
        person.setLastName("Iliya");
        person.setEmail("iliya@gmail.com");
        person.setDateOfBirth(new Date(1990,8,12));
        person.setRole(RoleType.OWNER);
        Station station = new Station();
        station.setAddress("Екатеринбург, ул. Вавилова 12");
        station.setStationName("AquaMash");
        station.setStationEmail("aquamash@gmail.com");
        station.setOpenTime(LocalTime.of(8,00));
        station.setCloseTime(LocalTime.of(23,00));
        station.setFirstPhone("234-45-23");
        station.setCoordinates("56.865210, 60.640468");
        station.setFilePath("/image.png");
        station.setPerson(person);
        stationServiceImpl.createStation(station);
    }
    @Test
    public void insertOrderTest(){
        int stationId = 12;
        List<LocalDateTime> emptySlots = slotService.getEmptySlots(stationId, LocalDate.now());
        Order order = new Order();
        order.setCar(carServiceImpl.getCarByCarNumber("343"));
        order.setStation(stationServiceImpl.getStationById(stationId));
        order.setService(stationServiceServiceImpl.getServiceById(1));
        order.setOrderDate(LocalDateTime.now());
        order.setStartTime(emptySlots.get(2));
        order.setEndTime(emptySlots.get(2).plusMinutes(59));
        order.setOrderStatus(OrderStatus.OPEN);
        orderServiceImpl.createOrder(order);
    }
    @Test
    public void getPersonByOrderIdTest(){
        Order order = orderServiceImpl.getOrderById(1);
        Person person = order.getCar().getPerson();
    }
    @Test
    public void updateCarTest(){
        Car car = carServiceImpl.getCarByCarNumber("343");
        car.setCarType(CarType.SUV);
        carServiceImpl.updateCar(car);
    }
    @Test
    public void createStationServiceTest(){
        StationService stationService = new StationService();
        stationService.setName("Мойка");
        stationService.setPrice(500);
        stationServiceServiceImpl.createStationService(stationService);
    }
    @Test
    public void createOrderTest(){
        StationService stationService = new StationService();
        stationService.setName("Мойка");
        stationService.setPrice(500);
        stationServiceServiceImpl.createStationService(stationService);
    }
}
