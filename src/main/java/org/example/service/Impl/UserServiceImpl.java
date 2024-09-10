package org.example.service.Impl;


import org.example.dao.Client;
import org.example.dao.Person;
import org.example.dao.Role;
import org.example.enums.PersonType;
import org.example.repository.PersonRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Person createUser(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person updateUser(Person person) {
        return personRepository.save(person);
    }

    @Override
    public void deleteUserById(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person getUserByEmail(String email) {
        return personRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Person getUserById(long id) {
        return  personRepository.findById(id).orElse(null);
    }

    @Override
    public Person getUserByUsername(String username) {
        return personRepository.findByLogin(username).orElse(null);
    }

    @Override
    public List<Person> getAllUsers() {

        return personRepository.findAll();
    }

    @Override
    public List<Role> getRolesByUserId(long id) {
        return personRepository.findRolesById(id);
    }

    @Override
    public List<PersonType> getPersonTypesByPersonId(long id) {
        return personRepository.findPersonTypesById(id);
    }

    @Override
    public List<Client> getClientsById(long id) {
        return List.of();
    }
}
