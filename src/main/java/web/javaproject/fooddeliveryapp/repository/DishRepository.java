package web.javaproject.fooddeliveryapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.javaproject.fooddeliveryapp.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
