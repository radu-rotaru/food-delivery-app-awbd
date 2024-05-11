package web.javaproject.fooddeliveryapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.exception.DishDoesNotExistException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.service.DishService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishControllerTest {
    @Mock
    private DishService dishService;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishController dishController;

    @Test
    public void testUpdateDish_SuccessfulUpdate() {
        Long dishId = 1L;
        DishDTO updateDishDTO = new DishDTO(dishId, "Updated Dish", 5, 15.99f);
        Dish updatedDish = new Dish(dishId, "Updated Dish", 5, 15.99f);
        DishDTO updatedDishDTO = new DishDTO(dishId, "Updated Dish", 5, 15.99f);

        when(dishMapper.toEntity(updateDishDTO)).thenReturn(updatedDish);
        when(dishService.updateDish(updatedDish)).thenReturn(updatedDish);
        when(dishMapper.toDTO(updatedDish)).thenReturn(updatedDishDTO);

        ResponseEntity<?> responseEntity = dishController.update(dishId, updateDishDTO, mock(BindingResult.class));

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedDishDTO, responseEntity.getBody());
    }

    @Test
    public void testUpdateDish_IdMismatch() {
        Long dishId = 1L;
        DishDTO updateDishDTO = new DishDTO(dishId + 1, "Updated Dish", 5, 15.99f);

        ResponseEntity<?> responseEntity = dishController.update(dishId, updateDishDTO, mock(BindingResult.class));

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Ids must match!", responseEntity.getBody());
    }

    @Test
    public void testUpdateDish_ValidationErrors() {
        Long dishId = 1L;
        DishDTO updateDishDTO = new DishDTO(dishId, "", -5, -15.99f);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("updateDishDTO", "name", "Name cannot be blank"),
                new FieldError("updateDishDTO", "quantity", "Quantity must be positive"),
                new FieldError("updateDishDTO", "price", "Price must be positive")
        ));

        ResponseEntity<?> responseEntity = dishController.update(dishId, updateDishDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Validation errors: \nName cannot be blank\nQuantity must be positive\nPrice must be positive", responseEntity.getBody());
    }

    @Test
    public void testUpdateDish_DishDoesNotExist() {
        Long dishId = 1L;
        DishDTO updateDishDTO = new DishDTO(dishId, "Updated Dish", 5, 15.99f);

        when(dishMapper.toEntity(updateDishDTO)).thenReturn(new Dish(dishId, "Updated Dish", 5, 15.99f));
        when(dishService.updateDish(any())).thenThrow(new DishDoesNotExistException());

        ResponseEntity<?> responseEntity = dishController.update(dishId, updateDishDTO, mock(BindingResult.class));

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error updating dish: The dish does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateDish_Successful() {
        CreateDishDTO createDishDTO = new CreateDishDTO("Pizza Pepes", 5, 15.99f, 1L);

        DishDTO dishDTO = new DishDTO();
        Dish createdDish = new Dish();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(dishService.createDish(createDishDTO)).thenReturn(createdDish);
        Mockito.when(dishMapper.toDTO(createdDish)).thenReturn(dishDTO);

        ResponseEntity<?> responseEntity = dishController.create(createDishDTO, mock(BindingResult.class));

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(dishDTO, responseEntity.getBody());
    }

    @Test
    public void testCreateOrder_RestaurantDoesNotExist() {
        CreateDishDTO createDishDTO = new CreateDishDTO("Pizza Pepes", 5, 15.99f, 1L);

        Mockito.when(dishService.createDish(createDishDTO))
                .thenThrow(new RestaurantDoesNotExistException());

        ResponseEntity<?> responseEntity = dishController.create(createDishDTO, mock(BindingResult.class));

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating dish: The restaurant does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateDish_ValidationErrors() {
        CreateDishDTO createDishDTO = new CreateDishDTO("Pizza Pepes", 5, 15.99f, 1L);
        BindingResult bindingResult = mock(BindingResult.class);

        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("createDishDTO", "name", "Name cannot be blank"),
                new FieldError("createDishDTO", "quantity", "Quantity must be positive"),
                new FieldError("createDishDTO", "price", "Price must be positive")
        ));

        ResponseEntity<?> responseEntity = dishController.create(createDishDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Validation errors: \nName cannot be blank\nQuantity must be positive\nPrice must be positive", responseEntity.getBody());
    }
}
