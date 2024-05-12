package web.javaproject.fooddeliveryapp.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.Dish;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@DataJpaTest
@ActiveProfiles("h2")
@Slf4j
public class DishRepositoryH2Test {
    DishRepository dishRepository;
    @Autowired
    DishRepositoryH2Test(DishRepository dishRepository) { this.dishRepository = dishRepository; }

    @Test
    public void findDishesByRestaurantId(){
        List<Dish> dishes = dishRepository.findByRestaurantId(1L);
        assertTrue(dishes.size() > 0);
        log.info("Find dished by restaurant id...");
        dishes.forEach(dish -> log.info(dish.getName()));
    }

    @Test
    public void findDishesByRestaurantIdNotFound(){
        List<Dish> dishes = dishRepository.findByRestaurantId(200L);
        assertEquals(false, dishes.size() > 0);
        log.info("Find dished by restaurant id - Not Found...");
        log.info(("Dishes not found: " + dishes.isEmpty()));
    }
}
