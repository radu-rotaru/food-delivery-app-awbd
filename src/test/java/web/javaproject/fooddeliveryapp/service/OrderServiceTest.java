package web.javaproject.fooddeliveryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import web.javaproject.fooddeliveryapp.dto.CreateOrderDTO;
import web.javaproject.fooddeliveryapp.exception.*;
import web.javaproject.fooddeliveryapp.model.*;
import web.javaproject.fooddeliveryapp.repository.CourierRepository;
import web.javaproject.fooddeliveryapp.repository.OrderRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L, 2L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = new Courier("John", true, "0770222222");
        Dish dish1 = Mockito.mock(Dish.class);
        Dish dish2 = Mockito.mock(Dish.class);
        List<Dish> dishes = new ArrayList<>(Arrays.asList(dish1, dish2));

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(courierService.getCourier(createOrderDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(dishService.getDish(1L)).thenReturn(Optional.of(dish1));
        when(dishService.getDish(2L)).thenReturn(Optional.of(dish2));
        when(orderRepository.save(any(Order.class))).thenReturn(new Order(restaurant, client, courier, dishes));
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
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.empty());

        assertThrows(ClientDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_RestaurantDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.empty());

        assertThrows(RestaurantDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }


    @Test
    void createOrder_CourierDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(courierService.getCourier(createOrderDTO.getCourierId())).thenReturn(Optional.empty());

        assertThrows(CourierDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_CourierNotAvailable() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(courierService.getCourier(createOrderDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(courier.isAvailable()).thenReturn(false);

        assertThrows(CourierNotAvailableException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_DishDoesNotExist() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(courierService.getCourier(createOrderDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(dishService.getDish(createOrderDTO.getDishesIds().get(0))).thenReturn(Optional.empty());
        when(courier.isAvailable()).thenReturn(true);

        assertThrows(DishDoesNotExistException.class,
                () -> orderService.createOrder(createOrderDTO));
    }

    @Test
    void createOrder_DishNotOnMenu() {
        CreateOrderDTO createOrderDTO = new CreateOrderDTO(1L, 1L, 1L, new ArrayList<>(Arrays.asList(1L)));
        Long dishRestaurantId = 2L;

        Client client = Mockito.mock(Client.class);
        Restaurant restaurant = Mockito.mock(Restaurant.class);
        Courier courier = Mockito.mock(Courier.class);
        Dish dish = Mockito.mock(Dish.class);
        Restaurant dishRestaurant = Mockito.mock(Restaurant.class);

        when(clientService.getClient(createOrderDTO.getClientId())).thenReturn(Optional.of(client));
        when(restaurantService.getRestaurant(createOrderDTO.getRestaurantId())).thenReturn(Optional.of(restaurant));
        when(courierService.getCourier(createOrderDTO.getCourierId())).thenReturn(Optional.of(courier));
        when(dishService.getDish(createOrderDTO.getDishesIds().get(0))).thenReturn(Optional.of(dish));
        when(courier.isAvailable()).thenReturn(true);
        when(dish.getRestaurant()).thenReturn(dishRestaurant);
        when(dishRestaurant.getId()).thenReturn(dishRestaurantId);

        assertThrows(DishNotOnMenuException.class,
                () -> orderService.createOrder(createOrderDTO));
    }
}
