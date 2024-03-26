package web.javaproject.fooddeliveryapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateDishDTO;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DishMapper {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public DishMapper(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public DishDTO toDTO(Dish dish) {
        return new DishDTO(
            dish.getName(),
            dish.getQuantity(),
            dish.getPrice()
        );
    }

    public List<DishDTO> toDTOsList(List<Dish> dishes) {
        List<DishDTO> dishesDTOs = new ArrayList<DishDTO>();

        for (Dish dish :  dishes) {
            dishesDTOs.add(toDTO(dish));
        }

        return dishesDTOs;
    }

    public Dish toEntity(UpdateDishDTO updateDishDTO) {
        return new Dish(
            updateDishDTO.getId(),
            updateDishDTO.getName(),
            updateDishDTO.getQuantity(),
            updateDishDTO.getPrice()
        );
    }

    public Dish createDTOtoEntity(CreateDishDTO createDishDTO) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(createDishDTO.getRestaurantId());

        Restaurant restaurant = new Restaurant();
        if (optionalRestaurant.isPresent()) {
            restaurant = optionalRestaurant.get();
        }

        return new Dish(
            createDishDTO.getName(),
            createDishDTO.getQuantity(),
            createDishDTO.getPrice(),
            restaurant
        );
    }
}
