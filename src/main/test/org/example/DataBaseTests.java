package org.example;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.dao.Client;
import org.example.dao.Person;
import org.example.repository.ClientRepository;
import org.example.repository.PersonRepository;
import org.example.service.Impl.ClientServiceImpl;
import org.example.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class DataBaseTests {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Test
    public void getPersonsTest() {
        UserServiceImpl userServiceImpl = new UserServiceImpl(personRepository);
        List<Person> persons = userServiceImpl.getAllUsers();
        for (Person person : persons) {
            System.out.println(person);
        }
    }
    @Test
    public void insertClientTest() {

    }
}
