package Exceptions;

public class OutOfRangeException extends RuntimeException{
    public OutOfRangeException(String errorMessage){
        super(errorMessage);
    }
}
