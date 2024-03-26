package web.javaproject.fooddeliveryapp.exception;

public class DishNotOnMenuException extends RuntimeException {
    public DishNotOnMenuException() { super("The dish was not on the menu."); }
}
