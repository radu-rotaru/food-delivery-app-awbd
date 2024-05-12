package web.javaproject.fooddeliveryapp.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Client;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void insertClient() {

        Client client = new Client();
        client.setName("Test Client");
        client.setEmail("test@test.com");
        client.setAddress("test address");

        clientRepository.save(client);

        Optional<Client>  clientOpt = clientRepository.findById(4l);
        client = clientOpt.get();
        assertEquals("Test Client", client.getName());
        assertEquals("test@test.com", client.getEmail());
        assertEquals("test address", client.getAddress());
    }

    @Test
    public void updateEmail(){
        Optional<Client> clientOpt = clientRepository.findById(1L);
        Client client = clientOpt.get();
        client.setEmail("new@test.com");

        clientRepository.save(client);

        clientOpt = clientRepository.findById(1L);
        client = clientOpt.get();
        Assertions.assertEquals("new@test.com", client.getEmail());

    }

    @Test
    public void deleteClient() {
        clientRepository.deleteById(1L);
        Optional<Client>  client = clientRepository.findById(1L);
        assertTrue(client.isEmpty());

    }

}
