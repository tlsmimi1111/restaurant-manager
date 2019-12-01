package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthorizationFailException extends RuntimeException {
    private String errorMessage;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFailException.class.getName());

    public AuthorizationFailException(String message) {
        super(message);
        this.errorMessage = message;
        LOGGER.error("AuthorizationFailException: {}", message);
    }

    public AuthorizationFailException() {}

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
