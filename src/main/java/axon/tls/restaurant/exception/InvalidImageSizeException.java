package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidImageSizeException extends RuntimeException{
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidImageSizeException.class.getName());


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public InvalidImageSizeException(String errorMessage) {
        this.errorMessage = errorMessage;
        LOGGER.error("FileStorageException: {}", errorMessage);

    }
}
