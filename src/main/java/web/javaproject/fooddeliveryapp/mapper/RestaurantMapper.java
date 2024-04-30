package web.javaproject.fooddeliveryapp.mapper;

import org.mapstruct.Mapper;
import web.javaproject.fooddeliveryapp.dto.CreateRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.GetRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Restaurant;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDTO toDto (Restaurant restaurant);

    GetRestaurantDTO toGetDto(Restaurant restaurant);
    Restaurant toRestaurant (RestaurantDTO restaurantDTO);
    Restaurant createDTOtoEntity(CreateRestaurantDTO createRestaurantDTO);
    CreateRestaurantDTO toCreateDto(Restaurant restaurant);
}
