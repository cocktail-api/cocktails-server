package de.slevermann.cocktails.controller;

import de.slevermann.cocktails.dto.ErrorModel;
import de.slevermann.cocktails.exception.BadTranslationException;
import de.slevermann.cocktails.exception.MissingIngredientTypeException;
import org.jdbi.v3.core.JdbiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(JdbiException.class)
    public ResponseEntity<Void> handleSqlException(JdbiException ex, WebRequest request) {
        LOGGER.error("JDBI Error", ex);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(BadTranslationException.class)
    public ResponseEntity<ErrorModel> handleBadTranslationException(BadTranslationException ex, WebRequest request) {
        return handleInternal(UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorModel> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return handleInternal(UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(MissingIngredientTypeException.class)
    public ResponseEntity<ErrorModel> handleMissingIngredientTypeException(MissingIngredientTypeException ex, WebRequest request) {
        return handleInternal(UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    private ResponseEntity<ErrorModel> handleInternal(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ErrorModel()
                .status(status.value())
                .message(message));
    }

    private ResponseEntity<ErrorModel> handleInternal(HttpStatus status) {
        return handleInternal(status, null);
    }
}
