package web.javaproject.fooddeliveryapp.exception;

public class CourierNotAvailableException extends RuntimeException {
    public CourierNotAvailableException() {
        super("The courier is not available.");
    }
}
