package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Address cannot be blank")
    private String address;

    public ClientDTO() {
    }

    public ClientDTO(String name, String email, String address, Long id) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
}