package web.javaproject.fooddeliveryapp.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.repository.ClientRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

}
