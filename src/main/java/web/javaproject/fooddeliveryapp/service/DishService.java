package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.exception.DishDoesNotExistException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.DishRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class DishService {
    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;
    private final DishMapper dishMapper;


    @Autowired
    public DishService(DishRepository dishRepository, RestaurantService restaurantService, DishMapper dishMapper) {
        this.dishRepository = dishRepository;
        this.restaurantService = restaurantService;
        this.dishMapper = dishMapper;
    }

    public Dish getDish(Long dishId) {
        Optional<Dish> dish = dishRepository.findById(dishId);

        if(dish.isEmpty()) {
            throw new DishDoesNotExistException();
        }

        return dish.get();
    }

    public List<Dish> getDishes(List<Long> dishIds) {
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < dishIds.size(); i++) {
            Optional<Dish> dish = dishRepository.findById(dishIds.get(i));

            if(dish.isPresent()) {
                dishes.add(dish.get());
            }
            else {
                throw new DishDoesNotExistException();
            }
        }

        return dishes;
    }

    public Dish updateDish(Dish dish) {
        Optional<Dish> existingDish = dishRepository.findById(dish.getId());

        if(existingDish.isEmpty()) {
            throw new DishDoesNotExistException();
        }

        dish.setRestaurant(existingDish.get().getRestaurant());

        return dishRepository.save(dish);
    }

    public Dish createDish(CreateDishDTO createDishDTO) {
        Restaurant restaurant = restaurantService.getRestaurant(createDishDTO.getRestaurantId());

        Dish dish = dishMapper.createDTOtoEntity(createDishDTO);

        return dishRepository.save(dish);
    }

    public List<Dish> getDishesByRestaurantId(Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

        return dishRepository.findByRestaurantId(restaurantId);
    }

    public void deleteDish(Long id) {
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if (dishOptional.isPresent()) {
            Dish dish = dishOptional.get();
            dishRepository.delete(dish);
        } else {
            throw new DishDoesNotExistException();
        }
    }
//
//    public Dish createDish(CreateDishDTO createDishDTO) {
//        Optional<Restaurant> restaurant = restaurantService.getRestaurant(createDishDTO.getRestaurantId());
//
//        if (restaurant.isEmpty()) {
//            throw new RestaurantDoesNotExistException();
//        }
//
//        Dish dish = dishMapper.createDTOtoEntity(createDishDTO);
//
//        return dishRepository.save(dish);
//    }
}