package web.javaproject.fooddeliveryapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import web.javaproject.fooddeliveryapp.exception.RestaurantAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.RestaurantRepository;

import java.util.Optional;

@Service
@Validated
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Optional<Restaurant> getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public Restaurant getRestaurantByEmail(String email) {
        Optional<Restaurant> restaurant = restaurantRepository.findByEmail(email);

        if(restaurant.isEmpty()) {
            throw new RestaurantDoesNotExistException();
        }

        return restaurant.get();
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findByEmail(restaurant.getEmail());

        if(existingRestaurant.isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }

        return restaurantRepository.save(restaurant);
    }
}