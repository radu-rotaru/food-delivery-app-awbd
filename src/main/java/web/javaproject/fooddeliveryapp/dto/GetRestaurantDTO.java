package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetRestaurantDTO extends CreateRestaurantDTO {
    private List<DishDTO> dishes;

    public GetRestaurantDTO() {
    }

    public GetRestaurantDTO(String name, String address, String email, String openingHours, List<DishDTO> dishes) {
        super(name, address, email, openingHours);
        this.dishes = dishes;
    }
}
