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
import web.javaproject.fooddeliveryapp.mapper.ClientMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.service.ClientService;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    ClientMapper clientMapper;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {

        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @RequestMapping("")
        public String clientList(Model model) {
            List<ClientDTO> clients = clientService.findAll();
            model.addAttribute("clients", clients);
            return "clientList";
        }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("client", clientService.findById(Long.valueOf(id)));
        return "clientForm";
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
    public String clientForm(Model model){
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