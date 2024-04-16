package web.javaproject.fooddeliveryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;


    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> create(@RequestBody CreateOrderDTO createOrderDTO) {
//        try {
//            Order order = orderService.createOrder(createOrderDTO);
//            OrderDTO orderDTO = orderMapper.toDTO(order);
//
//            return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
}