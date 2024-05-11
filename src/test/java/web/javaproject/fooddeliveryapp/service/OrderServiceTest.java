package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.dto.UpdateOrderDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.model.*;
import web.javaproject.fooddeliveryapp.repository.CourierRepository;
import web.javaproject.fooddeliveryapp.repository.OrderRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CourierRepository courierRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private CourierService courierService;

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private DishService dishService;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void createOrder_Success() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = new Courier("John", true, "0770222222");
        Dish dish1 = Mockito.mock(Dish.class);
        Dish dish2 = Mockito.mock(Dish.class);
        List<Dish> dishes = new ArrayList<>(Arrays.asList(dish1, dish2));

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(client);
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(restaurant);
        when(dishService.getDish(1L)).thenReturn(dish1);
        when(dishService.getDish(2L)).thenReturn(dish2);
        when(orderRepository.save(any(Order.class))).thenReturn(new Order(restaurant, client, courier, dishes, "processed"));
        when(courierService.findAvailable()).thenReturn(courier);
        when(dish1.getRestaurant()).thenReturn(restaurant);
        when(dish2.getRestaurant()).thenReturn(restaurant);
        when(restaurant.getId()).thenReturn(createOrderDTO.getRestaurantId());

        Order order = orderService.createOrder(createOrderDTO);

        assertNotNull(order);
        verify(orderRepository, times(1)).save(any(Order.class));
        assertThat(order.getCourier()).isEqualTo(courier);
        assertThat(order.getCourier().isAvailable()).isEqualTo(false);
        assertThat(order.getClient()).isEqualTo(client);
        assertThat(order.getRestaurant()).isEqualTo(restaurant);
        assertThat(order.getDishes()).containsExactlyInAnyOrder(dish1, dish2);
    }

    @Test
    void createOrder_ClientDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        when(clientService.getClient(createOrderDTO.getClientId())).thenThrow(ClientDoesNotExistException.class);

        assertThrows(ClientDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_RestaurantDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(client);
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenThrow(RestaurantDoesNotExistException.class);

        assertThrows(RestaurantDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_CourierNotAvailable() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(client);
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(restaurant);
        when(courierService.findAvailable()).thenThrow(CourierNotAvailableException.class);

        assertThrows(CourierNotAvailableException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_DishDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(client);
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(restaurant);
        when(courierService.findAvailable()).thenReturn(courier);
        when(dishService.getDish(createOrderDTO.getDishesIds().get(0))).thenThrow(new DishDoesNotExistException());

        assertThrows(DishDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_DishNotOnMenu() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, new ArrayList<>(Arrays.asList(1L)));
        Long dishRestaurantId = 2L;

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);
        Dish dish = Mockito.mock(Dish.class);
        Restaurant dishRestaurant = Mockito.mock(Restaurant.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(client);
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(restaurant);
        when(dishService.getDish(createOrderDTO.getDishesIds().get(0))).thenReturn(dish);
        when(courierService.findAvailable()).thenReturn(courier);
        when(dish.getRestaurant()).thenReturn(dishRestaurant);
        when(dishRestaurant.getId()).thenReturn(dishRestaurantId);

        assertThrows(DishNotOnMenuException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    public void testUpdateOrder_StatusUpdate() {
        Long orderId = 1L;
        UpdateOrderDTO updateOrderDTO = new UpdateOrderDTO();
        updateOrderDTO.setStatus("delivered");
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order updatedOrder = orderService.updateOrder(orderId, updateOrderDTO);

        assertEquals("delivered", updatedOrder.getStatus());
    }

    @Test
    public void testDeleteOrder_Success() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.deleteOrder(orderId);

        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    public void testDeleteOrder_OrderDoesNotExist() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderDoesNotExistException.class, () -> orderService.deleteOrder(orderId));
    }

    @Test
    public void testGetOrder_Success() {
        Long orderId = 1L;
        Order order = new Order();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        Order retrievedOrder = orderService.getOrder(orderId);

        assertEquals(order, retrievedOrder);
    }

    @Test
    public void testGetOrder_NotFound() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderDoesNotExistException.class, () -> orderService.getOrder(orderId));
    }

    @Test
    public void testGetAllOrdersForClient_Success() {
        Long clientId = 1L;
        String status = "pending";
        List<Order> orders = new ArrayList<>();
        when(clientService.getClient(clientId)).thenReturn(new Client());
        when(orderRepository.findByClientIdAndStatus(clientId, status)).thenReturn(orders);

        List<Order> retrievedOrders = orderService.getAllOrders(clientId, status);

        assertEquals(orders, retrievedOrders);
    }

    @Test
    public void testGetAllOrdersForClient_ClientDoesNotExist() {
        Long clientId = 1L;
        when(clientService.getClient(clientId)).thenThrow(ClientDoesNotExistException.class);

        assertThrows(ClientDoesNotExistException.class, () -> orderService.getAllOrders(clientId, null));
    }
}
