package org.example.service.Impl;

import jakarta.transaction.Transactional;
import org.example.dao.Person;
import org.example.enums.LogStatus;
import org.example.repository.PersonRepository;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PersonRepository personRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LogServiceImpl logService;

    @Autowired
    public UserServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public Person createUser(Person person) {
        Person personByEmail = personRepository.findByLogin(person.getLogin()).orElse(null);
        if (personByEmail == null) {
            String encriptPass = passwordEncoder.encode(person.getPassword());
            person.setPassword(encriptPass);
            return personRepository.save(person);
        }
        String message = "Пользователь с логином " + personByEmail.getLogin() + " уже существует";
        log.error(message);
        logService.writeLog(message, LogStatus.ERROR, "создание пользователя");
        return personByEmail;
    }

    @Override
    public Boolean updateUser(Person person) {
        boolean result = false;
        try {
            Person personByEmail = personRepository.findByEmail(person.getEmail()).orElse(null);
            if (personByEmail.getLogin().equals(person.getLogin()) || personByEmail.getEmail().equals(person.getEmail())) {
                result = true;
            }
            personRepository.save(person);
            result = true;
        } catch (RuntimeException ex) {
            log.error(ex.getMessage());
            logService.writeLog(ex.getMessage(), LogStatus.ERROR, "редактирование пользователя");
            result = false;
        } finally {
            return result;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        if (personRepository.findById(id).isEmpty()) {
            String message = "Пользователь с id: " + id + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "удаление пользователя");
            throw new UsernameNotFoundException("User not found");
        } else {
            personRepository.deleteById(id);
            String message = "Пользователь с id: " + id + " успешно удален";
            log.info(message);
            logService.writeLog(message, LogStatus.INFO, "удаление пользователя");
        }

    }

    @Override
    public Person getUserByEmail(String email) {
        Person person = personRepository.findByEmail(email).orElse(null);
        if (person == null) {
            String message = "Пользователь с email: " + email + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "поиск пользователя");
        }
        return person;
    }

    @Override
    public Person getUserById(long id) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            String message = "Пользователь с id: " + id + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "поиск пользователя");
        }
        return person;
    }

    @Override
    public Person getUserByUsername(String username) {
        Person person = personRepository.findByLogin(username).orElse(null);
        if (person == null) {
            String message = "Пользователь с username: " + username + " не найден";
            log.info(message);
            logService.writeLog(message, LogStatus.WARN, "поиск пользователя");
        }
        return person;
    }

    @Override
    public List<Person> getAllUsers() {
        return personRepository.findAll();
    }
}
