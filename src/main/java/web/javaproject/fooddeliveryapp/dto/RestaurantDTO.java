package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RestaurantDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private String openingHours;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String address, String openingHours) {
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }
}
