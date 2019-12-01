package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BadRequestException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(BadRequestException.class.getName());

    public BadRequestException(String message) {
        super(message);
        LOGGER.error("BadRequestException: {}", message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
