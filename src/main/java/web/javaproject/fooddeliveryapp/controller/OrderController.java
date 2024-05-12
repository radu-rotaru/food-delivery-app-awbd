package web.javaproject.fooddeliveryapp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.OrderDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateOrderDTO;
import web.javaproject.fooddeliveryapp.mapper.OrderMapper;
import web.javaproject.fooddeliveryapp.model.Client;
import web.javaproject.fooddeliveryapp.model.Order;
import web.javaproject.fooddeliveryapp.model.security.Authority;
import web.javaproject.fooddeliveryapp.model.security.CustomUserDetails;
import web.javaproject.fooddeliveryapp.service.ClientService;
import web.javaproject.fooddeliveryapp.service.OrderService;
import java.util.Arrays;

import java.util.List;
import java.util.Objects;

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
    public String create(HttpServletRequest request, Model model) {
        try {
            Long restaurantId = Long.parseLong(request.getParameter("restaurantId"));
            Long clientId = Long.parseLong(request.getParameter("clientId"));
            List<Long> dishesIds = Arrays.stream(request.getParameter("dishesIds").split(","))
                    .map(Long::valueOf)
                    .toList();

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
            if(firstAuthority == null || !Objects.equals(firstAuthority.getAuthority(), "CLIENT")) {
                return "access_denied";
            }

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if(!Objects.equals(userDetails.getAssociatedId(), clientId)) {
                return "access_denied";
            }

            CreateOrderDTO createOrderDTO = new CreateOrderDTO(clientId, restaurantId, dishesIds);

            Order order = orderService.createOrder(createOrderDTO);
            OrderDTO orderDTO = orderMapper.toDTO(order);

            model.addAttribute("order", orderDTO);
            model.addAttribute("orderId", order.getId());
            return "orderView";
        } catch (Exception e) {
            model.addAttribute(e.getMessage(), "errorMessage");
            return "errorView";
        }
    }

    @GetMapping("/{orderId}")
    public String getOrderById(@PathVariable Long orderId, Model model) {
        try {
            Order order = orderService.getOrder(orderId);
            OrderDTO orderDTO = orderMapper.toDTO(order);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
            if(firstAuthority == null) {
                return "access_denied";
            }

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            switch (firstAuthority.getAuthority()) {
                case "CLIENT":
                    if(!Objects.equals(userDetails.getAssociatedId(), order.getClient().getId())) {
                        return "access_denied";
                    }
                case "RESTAURANT":
                    if(!Objects.equals(userDetails.getAssociatedId(), order.getRestaurant().getId())) {
                        return "access_denied";
                    }
                case "COURIER":
                    if(!Objects.equals(userDetails.getAssociatedId(), order.getCourier().getId())) {
                        return "access_denied";
                    }
            }

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

            model.addAttribute("orders", orderDTOs);
            model.addAttribute("client", client);

            return "clientOrdersList";
         } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
         }
    }

    @PutMapping("/{orderId}")
    public String updateOrder(@PathVariable Long orderId, @RequestBody UpdateOrderDTO updateOrderDTO, Model model) {
        try {
            Order updatedOrder = orderService.updateOrder(orderId, updateOrderDTO);
            OrderDTO updatedOrderDTO = orderMapper.toDTO(updatedOrder);

            model.addAttribute("order", updatedOrderDTO);
            model.addAttribute("orderId", updatedOrder.getId());

            return "orderView";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
        }
    }

    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId, Model model) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            GrantedAuthority firstAuthority = authentication.getAuthorities().stream().findFirst().orElse(null);
            if(firstAuthority == null) {
                return "access_denied";
            }

            Order order = orderService.getOrder(orderId);
            if(Objects.equals(firstAuthority.getAuthority(), "CLIENT")) {
                CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

                if(!Objects.equals(order.getClient().getId(), userDetails.getAssociatedId())) {
                    return "acess_denied";
                }
            }

            orderService.deleteOrder(orderId);
            return "redirect:/order";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorView";
        }
    }
}

