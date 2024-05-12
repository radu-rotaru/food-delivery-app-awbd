package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class RestaurantRepositoryH2Test {
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantRepositoryH2Test(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Test
    public void findRestaurantsByOpeningHours(){
        List<Restaurant> restaurants = restaurantRepository.findByOpeningHours("10-23");
        assertTrue(restaurants.size() >= 1);
        log.info("Find by opening hours ....");
        restaurants.forEach(restaurant -> log.info(restaurant.getName()));
    }

    @Test
    public void findRestaurantsByOpeningHoursNotFound(){
        List<Restaurant> restaurants = restaurantRepository.findByOpeningHours("10-11");
        assertEquals(0, restaurants.size());
        log.info("Find by opening hours - Not Found....");
        log.info("Restaurants not found: " + restaurants.isEmpty());
    }

    @Test
    public void findRestaurantByEmail(){
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail("herestrau@test.com");
        assertTrue(restaurant.isPresent());
        log.info("Find by email ....");
        log.info(restaurant.get().getName());
    }

    @Test
    public void findRestaurantByEmailNotFound(){
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail("notexists@test.com");
        assertEquals(false, restaurant.isPresent());
        log.info("Find by email - Not Found....");
        log.info("Restaurant not found: " + restaurant.isEmpty());
    }

    @Test
    public void findRestaurantById(){
        Optional<Restaurant> restaurant = restaurantRepository.findById(1L);
        assertTrue(restaurant.isPresent());
        log.info("Find by id....");
        log.info(restaurant.get().getName());
    }
    @Test
    public void findRestaurantByIdNotFound(){
        Optional<Restaurant> restaurant = restaurantRepository.findById(1000L);
        assertEquals(false, restaurant.isPresent());
        log.info("Find by id - Not Found....");
        log.info("Restaurant not found: " + restaurant.isEmpty());
    }



}
