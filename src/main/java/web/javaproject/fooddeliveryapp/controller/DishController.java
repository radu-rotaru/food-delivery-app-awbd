package web.javaproject.fooddeliveryapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.DishDTO;
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.mapper.DishMapper;
import web.javaproject.fooddeliveryapp.mapper.RestaurantMapper;
import web.javaproject.fooddeliveryapp.model.Dish;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.model.security.CustomUserDetails;
import web.javaproject.fooddeliveryapp.service.DishService;
import web.javaproject.fooddeliveryapp.service.RestaurantService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final DishMapper dishMapper;
    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    @Autowired
    public DishController(DishService dishService, DishMapper dishMapper, RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.dishService = dishService;
        this.dishMapper = dishMapper;
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        Dish dish = dishService.getDish(Long.parseLong(id));
        if(Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if(!Objects.equals(dish.getRestaurant().getId(), userDetails.getAssociatedId())) {
                return "access_denied";
            }
        }

        model.addAttribute("dish", dishService.getDish(Long.valueOf(id)));
        return "dishForm";
    }

    @PostMapping("")
    public String saveOrUpdate(HttpServletRequest request, Model model){
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Long restaurantId = Long.parseLong(request.getParameter("restaurantId"));
            String name = request.getParameter("dish_name");
            Float price = Float.parseFloat(request.getParameter("dish_price"));
            int quantity = Integer.parseInt(request.getParameter("dish_quantity"));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
            if (firstAuthority == null) {
                return "access_denied";
            }

            if (Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                if (!Objects.equals(restaurantId, userDetails.getAssociatedId())) {
                    return "access_denied";
                }
            }

            RestaurantDTO restaurantDTO = restaurantMapper.toDto(restaurantService.getRestaurant(restaurantId));
            DishDTO dish = new DishDTO(id, name, quantity, price, restaurantDTO);

            dishService.save(dish);

            return "redirect:/dishes/restaurant/" + dish.getRestaurant().getId();
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
        }
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
        if(firstAuthority == null) {
            return "access_denied";
        }

        Dish dish = dishService.getDish(Long.parseLong(id));
        if(Objects.equals(firstAuthority.getAuthority(), "RESTAURANT")) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            if (!Objects.equals(dish.getRestaurant().getId(), userDetails.getAssociatedId())) {
                return "access_denied";
            }
        }

        dishService.deleteById(Long.valueOf(id));
        return "redirect:/dish";
    }

    @RequestMapping("/form")
    public String dishForm(Model model){
        Dish dish = new Dish();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Restaurant restaurant = restaurantService.getRestaurant(userDetails.getAssociatedId());
        dish.setRestaurant(restaurant);

        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getDishesByRestaurantId(@PathVariable Long restaurantId, Model model) {
        try {
            List<Dish> dishes = dishService.getDishesByRestaurantId(restaurantId);
            List<DishDTO> dishDTOs = dishMapper.toDTOsList(dishes);

            Restaurant restaurant = restaurantService.getRestaurant(restaurantId);

            model.addAttribute("restaurant", restaurant);
            model.addAttribute("dishes", dishDTOs);

            return "restaurantMenu";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());

            return "errorView";
        }
    }
}
