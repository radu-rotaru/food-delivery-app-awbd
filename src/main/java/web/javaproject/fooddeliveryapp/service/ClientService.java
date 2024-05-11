package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.model.Client;

import java.util.List;


public interface ClientService {
    List<ClientDTO> findAll();
    public Client createClient(Client clientEntity);
    public Client getClient(Long clientId);
    ClientDTO save(ClientDTO clientDTO);
    void deleteById (Long id);
    ClientDTO findById(Long id);
}
