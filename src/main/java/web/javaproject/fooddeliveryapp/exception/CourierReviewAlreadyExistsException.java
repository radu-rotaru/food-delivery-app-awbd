package web.javaproject.fooddeliveryapp.exception;

public class CourierReviewAlreadyExistsException extends RuntimeException {
    public CourierReviewAlreadyExistsException() { super("A review with this specific client, courier and order already exists."); }
}
