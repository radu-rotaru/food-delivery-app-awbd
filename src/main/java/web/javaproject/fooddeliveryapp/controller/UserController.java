package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.javaproject.fooddeliveryapp.dto.UserDto;
import web.javaproject.fooddeliveryapp.mapper.UserMapper;
import web.javaproject.fooddeliveryapp.model.security.User;
import web.javaproject.fooddeliveryapp.service.UserService;

import java.util.Objects;

@Controller
@RequestMapping("")
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        if (Objects.equals(user.getRole(), "client"))
            return "redirect:/client/form";
        if (Objects.equals(user.getRole(), "courier"))
            return "redirect:/courier/form";
        if (Objects.equals(user.getRole(), "restaurant"))
            return "redirect:/restaurant/form";
        return "redirect:/register";

    }

}
