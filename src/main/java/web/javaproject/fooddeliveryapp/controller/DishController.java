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
}
