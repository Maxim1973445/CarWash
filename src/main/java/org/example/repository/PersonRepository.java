package org.example.repository;

import org.example.dao.Person;
import org.example.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByLogin(String username);
    Optional<Person> findByEmail(String email);
    Optional<Person> findPersonEntityByLogin(String username);
}


