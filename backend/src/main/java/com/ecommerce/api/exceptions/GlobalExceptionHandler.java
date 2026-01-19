package com.ecommerce.api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ecommerce.api.dtos.ErrorResponse;

/**
 * Global exception interceptor for the entire application.
 * Centralizes error handling by catching exceptions and transforming them
 * into a standardized ErrorResponse format.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all generic exceptions that are not specifically caught elsewhere.
     * Maps these unhandled exceptions to a standard Internal Server Error (500).
     * 
     * @param ex      The exception thrown.
     * @param request The current web request details.
     * @return Standardized error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
