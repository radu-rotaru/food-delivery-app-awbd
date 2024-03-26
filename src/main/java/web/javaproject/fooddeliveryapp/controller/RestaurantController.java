package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CreateRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.GetRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.mapper.RestaurantMapper;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.service.RestaurantService;
import web.javaproject.fooddeliveryapp.util.ValidationCheck;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    private final RestaurantMapper restaurantMapper;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getByEmail(@RequestParam String restaurantEmail) {
        try {
            Restaurant restaurant = restaurantService.getRestaurantByEmail(restaurantEmail);
            GetRestaurantDTO restaurantDTO = restaurantMapper.toGetDTO(restaurant);

            return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving restaurant: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateRestaurantDTO createRestaurantDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            Restaurant restaurant = restaurantMapper.createDTOtoEntity(createRestaurantDTO);
            Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
            RestaurantDTO createdRestaurantDTO = restaurantMapper.toCreateDTO(createdRestaurant);

            return new ResponseEntity<>(createdRestaurantDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating client: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
