package web.javaproject.fooddeliveryapp.exception;

public class DishDoesNotExistException extends RuntimeException {
    public DishDoesNotExistException() { super("The dish does not exist."); }
}
