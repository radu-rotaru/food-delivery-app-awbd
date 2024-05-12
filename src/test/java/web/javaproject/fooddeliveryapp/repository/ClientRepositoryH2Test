package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Client;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class ClientRepositoryH2Test {
    ClientRepository clientRepository;

    @Autowired
    ClientRepositoryH2Test(ClientRepository clientRepository) {this.clientRepository = clientRepository;}

    @Test
    public void findClientByEmail(){
        Optional<Client> client = clientRepository.findByEmail("ana@test.com");
        assertTrue(client.isPresent());
        log.info("Find by client email...");
        log.info(client.get().getName());
    }

    @Test
    public void findClientByEmailNotFound(){
        Optional<Client> client = clientRepository.findByEmail("notexists@test.com");
        assertEquals(false, client.isPresent());
        log.info("Find by client email - Not Found...");
        log.info("Client not found: " + client.isEmpty());
    }
}
