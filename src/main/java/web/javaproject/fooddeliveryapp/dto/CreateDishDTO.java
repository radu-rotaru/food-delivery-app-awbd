package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class CreateDishDTO extends DishDTO {
    private Long restaurantId;

    public CreateDishDTO() {
    }

    public CreateDishDTO(String name, int quantity, float price, Long restaurantId) {
        super(name, quantity, price);
    }
}
