package ch.lubu.timekeeperv2.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * This class handles Exceptions thrown by the application and returns well-formed JSON responses instead.
 *
 * @author Patrick Meier, Lukas Bühler
 * @version 2.0
 *
 */

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateCouldNotBeSavedException.class)
    public ResponseEntity<Object> handleDateCouldNotBeSavedException(DateCouldNotBeSavedException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(CategoryLoadException.class)
    public ResponseEntity<Object> handleCategoryLoadException(CategoryLoadException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(EntryDateLoadException.class)
    public ResponseEntity<Object> handleEntryDateLoadException(EntryDateLoadException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(EntryDateNotFoundException.class)
    public ResponseEntity<Object> handleEntryDateNotFoundException(EntryDateNotFoundException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TimeCouldNotBeSavedException.class)
    public ResponseEntity<Object> handleTimeCouldNotBeSavedException(TimeCouldNotBeSavedException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(TimeCatEntrydateDublicateException.class)
    public ResponseEntity<Object> handleTimeCatEntrydateDublicateException(TimeCatEntrydateDublicateException ex, WebRequest request) {
        return createDefaultErrorResponse(ex.getMessage());
    }

    /**
     * Creates a default error response with the given message.
     *
     * @param message The message to be returned.
     * @return The response entity.
     */
    private ResponseEntity<Object> createDefaultErrorResponse(String exceptionMessage) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exceptionMessage);

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
