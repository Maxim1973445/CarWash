package org.example.repository;

import org.example.dao.Person;
import org.example.dao.Role;
import org.example.enums.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>, PagingAndSortingRepository<Person, Long>, CrudRepository<Person, Long> {
    Optional<Person> findByLogin(String username);
    Optional<Person> findByEmail(String email);
    List<Role> findRolesById(long id);

    List<PersonType> findPersonTypesById(long id);
}


