package web.javaproject.fooddeliveryapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.javaproject.fooddeliveryapp.dto.CourierReviewDTO;
import web.javaproject.fooddeliveryapp.dto.CreateCourierReviewDTO;
import web.javaproject.fooddeliveryapp.mapper.CourierReviewMapper;
import web.javaproject.fooddeliveryapp.model.CourierReview;
import web.javaproject.fooddeliveryapp.service.CourierReviewService;
import web.javaproject.fooddeliveryapp.util.ValidationCheck;

@RestController
@RequestMapping("/api/courier-reviews")
@Validated
public class CourierReviewController {

    private final CourierReviewService courierReviewService;

    private final CourierReviewMapper courierReviewMapper;

    @Autowired
    public CourierReviewController(CourierReviewService courierReviewService, CourierReviewMapper courierReviewMapper) {
        this.courierReviewService = courierReviewService;
        this.courierReviewMapper = courierReviewMapper;
    }

//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody @Valid CreateCourierReviewDTO createCourierReviewDTO, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            String errorMessage = ValidationCheck.extractValidationErrorMessage(bindingResult);
//            return ResponseEntity.badRequest().body(errorMessage);
//        }
//
//        try {
//            CourierReview courierReview = courierReviewService.createCourierReview(createCourierReviewDTO);
//            CourierReviewDTO courierReviewDTO = courierReviewMapper.toDTO(courierReview);
//
//            return new ResponseEntity<>(courierReviewDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating courier review: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}
