package com.ecommerce.api.dtos;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object representing a standardized error message across the
 * API.
 * Encapsulates error details consistent with REST best practices.
 */
@Getter
@Setter
@Builder
public class ErrorResponse {

    /**
     * Exact time when the error occurred.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP status code (e.g., 404, 500).
     */
    private int status;

    /**
     * Short name of the HTTP error.
     */
    private String error;

    /**
     * Descriptive message explaining the error.
     */
    private String message;

    /**
     * The URI path where the error was triggered.
     */
    private String path;
}
