package web.javaproject.fooddeliveryapp.exception;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException() {
        super("A client with the same email already exists.");
    }
}