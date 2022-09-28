package pro.sry.EmploeeMockito.WorkingExceptions;

public class IncorrectFirstNameException extends RuntimeException {
    public IncorrectFirstNameException() {
    }

    public IncorrectFirstNameException(String message) {
        super(message);
    }

    public IncorrectFirstNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFirstNameException(Throwable cause) {
        super(cause);
    }

    public IncorrectFirstNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
