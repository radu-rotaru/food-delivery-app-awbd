package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.model.Client;


@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO toDto (Client client);
    Client toClient (ClientDTO clientDTO);
}