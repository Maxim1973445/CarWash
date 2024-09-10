package org.example.service.Impl;


import org.example.dao.Client;
import org.example.dao.Order;
import org.example.dao.Station;
import org.example.repository.ClientRepository;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client getClientById(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client getClientByPhone(int phone) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findClientByPhone(phone).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public List<Client> getClientByUsername(String username) {
        return clientRepository.findClientByName(username);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Station> getStationsByClientId(long id) {
        return clientRepository.findStationById(id);
    }

    @Override
    public List<Order> getOrdersByClientId(long id) {
        return clientRepository.findOrdersById(id);
    }
}
