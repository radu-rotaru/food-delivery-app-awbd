package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;

public class RestaurantDTO {

    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    private String openingHours;

    public RestaurantDTO() {
    }
    public RestaurantDTO(String name, String address, String openingHours, Long id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
    }

    public RestaurantDTO(String name, String address, String openingHours) {
        this.name = name;
        this.address = address;
        this.openingHours = openingHours;
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

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Long getId() {return id; }
    public void setId(Long id) { this.id = id; }

}
