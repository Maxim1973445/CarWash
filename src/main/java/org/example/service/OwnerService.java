package org.example.service;

import org.example.dao.Owner;
import org.example.dao.Person;
import org.example.dao.Station;

public interface OwnerService {
    Owner createOwner(Owner owner);
    Owner updateOwner(Owner owner);
    Owner getOwnerById(long id);
    Person getPersonById(long ownerId);
    Station getStationById(long ownerId);

}
