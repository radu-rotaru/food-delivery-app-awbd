//package web.javaproject.fooddeliveryapp.controller;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import web.javaproject.fooddeliveryapp.dto.ClientDTO;
//import web.javaproject.fooddeliveryapp.exception.ClientAlreadyExistsException;
//import web.javaproject.fooddeliveryapp.mapper.ClientMapper;
//import web.javaproject.fooddeliveryapp.model.Client;
//import web.javaproject.fooddeliveryapp.service.ClientService;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//public class ClientControllerTest {
//
//    @Mock
//    private ClientService clientService;
//
//    @Mock
//    private ClientMapper clientMapper;
//
//    @InjectMocks
//    private ClientController clientController;
//
//    @Test
//    public void testCreateClient_Success() {
//        ClientDTO clientDTO = new ClientDTO("John Doe", "john.doe@example.com", "123 Main St");
//        Client mappedClient = new Client();
//        Client createdClient = new Client();
//        ClientDTO createdClientDTO = new ClientDTO("John Doe", "john.doe@example.com", "123 Main St");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(clientMapper.toEntity(clientDTO)).thenReturn(mappedClient);
//        Mockito.when(clientService.createClient(mappedClient)).thenReturn(createdClient);
//        Mockito.when(clientMapper.toDTO(createdClient)).thenReturn(createdClientDTO);
//
//        ResponseEntity<?> responseEntity = clientController.create(clientDTO, bindingResult);
//
//        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//        assertEquals(createdClientDTO, responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateClient_ClientAlreadyExists() {
//        ClientDTO clientDTO = new ClientDTO("John Doe", "john.doe@example.com", "123 Main St");
//        Client mappedClient = new Client("John Doe", "john.doe@example.com", "123 Main St");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(clientMapper.toEntity(clientDTO)).thenReturn(mappedClient);
//        Mockito.when(clientService.createClient(mappedClient)).thenThrow(new ClientAlreadyExistsException());
//
//        ResponseEntity<?> responseEntity = clientController.create(clientDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals("Error creating client: A client with the same email already exists.", responseEntity.getBody());
//    }
//
//    @Test
//    public void testCreateClient_InvalidEmailFormat() {
//        ClientDTO invalidEmailClientDTO = new ClientDTO("John Doe", "invalid_email", "123 Main St");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
//        List<FieldError> fieldErrors = Collections.singletonList(new FieldError("clientCreateDTO", "email", "Invalid email format"));
//        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//
//        ResponseEntity<?> responseEntity = clientController.create(invalidEmailClientDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(responseEntity.getBody().toString(), "Validation errors: \nInvalid email format");
//    }
//
//    @Test
//    public void testCreateClient_BlankName() {
//        ClientDTO invalidEmailClientDTO = new ClientDTO("", "johndoe@gmail.com", "123 Main St");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
//        List<FieldError> fieldErrors = Collections.singletonList(new FieldError("clientCreateDTO", "name", "Name cannot be blank"));
//        MethodArgumentNotValidException e = Mockito.mock(MethodArgumentNotValidException.class);
//
//        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//
//        ResponseEntity<?> responseEntity = clientController.create(invalidEmailClientDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(responseEntity.getBody().toString(), "Validation errors: \nName cannot be blank");
//    }
//
//    @Test
//    public void testCreateClient_BlankAddress() {
//        ClientDTO invalidEmailClientDTO = new ClientDTO("John", "johndoe@gmail.com", "");
//        BindingResult bindingResult = Mockito.mock(BindingResult.class);
//
//        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
//        List<FieldError> fieldErrors = Collections.singletonList(new FieldError("clientCreateDTO", "name", "Address cannot be blank"));
//        MethodArgumentNotValidException e = Mockito.mock(MethodArgumentNotValidException.class);
//
//        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
//
//        ResponseEntity<?> responseEntity = clientController.create(invalidEmailClientDTO, bindingResult);
//
//        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//        assertEquals(responseEntity.getBody().toString(), "Validation errors: \nAddress cannot be blank");
//    }
//}
