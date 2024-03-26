package web.javaproject.fooddeliveryapp.exception;

public class CourierDoesNotExistException extends RuntimeException {
    public CourierDoesNotExistException() {
        super("The courier does not exist.");
    }
}
