package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviser {

    @ExceptionHandler({ElementNotFoundException.class})
    public ResponseEntity<Object> handleElementNotFound(ElementNotFoundException ex) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        errors.put("timestamp", LocalDateTime.now());
        errors.put("message", "Data input error");

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
