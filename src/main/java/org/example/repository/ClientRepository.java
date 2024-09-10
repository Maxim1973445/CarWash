package org.example.repository;

import org.example.dao.Client;
import org.example.dao.Order;
import org.example.dao.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Optional<Client> findClientByPhone(int phone);
    List<Client> findClientByName(String username);
    List<Station> findStationById(long id);
    List<Order> findOrdersById(long id);
}
