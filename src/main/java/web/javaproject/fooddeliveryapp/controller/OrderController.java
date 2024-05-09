package web.javaproject.fooddeliveryapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateOrderDTO;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.service.ClientService;
import web.javaproject.fooddeliveryapp.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {


    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final ClientService clientService;

    public OrderController(OrderService orderService, OrderMapper orderMapper, ClientService clientService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.clientService = clientService;
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
    public String getOrderById(@PathVariable Long orderId, Model model) {
        try {
            Order order = orderService.getOrder(orderId);
            OrderDTO orderDTO = orderMapper.toDTO(order);

            model.addAttribute("order", orderDTO);
            model.addAttribute("orderId", order.getId());

            return "orderView";
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
        }
    }

    @GetMapping("/all")
    public String getAllOrdersForClient(@RequestParam(required = true) Long clientId, @RequestParam(required = false) String status, Model model) {
        try {
            Client client = clientService.getClient(clientId);
            List<Order> orders = orderService.getAllOrders(clientId, status);
            List<OrderDTO> orderDTOs = orderMapper.toDTOsList(orders);

            System.out.println(orders.get(0).getRestaurant().getName());
            System.out.println(orderDTOs.get(0).getRestaurant().getName());

            model.addAttribute("orders", orderDTOs);
            model.addAttribute("client", client);

            return "clientOrdersList";
         } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
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

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting order: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
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
