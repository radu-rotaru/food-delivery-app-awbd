package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Order;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByRestaurantId(Long restaurantId);
}
