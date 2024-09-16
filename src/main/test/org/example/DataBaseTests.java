package org.example;

import org.example.dao.Car;
import org.example.dao.Person;
import org.example.dao.Role;
import org.example.enums.CarType;
import org.example.enums.RoleType;
import org.example.repository.CarRepository;
import org.example.repository.PersonRepository;
import org.example.repository.RoleRepository;
import org.example.service.CarService;
import org.example.service.Impl.CarServiceImpl;
import org.example.service.Impl.RoleServiceImpl;
import org.example.service.Impl.UserServiceImpl;
import org.example.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


@SpringBootTest
public class DataBaseTests {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CarServiceImpl carServiceImpl;
    @Autowired
    private RoleServiceImpl roleServiceImpl;


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
        car.setPerson(person);
        Role admin = roleRepository.findByRoleType(RoleType.ADMIN);
        HashSet roles = new HashSet<>();
        roles.add(admin);
        person.setRoles(roles);
        userService.createUser(person);
        carServiceImpl.createCar(car);
    }
    @Test
    public void insertRoleTest() {
        Role admin = new Role();
        roleServiceImpl = new RoleServiceImpl(roleRepository);
        admin.setRoleType(RoleType.ADMIN);
        admin.setDescription("Админ");
        HashSet roles = new HashSet<>();
        roleServiceImpl.createRole(admin);
    }
}
