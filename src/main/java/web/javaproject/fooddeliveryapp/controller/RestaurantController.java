package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.model.security.CustomUserDetails;
import web.javaproject.fooddeliveryapp.service.RestaurantService;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
    RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping("")
    public String restaurantList(Model model) {
        List<RestaurantDTO> restaurants = restaurantService.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurantList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        if(Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(Long.parseLong(id) != userDetails.getAssociatedId()) {
                return "acess_denied";
            }
        }
        model.addAttribute("restaurant", restaurantService.findById(Long.valueOf(id)));
        return "restaurantForm";
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute RestaurantDTO restaurant,
                               BindingResult bindingResult,
                               Model model
    ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        if(Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(restaurant.getId() != userDetails.getAssociatedId()) {
                return "acess_denied";
            }
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("restaurant", restaurant);
            return "restaurantForm";
        }

        restaurantService.save(restaurant);

        return "redirect:/restaurant";
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        if(Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(Long.parseLong(id) != userDetails.getAssociatedId()) {
                return "acess_denied";
            }
        }

        restaurantService.deleteById(Long.valueOf(id));
        return "redirect:/restaurant";
    }

    @RequestMapping("/form")
    public String restaurantForm(Model model){
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }
}
