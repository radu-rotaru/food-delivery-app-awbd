package web.javaproject.fooddeliveryapp.dto;

import web.javaproject.fooddeliveryapp.model.security.Authority;

import java.util.Set;

public class UserDto {
    private String username;
    private String password;
    private Authority authority;


    public UserDto(String username, String password, Authority authority) {
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public Authority getAuthority() {return authority;}

    public void setAuthority(Authority authority) {this.authority = authority;}
    public void setUsername(String username) {this.username = username;}
    public void setPassword(String password) {this.password = password;}

}
