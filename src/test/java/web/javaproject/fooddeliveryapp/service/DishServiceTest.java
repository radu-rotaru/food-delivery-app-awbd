package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.exception.DishDoesNotExistException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.repository.DishRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {
    @Mock
    private DishRepository dishRepository;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishService dishService;

    @Test
    public void testGetDish_ExistingDish() {
        Long dishId = 1L;
        Dish expectedDish = new Dish();
        when(dishRepository.findById(dishId)).thenReturn(Optional.of(expectedDish));

        Dish result = dishService.getDish(dishId);

        assertEquals(expectedDish, result);
    }

    @Test
    public void testGetDish_NonExistingDish() {
        Long dishId = 1L;
        when(dishRepository.findById(dishId)).thenReturn(Optional.empty());

        assertThrows(DishDoesNotExistException.class, () -> dishService.getDish(dishId));
    }

    @Test
    public void testUpdateDish_ExistingDish() {
        Dish existingDish = new Dish(1L, "Pizza Pepes", 200, 10.99f);
        Dish updatedDish = new Dish(1L, "Pizza Pepes Xtra", 300, 12.99f);
        when(dishRepository.findById(existingDish.getId())).thenReturn(Optional.of(existingDish));
        when(dishRepository.save(updatedDish)).thenReturn(updatedDish);

        Dish result = dishService.updateDish(updatedDish);

        assertEquals(updatedDish, result);
    }

    @Test
    public void testUpdateDish_NonExistingDish() {
        Dish nonExistingDish = new Dish(1L, "Pizza Calzone", 200, 9.99f);
        when(dishRepository.findById(nonExistingDish.getId())).thenReturn(Optional.empty());

        assertThrows(DishDoesNotExistException.class, () -> dishService.updateDish(nonExistingDish));
    }

    @Test
    public void createDish_Success() {
        CreateDishDTO createDishDTO = new CreateDishDTO("Pizza Pepes", 200, 10.99f, 1L);

        Restaurant restaurant = Mockito.mock(Restaurant.class);

        when(restaurantService.getRestaurant(createDishDTO.getRestaurantId())).thenReturn(restaurant);
        when(dishRepository.save(any(Dish.class))).thenReturn(new Dish("Pizza Pepes", 200, 10.99f, restaurant));
        when(restaurant.getId()).thenReturn(createDishDTO.getRestaurantId());
        when(dishMapper.createDTOtoEntity(createDishDTO)).thenReturn(new Dish());

        Dish dish = dishService.createDish(createDishDTO);

        assertNotNull(dish);
        verify(dishRepository, times(1)).save(any(Dish.class));
        assertThat(dish.getName()).isEqualTo(createDishDTO.getName());
        assertThat(dish.getQuantity()).isEqualTo(createDishDTO.getQuantity());
        assertThat(dish.getPrice()).isEqualTo(createDishDTO.getPrice());
        assertThat(dish.getRestaurant().getId()).isEqualTo(createDishDTO.getRestaurantId());
    }

    @Test
    void createDish_RestaurantDoesNotExist() {
        CreateDishDTO createDishDTO = new CreateDishDTO("Pizza Pepes", 200, 10.99f, 1L);
        Restaurant restaurant = Mockito.mock(Restaurant.class);

        when(restaurantService.getRestaurant(createDishDTO.getRestaurantId())).thenThrow(RestaurantDoesNotExistException.class);

        assertThrows(RestaurantDoesNotExistException.class,
                () -> dishService.createDish(createDishDTO));
    }
}
