package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.CourierReview;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class CourierReviewRepositoryH2Test {

    CourierReviewRepository courierReviewRepository;

    @Autowired
    CourierReviewRepositoryH2Test(CourierReviewRepository courierReviewRepository) {
        this.courierReviewRepository = courierReviewRepository;
    }

    @Test
    public void findReviewsByCourierId() {
        Optional<CourierReview> courierReviews = courierReviewRepository.findById(1L);
        assertTrue(courierReviews.isPresent());
        log.info("Find reviews by courier id...");
        log.info(courierReviews.get().toString());
    }

    @Test
    public void findReviewsByCourierIdNotFound(){
        Optional<CourierReview> courierReviews = courierReviewRepository.findById(100L);
        assertEquals(false,  courierReviews.isPresent());
        log.info("Find reviews by courier id - Not Found...");
        log.info("Reviews not found: " + courierReviews.isEmpty());

    }

    @Test
    public void findReviewsByClientAndCourierAndOrder(){
        Optional<CourierReview> courierReviews = courierReviewRepository.findByClientIdAndCourierIdAndOrderId(1L, 1L, 1L);
        assertTrue(courierReviews.isPresent());
        log.info("Find reviews by client, courier and order...");
        log.info(courierReviews.get().toString());
    }

    @Test
    public void findReviewsByClientAndCourierAndOrderNotFound(){
        Optional<CourierReview> courierReviews = courierReviewRepository.findByClientIdAndCourierIdAndOrderId(100L, 1L, 1L);
        assertEquals(false,  courierReviews.isPresent());
        log.info("Find reviews by client, courier and order - Not Found...");
        log.info("Reviews not found: " + courierReviews.isEmpty());

    }

}
