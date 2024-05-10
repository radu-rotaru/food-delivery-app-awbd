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
import web.javaproject.fooddeliveryapp.dto.CreateRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.GetRestaurantDTO;
import web.javaproject.fooddeliveryapp.exception.RestaurantAlreadyExistsException;
import web.javaproject.fooddeliveryapp.exception.RestaurantDoesNotExistException;
import web.javaproject.fooddeliveryapp.mapper.RestaurantMapper;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.service.RestaurantService;
import web.javaproject.fooddeliveryapp.service.RestaurantServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private RestaurantMapper restaurantMapper;

    @InjectMocks
    private RestaurantController restaurantController;

//    @Test
//    public void testGetRestaurant_ExistingRestaurant() {
//        String restaurantEmail = "example@example.com";
//        Restaurant expectedRestaurant = new Restaurant();
//        GetRestaurantDTO expectedRestaurantDTO = new GetRestaurantDTO();
//
//        when(restaurantService.getRestaurantByEmail(restaurantEmail)).thenReturn(expectedRestaurant);
//        when(restaurantMapper.toGetDto(expectedRestaurant)).thenReturn(expectedRestaurantDTO);
//
//        ResponseEntity<?> responseEntity = restaurantController.getByEmail(restaurantEmail);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(expectedRestaurantDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testGetRestaurant_NonExistingRestaurant() {
//        String restaurantEmail = "nonexistent@example.com";
//
//        when(restaurantService.getRestaurantByEmail(restaurantEmail)).thenThrow(new RestaurantDoesNotExistException());
//
//        ResponseEntity<?> responseEntity = restaurantController.getByEmail(restaurantEmail);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error retrieving restaurant: The restaurant does not exist.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateRestaurant_Success() {
//        CreateRestaurantDTO createRestaurantDTO = new CreateRestaurantDTO("Pepes", "pepes@gmail.com", "123 Main St", "10:00 - 22:00");
//
//        Restaurant mappedRestaurant = new Restaurant();
//        Restaurant createdRestaurant = new Restaurant();
//        CreateRestaurantDTO createdRestaurantDTO = new CreateRestaurantDTO("Pepes", "pepes@gmail.com", "123 Main St", "10:00 - 22:00");
//
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(restaurantMapper.createDTOtoEntity(createRestaurantDTO)).thenReturn(mappedRestaurant);
//        Mockito.when(restaurantService.createRestaurant(mappedRestaurant)).thenReturn(createdRestaurant);
//        Mockito.when(restaurantMapper.toCreateDto(createdRestaurant)).thenReturn(createdRestaurantDTO);
//
//        ResponseEntity<?> responseEntity = restaurantController.create(createRestaurantDTO, bindingResult);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(createdRestaurantDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateRestaurant_RestaurantAlreadyExists() {
//        CreateRestaurantDTO createRestaurantDTO = new CreateRestaurantDTO("Pepes", "pepes@gmail.com", "123 Main St", "10:00 - 22:00");
//        Restaurant mappedRestaurant = new Restaurant("Pepes", "pepes@gmail.com", "123 Main St", "10:00 - 22:00");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(restaurantMapper.createDTOtoEntity(createRestaurantDTO)).thenReturn(mappedRestaurant);
//        Mockito.when(restaurantService.createRestaurant(mappedRestaurant)).thenThrow(new RestaurantAlreadyExistsException());
//
//        ResponseEntity<?> responseEntity = restaurantController.create(createRestaurantDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating client: A restaurant with the same email already exists.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateRestaurant_ValidationErrors() {
//        CreateRestaurantDTO createRestaurantDTO = new CreateRestaurantDTO("", "", "123 Main St", "");
//        BindingResult bindingResult = mock(BindingResult.class);
//
//        when(bindingResult.hasErrors()).thenReturn(true);
//        when(bindingResult.getFieldErrors()).thenReturn(List.of(
//                new FieldError("createRestaurantDTO", "name", "Name cannot be blank"),
//                new FieldError("createRestaurantDTO", "address", "Address cannot be blank"),
//                new FieldError("createRestaurantDTO", "email", "Invalid email format")
//        ));
//
//        ResponseEntity<?> responseEntity = restaurantController.create(createRestaurantDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Validation errors: \nName cannot be blank\nAddress cannot be blank\nInvalid email format", responseEntity.getBody());
//    }
}
