package valera.gord.magnusmovieproject.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateTimeParseException(DateTimeParseException ex) {
        String customMessage = "Invalid date format. Please use 'yyyy-MM-dd' format.";
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, customMessage, ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    public static class ApiError {
        private HttpStatus status;
        private String message;
        private String debugMessage;
    }
}

