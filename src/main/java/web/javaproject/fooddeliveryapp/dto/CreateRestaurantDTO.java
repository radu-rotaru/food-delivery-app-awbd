package web.javaproject.fooddeliveryapp.dto;

public class CreateRestaurantDTO extends RestaurantDTO {
    private String email;
    private String openingHours;

    public CreateRestaurantDTO() {
    }

    public CreateRestaurantDTO(String name, String address, String email, String openingHours) {
        super(name, address);
        this.email = email;
        this.openingHours = openingHours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

}
