package pro.sry.EmploeeMockito.WorkingExceptions;

public class IncorrectLastNameException extends RuntimeException {

    public IncorrectLastNameException() {
    }

    public IncorrectLastNameException(String message) {
        super(message);
    }

    public IncorrectLastNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLastNameException(Throwable cause) {
        super(cause);
    }

    public IncorrectLastNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
