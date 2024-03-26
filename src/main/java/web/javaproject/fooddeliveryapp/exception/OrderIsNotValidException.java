package web.javaproject.fooddeliveryapp.exception;

public class OrderIsNotValidException extends  RuntimeException {
    public OrderIsNotValidException() { super("Order is not valid for the given client and courier."); }
}
