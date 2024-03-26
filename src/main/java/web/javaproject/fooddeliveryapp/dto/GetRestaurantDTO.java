package web.javaproject.fooddeliveryapp.dto;

import java.util.List;

public class GetRestaurantDTO extends CreateRestaurantDTO {
    private List<DishDTO> dishes;

    public GetRestaurantDTO() {
    }

    public GetRestaurantDTO(String name, String address, String email, String openingHours, List<DishDTO> dishes) {
        super(name, address, email, openingHours);
        this.dishes = dishes;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }
}
