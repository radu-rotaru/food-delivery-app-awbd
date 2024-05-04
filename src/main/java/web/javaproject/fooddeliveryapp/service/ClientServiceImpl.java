package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.exception.ClientAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.ClientDoesNotExistException;
import web.javaproject.fooddeliveryapp.exception.CourierDoesNotExistException;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.repository.ClientRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;
    ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = new LinkedList<>();
        clientRepository.findAll(Sort.by("name")).iterator().forEachRemaining(clients::add);

        return  clients.stream().map(client -> modelMapper.map(client, ClientDTO.class)).collect(Collectors.toList());
    }

    public Client createClient(Client clientEntity) {
        Optional<Client> existingClient = clientRepository.findByEmail(clientEntity.getEmail());

        if(existingClient.isPresent()) {
            throw new ClientAlreadyExistsException();
        }

        return clientRepository.save(clientEntity);
    }

    public Client getClient(Long clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if(client.isEmpty()) {
            throw new ClientDoesNotExistException();
        }

        return client.get();
    }
}
