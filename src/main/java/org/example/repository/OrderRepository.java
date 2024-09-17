package org.example.repository;

import org.example.dao.Order;
import org.example.dao.Person;
import org.example.dao.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Person> getPersonById(long id);


}
