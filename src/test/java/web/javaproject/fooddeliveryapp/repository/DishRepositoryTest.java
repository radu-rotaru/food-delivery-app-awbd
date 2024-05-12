package web.javaproject.fooddeliveryapp.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import web.javaproject.fooddeliveryapp.model.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("mysql")
public class DishRepositoryTest {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Test
    public void updatePrice(){
        Optional<Dish> dishOpt = dishRepository.findById(1L);
        Dish dish =dishOpt.get();
        dish.setPrice(40);

        dishRepository.save(dish);

        dishOpt = dishRepository.findById(1L);
        dish = dishOpt.get();
        Assertions.assertEquals(40, dish.getPrice());

    }

    @Test
    public void deleteDish() {
        dishRepository.deleteById(1L);
        Optional<Dish>  dish = dishRepository.findById(1L);
        assertTrue(dish.isEmpty());

    }

}
