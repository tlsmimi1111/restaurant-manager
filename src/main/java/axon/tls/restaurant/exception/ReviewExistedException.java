package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReviewExistedException  extends RuntimeException{
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewExistedException.class.getName());

    public ReviewExistedException(String errorMessage) {
        this.errorMessage = errorMessage;
        LOGGER.error("ResourceNotFoundException: {}", errorMessage);

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
