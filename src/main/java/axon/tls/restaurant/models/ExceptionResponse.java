package axon.tls.restaurant.models;

import java.util.List;

public class ExceptionResponse {
    private String errorMessage;
    private List<String> errors;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public ExceptionResponse(String errorMessage, List<String> errors) {
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public ExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ExceptionResponse() {
    }
}