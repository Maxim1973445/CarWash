package org.example.service;

import org.example.dao.Client;
import org.example.dao.Order;
import org.example.dao.Station;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ClientService {
    Client getClientById(long id);
    Client getClientByPhone(String phone) throws ChangeSetPersister.NotFoundException;
    List<Client> getClientByUsername(String username);
    List<Client> getAllClients();
    Client createClient(Client client);
    Client updateClient(Client client);
    void deleteClient(long id);
    List<Station> getStationsByClientId(long id);
    List<Order> getOrdersByClientId(long id);


}
