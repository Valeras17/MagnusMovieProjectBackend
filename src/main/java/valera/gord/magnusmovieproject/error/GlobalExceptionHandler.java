package valera.gord.magnusmovieproject.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        String customMessage = "Invalid date format. Please use 'yyyy-MM-dd' format.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, customMessage, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException e){
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problemDetails.setProperty("timestamp", LocalDateTime.now());
        problemDetails.setProperty("resourceName", e.getResourceName());
        problemDetails.setProperty("resourceId", e.getResourceId());
        return problemDetails;
    }

    @ExceptionHandler(MagnusMovieException.class)
    public ProblemDetail handleMagnusMovieException(MagnusMovieException e){
        var problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        //new ProblemDetails Object:
        var problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {

            //forEach fielderror in the exception:
            problemDetail.setProperty("Validation Failed for property",fieldError.getField());
            //problemDetail.setProperty("objectName",fieldError.getObjectName());
            problemDetail.setProperty("message",fieldError.getDefaultMessage());
            problemDetail.setProperty("rejectedValue",fieldError.getRejectedValue());
        });
        //add details about exception
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolationException(DataIntegrityViolationException e){
        var problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");
        if (e.getCause() instanceof ConstraintViolationException){
            problemDetail.setProperty("cause", "Constraint Violation");
        }
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e){
        var problemDetail =  ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }






    @Data
    @AllArgsConstructor
    public static class ApiError {
        private HttpStatus status;
        private String message;
        private String debugMessage;
    }
}

