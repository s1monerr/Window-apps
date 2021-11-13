package Exceptions;

public class EmptyArrayException extends RuntimeException{
    public EmptyArrayException(String errorMessage){
        super(errorMessage);
    }
}
