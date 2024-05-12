package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Courier;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class CourierRepositoryH2Test {
    CourierRepository courierRepository;

    @Autowired
    CourierRepositoryH2Test(CourierRepository courierRepository) {this.courierRepository = courierRepository;}

    @Test
    public void findCourierByPhoneNumber(){
        Optional<Courier> courier = courierRepository.findByPhoneNumber("0777777777");
        assertTrue(courier.isPresent());
        log.info("Find courier by phone number...");
        log.info(courier.get().getName());
    }

    @Test
    public void findCourierByPhoneNumberNotFound(){
        Optional<Courier> courier = courierRepository.findByPhoneNumber("0000000000");
        assertEquals(false, courier.isPresent());
        log.info("Find courier by phone number - Not Found...");
        log.info("Courier not found: " + courier.isEmpty());
    }

    @Test
    public void findCourierById(){
        Optional<Courier> courier = courierRepository.findById(1L);
        assertTrue(courier.isPresent());
        log.info("Find courier by phone number...");
        log.info(courier.get().getName());
    }

    @Test
    public void findCourierByIdNotFound(){
        Optional<Courier> courier = courierRepository.findById(100L);
        assertEquals(false, courier.isPresent());
        log.info("Find courier by phone number - Not Found...");
        log.info("Courier not found: " + courier.isEmpty());
    }
}
