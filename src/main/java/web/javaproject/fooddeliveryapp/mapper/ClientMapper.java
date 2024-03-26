package web.javaproject.fooddeliveryapp.mapper;


import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.model.Client;

@Component
public class ClientMapper {
    public Client toEntity(ClientDTO clientDTO) {
        return new Client(
            clientDTO.getName(),
            clientDTO.getEmail(),
            clientDTO.getAddress()
        );
    }

    public ClientDTO toDTO(Client client) {
        return new ClientDTO(
            client.getName(),
            client.getEmail(),
            client.getAddress()
        );
    }
}