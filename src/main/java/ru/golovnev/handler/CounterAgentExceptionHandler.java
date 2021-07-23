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
    /**
     * Метод обработки исключения, когда контрагент не найден в БД
     * @param exception вызванное исключение
     * @return страницу http со статусом {@link HttpStatus#NOT_FOUND} и сообщением исключения
     */
    @ExceptionHandler(AgentNotFoundException.class)
    public ResponseEntity<Object> agentNotFound(AgentNotFoundException exception) {
        log.error("[ExceptionHandler]\tThrowed AgentNotFoundException - return ResponseEntity with HttpStatus NOT_FOUND: " + exception);
        return new ResponseEntity<>("AgentNotFoundException: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обрабоки исключения, когда аргумент не прошел валидацию
     * @param exception вызванное исключение
     * @return страницу http со статусом {@link HttpStatus#BAD_REQUEST} и сообщением исключения
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e : errors) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            log.error("[ExceptionHandler]\tThrowed MethodArgumentNotValidException - return " +
                    "ResponseEntity with HttpStatus BAD_REQUEST:" + exception);
            return new ResponseEntity<>("Caught exception: " + message, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    /**
     * Метод обработки всех остальных исключений
     * @param exception вызванное исключение
     * @return страницу http со статусом {@link HttpStatus#INTERNAL_SERVER_ERROR} и сообщением исключения
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> responseEntityException(Exception exception) {
        log.error("[ExceptionHandler]\tThrowed some exception - return ResponseEntity with HttpStatus INTERNAL_SERVER_ERROR: " + exception);
        return new ResponseEntity<>("Caught exception: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
