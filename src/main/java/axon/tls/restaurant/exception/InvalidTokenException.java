package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidTokenException extends RuntimeException{
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidTokenException.class.getName());

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidTokenException(String errorMessage) {
        this.errorMessage = errorMessage;
        LOGGER.error("InvalidTokenException: {}", errorMessage);

    }
}
