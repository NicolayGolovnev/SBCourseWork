package ru.golovnev.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.golovnev.exception.AgentNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Обработка исключений
 */
@ControllerAdvice
@Slf4j
public class CounterAgentExceptionHandler {
    @ExceptionHandler(value = AgentNotFoundException.class)
    public ResponseEntity<Object> agentNotFound(AgentNotFoundException exception) {
        log.error("[ExceptionHandler]\tThrowed AgentNotFoundException - return ResponseEntity with HttpStatus NOT_FOUND");
        return new ResponseEntity<>("AgentNotFoundException: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        log.error("[ExceptionHandler]\tThrowed some exception - return ResponseEntity with HttpStatus INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>("Caught exception: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e : errors) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            log.error("[ExceptionHandler]\tThrowed MethodArgumentNotValidException - return " +
                    "ResponseEntity with HttpStatus BAD_REQUEST");
            return new ResponseEntity<>("Caught exception: " + message, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
