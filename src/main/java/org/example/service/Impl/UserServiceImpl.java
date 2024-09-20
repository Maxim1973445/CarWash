package org.example.service.Impl;

import org.example.dao.*;
import org.example.repository.PersonRepository;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements  UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return personRepository.findPersonEntityByLogin(username).orElseThrow(()->new UsernameNotFoundException(username));
//    }

    @Autowired
    public UserServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person createUser(Person person) {
        Person personByEmail = personRepository.findByLogin(person.getLogin()).orElse(null);
        if (personByEmail==null) {
            String encriptPass = passwordEncoder.encode(person.getPassword());
            person.setPassword(encriptPass);
            return personRepository.save(person);
        }
        log.error("Пользователь с таким login уже существует");
        return personByEmail;
    }

    @Override
    public Boolean updateUser(Person person) {
        Boolean result = false;
        try {
            Person personByEmail = personRepository.findByEmail(person.getEmail()).orElse(null);
            if (personByEmail.getLogin().equals(person.getLogin()) || personByEmail.getEmail().equals(person.getEmail())) {
                result = true;
            }
            personRepository.save(person);
            result = true;;
        }catch (RuntimeException ex){
            log.error(ex.getMessage());
            result = false;
        }
        finally {
            return result;
        }
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
