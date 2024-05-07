package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DishDTO {
    @NotBlank
    private String name;
    @Positive(message = "Quantity must be positive")
    private int quantity;
    @Positive(message = "Price must be positive")
    private float price;

    public DishDTO() {
    }

    public DishDTO(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
