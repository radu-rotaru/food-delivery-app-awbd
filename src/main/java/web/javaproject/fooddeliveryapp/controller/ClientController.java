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
import web.javaproject.fooddeliveryapp.dto.RestaurantDTO;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Restaurant;
import web.javaproject.fooddeliveryapp.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    public ClientController(ClientService clientService) {

        this.clientService = clientService;
    }

    @RequestMapping("")
        public String clientList(Model model) {
            List<ClientDTO> clients = clientService.findAll();
            model.addAttribute("clients", clients);
            return "clientList";
        }


    @PostMapping("")
    public String saveOrUpdate(@Valid @ModelAttribute ClientDTO client,
                               BindingResult bindingResult,
                               Model model
    ){
        if (bindingResult.hasErrors()){
            model.addAttribute("client", client);
            return "clientForm";
        }

        clientService.save(client);


        return "redirect:/client" ;
    }

    @RequestMapping("/delete/{id}")
    public String deleteById(@PathVariable String id){
        clientService.deleteById(Long.valueOf(id));
        return "redirect:/client";
    }

    @RequestMapping("/form")
    public String restaurantForm(Model model){
        Client client = new Client();
        model.addAttribute("client", client);
        List <ClientDTO> clientsAll = clientService.findAll();
        model.addAttribute("clientsAll", clientsAll);
        return "clientForm";
    }


//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody @Valid ClientDTO clientDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//
//        try {
//            Client clientEntity = clientMapper.toEntity(clientDTO);
//            Client createdClient = clientService.createClient(clientEntity);
//            ClientDTO createdClientDTO = clientMapper.toDTO(createdClient);
//
//            return new ResponseEntity<>(createdClientDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating client: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}