package axon.tls.restaurant.models;

public class ErrorDetails {
    private String errorMessage;
    private String errors;

    public ErrorDetails(String errorMessage, String errors) {
        super();
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}