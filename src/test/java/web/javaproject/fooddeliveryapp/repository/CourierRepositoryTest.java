package web.javaproject.fooddeliveryapp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class CourierRepositoryTest {
    @Autowired
    CourierRepository courierRepository;

    @Test
    public void insertCourier() {

        Courier courier = new Courier();
        courier.setName("Courier");
        courier.setPhoneNumber("0788888888");
        courier.setAvailable(true);

        courierRepository.save(courier);

        Optional<Courier> courierOpt = courierRepository.findById(3l);
        courier = courierOpt.get();
        assertEquals("Courier", courier.getName());
        assertEquals("0788888888", courier.getPhoneNumber());
        assertEquals(true, courier.isAvailable());
    }

    @Test
    public void updatePhoneNumber(){
        Optional<Courier> courierOpt = courierRepository.findById(1L);
        Courier courier = courierOpt.get();
        courier.setPhoneNumber("0744444444");

        courierRepository.save(courier);

        courierOpt = courierRepository.findById(1L);
        courier = courierOpt.get();
        Assertions.assertEquals("0744444444", courier.getPhoneNumber());

    }

    @Test
    public void deleteCourier() {
        courierRepository.deleteById(1L);
        Optional<Courier>  courier = courierRepository.findById(1L);
        assertTrue(courier.isEmpty());

    }
}
