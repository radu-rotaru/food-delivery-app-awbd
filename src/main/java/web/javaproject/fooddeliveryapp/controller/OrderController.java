package web.javaproject.fooddeliveryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateOrderDTO;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.service.OrderService;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateOrderDTO createOrderDTO) {
        try {
            Order order = orderService.createOrder(createOrderDTO);
            OrderDTO orderDTO = orderMapper.toDTO(order);

            return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable Long orderId) {
        Optional<Order> order = orderService.getOrder(orderId);

        if(order.isPresent()) {
            OrderDTO orderDTO = orderMapper.toDTO(order.get());
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(String.format("Order with id %s not found", orderId), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrdersForClient(@RequestParam(required = true) Long clientId, @RequestParam(required = false) String status) {
        try {
            List<Order> orders = orderService.getAllOrders(clientId, status);
            List<OrderDTO> orderDTOs = orderMapper.toDTOsList(orders);

            return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving orders: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<?> updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderDTO updateOrderDTO) {
        try {
            Order updatedOrder = orderService.updateOrder(orderId, updateOrderDTO);
            OrderDTO updatedOrderDTO = orderMapper.toDTO(updatedOrder);

            return new ResponseEntity<>(updatedOrderDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}