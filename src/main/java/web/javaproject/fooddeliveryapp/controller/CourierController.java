package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.service.CourierService;

import java.util.List;

@Controller
@RequestMapping("/courier")
public class CourierController {

    CourierService courierService;

    public CourierController(CourierService courierService) {

        this.courierService = courierService;
    }

    @RequestMapping("")
    public String courierList(Model model) {
        List<CourierDTO> couriers = courierService.findAll();
        model.addAttribute("couriers", couriers);
        return "courierList";
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute CourierDTO courier,
                               BindingResult bindingResult,
                               Model model
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("courier", courier);
            return "courierForm";
        }

        courierService.save(courier);


        return "redirect:/courier" ;
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        courierService.deleteById(Long.valueOf(id));
        return "redirect:/courier";
    }

    @RequestMapping("/form")
    public String restaurantForm(Model model){
        Courier courier = new Courier();
        model.addAttribute("courier", courier);
        List <CourierDTO> couriersAll = courierService.findAll();
        model.addAttribute("couriersAll", couriersAll);
        return "courierForm";
    }
}
