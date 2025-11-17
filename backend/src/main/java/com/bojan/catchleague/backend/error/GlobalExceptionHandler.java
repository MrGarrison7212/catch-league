package com.bojan.catchleague.backend.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation failed");
        pd.setType(URI.create("about:blank"));
        pd.setDetail("One or more fields are invalid.");
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        pd.setProperty("errors", fieldErrors);
        return pd;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Constraint violation");
        pd.setDetail("Request parameters failed validation.");
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());

        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> v : ex.getConstraintViolations()) {
            errors.put(v.getPropertyPath().toString(), v.getMessage());
        }
        pd.setProperty("errors", errors);
        return pd;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatus(ResponseStatusException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(ex.getStatusCode(), ex.getReason());
        pd.setTitle(defaultTitle(ex.getStatusCode().value()));
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    public ProblemDetail handleBadRequest(Exception ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Bad request");
        pd.setDetail(ex.getMessage());
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleConflict(DataIntegrityViolationException ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Data integrity violation");
        pd.setDetail("Request conflicts with existing data.");
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    @ExceptionHandler({ ErrorResponseException.class, Exception.class })
    public ProblemDetail handleUnhandled(Exception ex, HttpServletRequest req) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pd.setTitle("Internal server error");
        pd.setDetail("Unexpected error occurred.");
        pd.setProperty("timestamp", OffsetDateTime.now());
        pd.setProperty("path", req.getRequestURI());
        return pd;
    }

    private String defaultTitle(int status) {
        return switch (status) {
            case 400 -> "Bad request";
            case 404 -> "Not found";
            case 409 -> "Conflict";
            default -> "Error";
        };
    }
}
