package web.javaproject.fooddeliveryapp.exception;

public class RestaurantAlreadyExistsException extends RuntimeException {
    public RestaurantAlreadyExistsException() {
        super("A restaurant with the same email already exists.");
    }
}
