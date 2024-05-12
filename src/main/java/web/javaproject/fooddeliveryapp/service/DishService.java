package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.exception.DishDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface DishService {
    public Dish getDish(Long dishId);
    public List<Dish> getDishes(List<Long> dishIds);
    public Dish updateDish(Dish dish);
    public Dish createDish(CreateDishDTO createDishDTO);
    public List<Dish> getDishesByRestaurantId(Long restaurantId);
    public void deleteDish(Long id);
    public DishDTO save (DishDTO dishDTO);
    void deleteById(Long id);
}