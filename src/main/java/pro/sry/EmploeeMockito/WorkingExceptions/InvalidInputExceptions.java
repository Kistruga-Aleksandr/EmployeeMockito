package pro.sry.EmploeeMockito.WorkingExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputExceptions extends RuntimeException {
    public InvalidInputExceptions() {
    }

    public InvalidInputExceptions(String message) {
        super(message);
    }

    public InvalidInputExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputExceptions(Throwable cause) {
        super(cause);
    }

    public InvalidInputExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
