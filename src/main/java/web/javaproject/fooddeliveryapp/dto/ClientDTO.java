package web.javaproject.fooddeliveryapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Address cannot be blank")
    private String address;

    public ClientDTO() {
    }

    public ClientDTO(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
}