package web.javaproject.fooddeliveryapp.exception;

public class RestaurantDoesNotExistException extends RuntimeException{
    public RestaurantDoesNotExistException() {
        super("The restaurant does not exist.");
    }
}
