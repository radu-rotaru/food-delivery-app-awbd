package web.javaproject.fooddeliveryapp.service;

import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    public Optional<Restaurant> getRestaurant(Long restaurantId);
    public Restaurant getRestaurantByEmail(String email);
    public Restaurant createRestaurant(Restaurant restaurant);
}
