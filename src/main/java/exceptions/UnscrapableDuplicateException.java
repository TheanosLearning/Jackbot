package exceptions;

public class UnscrapableDuplicateException extends Exception {

    public UnscrapableDuplicateException() {}

    public UnscrapableDuplicateException(String message) {
        super(message);
    }

    public UnscrapableDuplicateException(Throwable cause) {
        super(cause);
    }

    public UnscrapableDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

}
