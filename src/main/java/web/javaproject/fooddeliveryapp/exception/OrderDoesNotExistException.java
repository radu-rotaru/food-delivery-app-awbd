package web.javaproject.fooddeliveryapp.exception;

public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException() { super("The order does not exist."); }
}
