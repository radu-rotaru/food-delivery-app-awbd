package web.javaproject.fooddeliveryapp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.javaproject.fooddeliveryapp.dto.CreateRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.GetRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Restaurant;

@Component
public class RestaurantMapper {
    private final DishMapper dishMapper;

    @Autowired
    public RestaurantMapper(DishMapper dishMapper) {
        this.dishMapper = dishMapper;
    }

    public Restaurant createDTOtoEntity(CreateRestaurantDTO createRestaurantDTO) {
        return new Restaurant(
            createRestaurantDTO.getName(),
            createRestaurantDTO.getEmail(),
            createRestaurantDTO.getAddress(),
            createRestaurantDTO.getOpeningHours()
        );
    }

    public RestaurantDTO toDTO(Restaurant restaurant) {
        return new RestaurantDTO(
            restaurant.getName(),
            restaurant.getAddress()
        );
    }

    public GetRestaurantDTO toGetDTO(Restaurant restaurant) {
        return new GetRestaurantDTO(
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getEmail(),
            restaurant.getOpeningHours(),
            dishMapper.toDTOsList(restaurant.getDishes())
        );
    }

    public CreateRestaurantDTO toCreateDTO(Restaurant restaurant) {
        return new CreateRestaurantDTO(
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getEmail(),
            restaurant.getOpeningHours()
        );
    }
}
