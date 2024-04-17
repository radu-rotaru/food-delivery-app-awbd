package web.javaproject.fooddeliveryapp.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.dto.CreateCourierReviewDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.service.CourierReviewService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CourierReviewControllerTest {
    @Mock
    private CourierReviewService courierReviewService;

    @Mock
    private CourierReviewMapper courierReviewMapper;

    @InjectMocks
    private CourierReviewController courierReviewController;

    @Test
    public void testCreateCourierReview_Success() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 2L, 3L, 5);

        CourierReviewDTO courierReviewDTO = new CourierReviewDTO();
        CourierReview createdCourierReview = new CourierReview();
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO)).thenReturn(createdCourierReview);
        Mockito.when(courierReviewMapper.toDto(createdCourierReview)).thenReturn(courierReviewDTO);

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(courierReviewDTO, responseEntity.getBody());
    }

    @Test
    public void testCreateCourierReview_CourierDoesNotExist() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO))
                .thenThrow(new CourierDoesNotExistException());

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating courier review: The courier does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateCourierReview_OrderDoesNotExist() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO))
                .thenThrow(new OrderDoesNotExistException());

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating courier review: The order does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateCourierReview_ClientDoesNotExist() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO))
                .thenThrow(new ClientDoesNotExistException());

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating courier review: The client does not exist.", responseEntity.getBody());
    }

    @Test
    public void testCreateCourierReview_ReviewAlreadyExists() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO))
                .thenThrow(new CourierReviewAlreadyExistsException());

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating courier review: A review with this specific client, courier and order already exists.", responseEntity.getBody());
    }

    @Test
    public void testCreateCourierReview_OrderIsNotValid() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 4);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(courierReviewService.createCourierReview(createCourierReviewDTO))
                .thenThrow(new OrderIsNotValidException());

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Error creating courier review: Order is not valid for the given client and courier.", responseEntity.getBody());
    }

    @Test
    public void testCreateClient_BiggerNumberOfStars() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 6);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        List<FieldError> fieldErrors = Collections.singletonList(new FieldError("createCourierReviewDTO", "stars", "Stars cannot be more than 5"));
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().toString(), "Validation errors: \nStars cannot be more than 5");
    }

    @Test
    public void testCreateClient_SmallerNumberOfStars() {
        CreateCourierReviewDTO createCourierReviewDTO = new CreateCourierReviewDTO(1L, 1L, 1L, 0);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        List<FieldError> fieldErrors = Collections.singletonList(new FieldError("createCourierReviewDTO", "stars", "Stars should be at least 1"));
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        ResponseEntity<?> responseEntity = courierReviewController.create(createCourierReviewDTO, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(responseEntity.getBody().toString(), "Validation errors: \nStars should be at least 1");
    }
}
