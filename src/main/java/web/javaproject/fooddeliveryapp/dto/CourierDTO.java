package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CourierDTO {
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits")
    private String phoneNumber;

    public CourierDTO() {
    }

    public CourierDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public CourierDTO(String name, String phoneNumber, Long id) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    public Long getId() {return this.id; };
    public void setId(Long id) { this.id = id;}
}
