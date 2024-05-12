package web.javaproject.fooddeliveryapp.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("A user with the same name already exists.");
    }
}
