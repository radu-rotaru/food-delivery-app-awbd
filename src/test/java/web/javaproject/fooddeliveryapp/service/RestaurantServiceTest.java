package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.exception.RestaurantAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.RestaurantRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Test
    public void testGetRestaurantById_ExistingRestaurant() {
        Long restaurantId = 1L;
        Restaurant expectedRestaurant = new Restaurant();

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant result = restaurantService.getRestaurant(restaurantId);

        assertEquals(expectedRestaurant, result);
    }

    @Test
    public void testGetRestaurantById_NonExistingRestaurant() {
        Long restaurantId = 1L;
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantDoesNotExistException.class, () -> restaurantService.getRestaurant(restaurantId));
    }

    @Test
    public void testGetRestaurantByEmail_ExistingRestaurant() {
        String email = "john@gmail.com";
        Restaurant expectedRestaurant = new Restaurant();
        when(restaurantRepository.findByEmail(email)).thenReturn(Optional.of(expectedRestaurant));

        Restaurant result = restaurantService.getRestaurantByEmail(email);

        assertNotNull(result);
        assertEquals(expectedRestaurant, result);
    }

    @Test
    public void testGetRestaurantByEmail_NonExistingRestaurant() {
        String email = "john@gmail.com";
        when(restaurantRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(RestaurantDoesNotExistException.class, () -> restaurantService.getRestaurantByEmail(email));
    }

    @Test
    public void testCreateRestaurant_Success() {
        Restaurant restaurant = new Restaurant("Pepes", "pepes@gmail.com", "William St. 23", "10:00 - 22:00");
        when(restaurantRepository.findByEmail(restaurant.getEmail()))
                .thenReturn(Optional.empty());

        Restaurant savedRestaurant = new Restaurant("Pepes", "pepes@gmail.com", "William St. 23", "10:00 - 22:00");

        when(restaurantRepository.save(restaurant)).thenReturn(savedRestaurant);

        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);

        assertThat(createdRestaurant).isNotNull();
        assertThat(createdRestaurant.getName()).isEqualTo(restaurant.getName());
        assertThat(createdRestaurant.getEmail()).isEqualTo(restaurant.getEmail());
        assertThat(createdRestaurant.getAddress()).isEqualTo(restaurant.getAddress());
        assertThat(createdRestaurant.getOpeningHours()).isEqualTo(restaurant.getOpeningHours());
        assertThat(createdRestaurant.getDishes()).isEqualTo(restaurant.getDishes());
    }

    @Test
    public void testCreateClient_EmailAlreadyExists() {
        Restaurant restaurant = new Restaurant("Pepes", "pepes@gmail.com", "William St. 23", "10:00 - 22:00");
        Restaurant alreadySavedRestaurant = new Restaurant("Pepes2", "pepes@gmail.com", "William St. 23", "10:00 - 22:00");

        when(restaurantRepository.findByEmail(restaurant.getEmail())).thenReturn(Optional.of(alreadySavedRestaurant));

        assertThrows(RestaurantAlreadyExistsException.class, () -> restaurantService.createRestaurant(restaurant));
    }
}
