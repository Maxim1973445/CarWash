package org.example.service;

import org.example.dao.Person;
import org.example.dao.Role;
import org.example.enums.RoleType;

import java.util.List;

public interface UserService {
    Person createUser(Person person);
    Person updateUser(Person person);
    void deleteUserById(Long id);
    Person getUserByEmail(String email);
    Person getUserById(long id);
    Person getUserByUsername(String username);
    List<Person> getAllUsers();
    List<Role> getRolesByUserId(long id);
}
