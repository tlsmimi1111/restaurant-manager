package axon.tls.restaurant.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;

import axon.tls.restaurant.exception.AuthorizationFailException;
import axon.tls.restaurant.exception.EntityNotFoundException;
import axon.tls.restaurant.exception.InvalidParamException;
import axon.tls.restaurant.exception.InvalidTokenException;
import axon.tls.restaurant.models.ErrorDetails;
import axon.tls.restaurant.models.ExceptionResponse;

@CrossOrigin
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handleEntityNotFoundException(EntityNotFoundException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getErrorMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity handleInvalidTokenException(InvalidTokenException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getErrorMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidParamException.class)
	public ResponseEntity handleInvalidParamException(InvalidParamException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getErrorMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AuthorizationFailException.class)
	public ResponseEntity handleAuthorizationFailException(AuthorizationFailException ex) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getErrorMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
				"Validation Failed");
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
//    @ExceptionHandler(InvalidImageSizeException.class)
//    public ResponseEntity handleInvalidImageSizeException(InvalidImageSizeException ex) {
//        ExceptionResponse exceptionResponse = new ExceptionResponse(
//                ex.getErrorMessage());
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
//    }
//
//
//    }

}
