package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityNotFoundException extends RuntimeException{
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityNotFoundException.class.getName());


    public EntityNotFoundException(String message) {
        super(message);
        this.errorMessage = message;
        LOGGER.error("EntityNotFoundException: {}", message);

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
