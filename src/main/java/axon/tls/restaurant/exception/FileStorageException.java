package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileStorageException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageException.class.getName());

    public FileStorageException() {
        super();
    }

    public FileStorageException(String message) {
        super(message);
        LOGGER.error("FileStorageException: {}", message);

    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
