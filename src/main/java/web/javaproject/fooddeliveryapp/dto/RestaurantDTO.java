package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;

public class RestaurantDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
