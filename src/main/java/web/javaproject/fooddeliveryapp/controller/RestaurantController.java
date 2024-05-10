package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.javaproject.fooddeliveryapp.dto.CreateRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.GetRestaurantDTO;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.mapper.RestaurantMapper;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.service.RestaurantService;
import web.javaproject.fooddeliveryapp.util.ValidationCheck;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    RestaurantService restaurantService;

    RestaurantMapper restaurantMapper;

    public RestaurantController(RestaurantMapper restaurantMapper, RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @RequestMapping("")
    public String restaurantList(Model model) {
        List<RestaurantDTO> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurantList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("restaurant", restaurantService.findById(Long.valueOf(id)));
        return "restaurantForm";
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute RestaurantDTO restaurant,
                               BindingResult bindingResult,
                               Model model
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("restaurant", restaurant);
            return "restaurantForm";
        }

        restaurantService.save(restaurant);


        return "redirect:/restaurant" ;
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        restaurantService.deleteById(Long.valueOf(id));
        return "redirect:/restaurant";
    }

    @RequestMapping("/form")
    public String restaurantForm(Model model){
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        List <RestaurantDTO> restaurantsAll = restaurantService.findAll();
        model.addAttribute("restaurantsAll", restaurantsAll);
        return "restaurantForm";
    }






//    @GetMapping("/get")
//    public ResponseEntity<?> getByEmail(@RequestParam String restaurantEmail) {
//        try {
//            Restaurant restaurant = restaurantService.getRestaurantByEmail(restaurantEmail);
//            GetRestaurantDTO restaurantDTO = restaurantMapper.toGetDto(restaurant);
//
//            return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error retrieving restaurant: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody @Valid CreateRestaurantDTO createRestaurantDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//
//        try {
//            Restaurant restaurant = restaurantMapper.createDTOtoEntity(createRestaurantDTO);
//            Restaurant createdRestaurant = restaurantService.createRestaurant(restaurant);
//            RestaurantDTO createdRestaurantDTO = restaurantMapper.toCreateDto(createdRestaurant);
//
//            return new ResponseEntity<>(createdRestaurantDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating client: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
