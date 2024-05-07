package web.javaproject.fooddeliveryapp.dto;

import lombok.Data;

@Data
public class CreateRestaurantDTO extends RestaurantDTO {
    private String email;
    private String openingHours;

    public CreateRestaurantDTO() {
    }

    public CreateRestaurantDTO(String name, String address, String email, String openingHours) {
        super(name, address, openingHours);
        this.email = email;
    }
}
