package org.example.service.Impl;

import lombok.AllArgsConstructor;
import org.example.dao.Owner;
import org.example.dao.Person;
import org.example.dao.Station;
import org.example.repository.OwnerRepository;
import org.example.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    @Autowired
    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwnerById(long id) {
        return ownerRepository.findById(id).orElse(null);
    }


    @Override
    public Person getPersonById(long ownerId) {
        return ownerRepository.findPersonById(ownerId).orElse(null);
    }

    @Override
    public Station getStationById(long ownerId) {
        return ownerRepository.findStationById(ownerId).orElseThrow(IllegalArgumentException::new);
    }
}
