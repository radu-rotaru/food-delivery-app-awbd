package web.javaproject.fooddeliveryapp.dto;

import web.javaproject.fooddeliveryapp.model.security.Authority;

import java.util.Set;

public class UserDto {
    private String username;
    private String password;
    private String role;

    public UserDto() {}

    public UserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}

}
