package axon.tls.restaurant.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

public class InvalidParamException extends RuntimeException {
    private BindingResult result;
    private String errorMessage;
    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidParamException.class.getName());

    public InvalidParamException(BindingResult result) {
        super();
        this.result = result;
    }
    public InvalidParamException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public BindingResult getResult() {
        return result;
    }

    public void setResult(BindingResult result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}