package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.mapper.UserMapper;
import web.javaproject.fooddeliveryapp.model.security.Authority;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    UserMapper userMapper;
    UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserMapper userMapper, PasswordEncoder passwordEncoder, UserService userService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String add (@Valid @ModelAttribute UserDto user){
        User newUser = userMapper.toUser(user);
        newUser.setEnabled(true);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Authority authority = user.getAuthority();
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        newUser.setAuthorities(authorities);

       // userService.save(user);
        if (user.getAuthority().getRole() == "CLIENT")
            return "redirect:/client/form";
        if (user.getAuthority().getRole() == "COURIER")
            return "redirect:/courier/form";
        if (user.getAuthority().getRole() == "RESTAURANT")
            return "redirect:/restaurant/form";
        return "redirect:/";

    }

}
