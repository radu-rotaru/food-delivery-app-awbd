package web.javaproject.fooddeliveryapp.exception;

public class ClientDoesNotExistException extends RuntimeException{
    public ClientDoesNotExistException() {
        super("The client does not exist.");
    }
}
