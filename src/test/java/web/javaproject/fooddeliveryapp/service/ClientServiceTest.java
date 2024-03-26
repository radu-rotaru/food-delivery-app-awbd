package web.javaproject.fooddeliveryapp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import web.javaproject.fooddeliveryapp.exception.ClientAlreadyExistsException;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    public void testCreateClient_Success() {
        Client client = new Client("John", "john@gmail.com", "Bucharest");
        when(clientRepository.findByEmail(client.getEmail()))
                .thenReturn(Optional.empty());

        Client savedClient = new Client(Long.valueOf(1), "John", "john@gmail.com", "Bucharest");

        when(clientRepository.save(client)).thenReturn(savedClient);

        Client createdClient = clientService.createClient(client);

        assertThat(createdClient).isNotNull();
        assertThat(createdClient.getName()).isEqualTo(client.getName());
        assertThat(createdClient.getEmail()).isEqualTo(client.getEmail());
        assertThat(createdClient.getAddress()).isEqualTo(client.getAddress());
    }

    @Test
    public void testCreateClient_EmailAlreadyExists() {
        Client client = new Client("John", "john@gmail.com", "Bucharest");
        Client alreadySavedClient = new Client("John Doe", "john@gmail.com", "London");

        when(clientRepository.findByEmail(anyString())).thenReturn(Optional.of(alreadySavedClient));

        assertThrows(ClientAlreadyExistsException.class, () -> clientService.createClient(client));
    }
}
