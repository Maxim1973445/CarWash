package org.example.service;

import org.example.dao.Person;

import java.util.List;

public interface UserService {
    Person createUser(Person person);
    Boolean updateUser(Person person);
    void deleteUserById(Long id);
    Person getUserByEmail(String email);
    Person getUserById(long id);
    Person getUserByUsername(String username);
    List<Person> getAllUsers();
}
