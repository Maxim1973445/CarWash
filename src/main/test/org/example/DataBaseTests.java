package org.example;

import org.example.dao.Car;
import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.enums.CarType;
import org.example.enums.OrderStatus;
import org.example.enums.RoleType;
import org.example.repository.CarRepository;
import org.example.repository.OrderRepository;
import org.example.repository.PersonRepository;
import org.example.repository.StationRepository;
import org.example.service.Impl.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class DataBaseTests {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarServiceImpl carServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private StationServiceServiceImpl stationServiceServiceImpl;
    @Autowired
    private StationServiceImpl stationServiceImpl;
    @Autowired
    private StationRepository stationRepository;
    @Autowired
    private OrderRepository orderRepository;


    @Test
    public void getPersonsTest() {
        UserServiceImpl userServiceImpl = new UserServiceImpl(personRepository);
        List<Person> persons = userServiceImpl.getAllUsers();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
    @Test
    public void insertCarTest() {
        UserServiceImpl userService = new UserServiceImpl(personRepository);
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
            user = userService.createUser(person);
        car.setPerson(user);
        carServiceImpl.createCar(car);
    }
    @Test
    public void getPersonsByStationIdTest() {
        CarServiceImpl carServiceImpl = new CarServiceImpl(carRepository);
        Car car = new Car();
        car.setCarNumber("351");
        car.setCarType(CarType.CONVERTIBLE);
        car.setMake("KIA");
        car.setModel("stinger");
        carServiceImpl.createCar(car);

    }
    @Test
    public void insertStationTest(){
        StationServiceImpl stationService = new StationServiceImpl(stationRepository, orderRepository);
        Station station = new Station();
        station.setAddress("Екатеринбург, ул. Вавилова 12");
        station.setStationName("AquaMash");
        station.setStationEmail("aquamash@gmail.com");
        station.setLifeCycle("8:00-23:00");
        station.setFirstPhone("234-45-23");
        station.setCoordinates("56.865210, 60.640468");
        station.setFilePath("/image.png");
        stationService.createStation(station);
    }
    @Test
    public void insertOrderTest(){
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);
        Order order = new Order();
        order.setPerson(userServiceImpl.getUserByEmail("vanya@gmail.com"));
        order.setStation(stationServiceImpl.getStationById(4));
        order.setService(stationServiceServiceImpl.getServiceById(1));
        order.setOrderDate(LocalDateTime.now().toString());
        order.setStartDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.OPEN);
        orderService.createOrder(order);
    }
}
