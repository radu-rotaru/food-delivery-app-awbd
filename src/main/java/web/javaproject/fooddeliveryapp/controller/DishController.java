package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CreateDishDTO;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateDishDTO;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.service.DishService;
import web.javaproject.fooddeliveryapp.util.ValidationCheck;

import java.util.List;

@RestController
@RequestMapping("/api/dishes")
public class DishController {
    private final DishService dishService;
    private final DishMapper dishMapper;

    @Autowired
    public DishController(DishService dishService, DishMapper dishMapper) {
        this.dishService = dishService;
        this.dishMapper = dishMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDishById(@PathVariable Long id) {
        try {
            Dish dish = dishService.getDish(id);
            DishDTO dishDTO = dishMapper.toDTO(dish);

            return new ResponseEntity<>(dishDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving dish: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateDishDTO createDishDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            Dish createdDish = dishService.createDish(createDishDTO);
            DishDTO createdDishDTO = dishMapper.toDTO(createdDish);

            return new ResponseEntity<>(createdDishDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating dish: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UpdateDishDTO updateDishDTO, BindingResult bindingResult) {
        if (id != updateDishDTO.getId()) {
            return  ResponseEntity.badRequest().body("Ids must match!");
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            Dish dish = dishMapper.toEntity(updateDishDTO);
            Dish updatedDish = dishService.updateDish(dish);
            DishDTO updatedDishDTO = dishMapper.toDTO(updatedDish);

            return new ResponseEntity<>(updatedDishDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating dish: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<?> getDishesByRestaurantId(@PathVariable Long restaurantId) {
        try {
            List<Dish> dishes = dishService.getDishesByRestaurantId(restaurantId);
            List<DishDTO> dishDTOs = dishMapper.toDTOsList(dishes);

            return new ResponseEntity<>(dishDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving dishes: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        try {
            dishService.deleteDish(id);
            return new ResponseEntity<>("Dish deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting dish: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody @Valid CreateDishDTO createDishDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//
//        try {
//            Dish createdDish = dishService.createDish(createDishDTO);
//            DishDTO createdDishDTO = dishMapper.toDTO(createdDish);
//
//            return new ResponseEntity<>(createdDishDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating dish: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
