package exceptions;

public class PhoneException extends RuntimeException{
    public PhoneException() {
    }

    public PhoneException(String message) {
        super(message);
    }
}
