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

}