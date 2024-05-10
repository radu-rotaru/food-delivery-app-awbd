package web.javaproject.fooddeliveryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @RequestMapping({"","/","/home"})
    public ModelAndView getHome(){
        return new ModelAndView("main");
    }

    @GetMapping("/login")
    public String showLogInForm(){ return "login"; }

    @GetMapping("/register")
    public String showRegisterForm(){ return "register"; }

    @GetMapping("/access_denied")
    public String accessDeniedPage(){ return "access_denied"; }
}