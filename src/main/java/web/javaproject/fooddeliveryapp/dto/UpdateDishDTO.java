package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class UpdateDishDTO extends DishDTO {
    private Long id;

    public UpdateDishDTO() {
    }

    public UpdateDishDTO(String name, int quantity, float price,  Long id) {
        super(name, quantity, price);
        this.id = id;
    }
}
