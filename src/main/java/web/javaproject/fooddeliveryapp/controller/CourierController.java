package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.javaproject.fooddeliveryapp.dto.CourierDTO;
import web.javaproject.fooddeliveryapp.mapper.CourierMapper;
import web.javaproject.fooddeliveryapp.model.Courier;
import web.javaproject.fooddeliveryapp.service.CourierService;

import java.util.List;

@Controller
@RequestMapping("/courier")
public class CourierController {

    CourierService courierService;

    CourierMapper courierMapper;

    public CourierController(CourierMapper courierMapper, CourierService courierService) {

        this.courierService = courierService;
        this.courierMapper = courierMapper;
    }

    @RequestMapping("")
    public String courierList(Model model) {
        List<CourierDTO> couriers = courierService.findAll();
        model.addAttribute("couriers", couriers);
        return "courierList";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("courier", courierService.findById(Long.valueOf(id)));
        return "courierForm";
    }

    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute CourierDTO courier,
                               BindingResult bindingResult,
                               Model model
    ){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
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
    public String courierForm(Model model){
        Courier courier = new Courier();
        model.addAttribute("courier", courier);
        List <CourierDTO> couriersAll = courierService.findAll();
        model.addAttribute("couriersAll", couriersAll);
        return "courierForm";
    }
}
