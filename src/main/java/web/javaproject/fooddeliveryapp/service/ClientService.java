package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.exception.ClientAlreadyExistsException;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.repository.ClientRepository;

import java.util.Optional;

@Service
@Validated
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client clientEntity) {
        Optional<Client> existingClient = clientRepository.findByEmail(clientEntity.getEmail());

        if(existingClient.isPresent()) {
            throw new ClientAlreadyExistsException();
        }

        return clientRepository.save(clientEntity);
    }

    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }
}