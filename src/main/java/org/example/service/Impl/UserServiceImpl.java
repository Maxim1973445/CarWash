package org.example.service.Impl;

import org.example.dao.*;
import org.example.enums.RoleType;
import org.example.repository.PersonRepository;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Autowired
    public UserServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person createUser(Person person) {
        Person personByEmail = personRepository.findByEmail(person.getEmail()).orElse(null);
        if (personByEmail==null) {
            return personRepository.save(person);
        }
        log.error("Пользователь с таким email уже существует");
        return personByEmail;
    }

    @Override
    public Person updateUser(Person person) {
        if (personRepository.findByLogin(person.getLogin()).isPresent() || personRepository.findByEmail(person.getEmail()).isPresent()) {
            return null;
        }
        return personRepository.save(person);
    }

    @Override
    public void deleteUserById(Long id) {
        if (!personRepository.findById(id).isPresent()) {
            log.info("not deleting user with id: " + id);
            throw new UsernameNotFoundException("User not found");
        } else {
            personRepository.deleteById(id);
            log.info("deleting user with id: " + id);
        }

    }

    @Override
    public Person getUserByEmail(String email) {
        return personRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Person getUserById(long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public Person getUserByUsername(String username) {
        return personRepository.findByLogin(username).orElse(null);
    }

    @Override
    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }
}
