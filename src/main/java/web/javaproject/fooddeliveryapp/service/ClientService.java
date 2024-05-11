package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDTO> findAll();
    public Client createClient(Client clientEntity);
    public Client getClient(Long clientId);
}
