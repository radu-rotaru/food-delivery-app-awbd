package web.javaproject.fooddeliveryapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import web.javaproject.fooddeliveryapp.dto.ClientDTO;
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