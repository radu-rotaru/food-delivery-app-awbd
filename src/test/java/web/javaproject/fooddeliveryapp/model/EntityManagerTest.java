package web.javaproject.fooddeliveryapp.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("h2")
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;;

    @Test
    public void findClient(){
        System.out.println(entityManager.getEntityManagerFactory());
        Client clientFound = entityManager.find(Client.class, 1L);;
        assertEquals(clientFound.getName(), "Andrei");

    }

}