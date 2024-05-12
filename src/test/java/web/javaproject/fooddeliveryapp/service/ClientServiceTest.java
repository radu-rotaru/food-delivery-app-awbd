package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.exception.ClientAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.ClientDoesNotExistException;
import org.modelmapper.ModelMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private  ModelMapper modelMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void createClient_WhenClientWithEmailDoesNotExist_SavesAndReturnsClient() {
        Client client = new Client();
        client.setEmail("test@test.com");

        when(clientRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client result = clientService.createClient(client);

        assertNotNull(result);
        assertEquals(client.getEmail(), result.getEmail());
        verify(clientRepository, times(1)).findByEmail(anyString());
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void createClient_WhenClientWithEmailExists_ThrowsException() {
        Client client = new Client();
        client.setEmail("test@test.com");

        when(clientRepository.findByEmail(anyString())).thenReturn(Optional.of(client));

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.createClient(client));
        verify(clientRepository, times(1)).findByEmail(anyString());
        verifyNoMoreInteractions(clientRepository);
    }

    @Test
    public void getClient_WhenClientExists_ReturnsClient() {
        Long clientId = 1L;
        Client client = new Client();
        client.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(modelMapper.map(any(Client.class), eq(ClientDTO.class))).thenReturn(new ClientDTO());

        ClientDTO result = clientService.findById(clientId);

        assertNotNull(result);
        verify(clientRepository, times(1)).findById(clientId);
        verify(modelMapper, times(1)).map(any(Client.class), eq(ClientDTO.class));
    }

    @Test
    public void getClient_WhenClientDoesNotExist_ThrowsException() {
        Long clientId = 1L;

        when(clientRepository.findById(clientId)).thenReturn(Optional.empty());

        assertThrows(ClientDoesNotExistException.class, () -> clientService.findById(clientId));
        verify(clientRepository, times(1)).findById(clientId);
        verifyNoMoreInteractions(modelMapper);
    }

    @Test
    public void save_WhenValidClientDTO_ReturnsSavedClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        Client client = new Client();
        client.setName("John Doe");

        when(modelMapper.map(any(ClientDTO.class), eq(Client.class))).thenReturn(client);
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(modelMapper.map(any(Client.class), eq(ClientDTO.class))).thenReturn(clientDTO);

        ClientDTO result = clientService.save(clientDTO);

        assertNotNull(result);
        assertEquals(clientDTO.getName(), result.getName());
        verify(modelMapper, times(1)).map(any(ClientDTO.class), eq(Client.class));
        verify(clientRepository, times(1)).save(any(Client.class));
        verify(modelMapper, times(1)).map(any(Client.class), eq(ClientDTO.class));
    }

    @Test
    public void deleteById_WhenClientExists_DeletesClient() {
        Long clientId = 1L;

        clientService.deleteById(clientId);

        verify(clientRepository, times(1)).deleteById(clientId);
    }
}
