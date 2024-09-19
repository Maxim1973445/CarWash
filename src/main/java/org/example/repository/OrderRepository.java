package org.example.repository;

import org.example.dao.Order;
import org.example.dao.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Person> getPersonById(long id);
    List<Order> getOrdersByStationId(long id);
    @Query("SELECT o FROM Order o WHERE o.station.id = :stationId AND DATE(o.startTime) = :date")
    List<Order> findOrdersByStationToDay(@Param("stationId") Long stationId,
                                         @Param("date") LocalDate date);
}
