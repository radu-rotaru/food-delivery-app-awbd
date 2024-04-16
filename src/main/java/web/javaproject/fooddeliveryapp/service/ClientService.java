package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> findAll();
}
