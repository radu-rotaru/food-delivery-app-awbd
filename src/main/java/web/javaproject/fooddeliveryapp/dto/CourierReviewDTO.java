package web.javaproject.fooddeliveryapp.dto;

public class CourierReviewDTO {
    private Long id;
    private ClientDTO client;
    private CourierDTO courier;
    private OrderDTO order;

    private int stars;

    public CourierReviewDTO() {
    }

    public CourierReviewDTO(Long id, ClientDTO client, CourierDTO courier, OrderDTO order, int stars) {
        this.id = id;
        this.client = client;
        this.courier = courier;
        this.order = order;
        this.stars = stars;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public CourierDTO getCourier() {
        return courier;
    }

    public void setCourier(CourierDTO courier) {
        this.courier = courier;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }
}
