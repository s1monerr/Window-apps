package Exceptions;

public class NegativeBoundException extends RuntimeException{
    public NegativeBoundException(String errorMessage){
        super(errorMessage);
    }

}
