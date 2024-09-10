package org.example.repository;

import org.example.dao.Owner;
import org.example.dao.Person;
import org.example.dao.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Person> findPersonById(long ownerId);

    Optional<Station> findStationById(long ownerId);
}
