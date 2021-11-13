package Exceptions;

public class NegativeNumberException extends RuntimeException{
    public NegativeNumberException(String errorMessage){
        super(errorMessage);
    }
}
