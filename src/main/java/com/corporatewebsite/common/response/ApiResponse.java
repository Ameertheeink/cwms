package com.corporatewebsite.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

/**
 * Standard API response wrapper used across all REST endpoints.
 *
 * @param <T> Response payload type
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final Map<String, String> errors;
    private final Instant timestamp;

    /**
     * Private constructor to enforce the use of static factory methods.
     */
    private ApiResponse(boolean success,
                        String message,
                        T data,
                        Map<String, String> errors) {

        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

    /**
     * Creates a success response with data.
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null);
    }

    /**
     * Creates a success response without data.
     */
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(true, message, null, null);
    }

    /**
     * Creates a failure response with data.
     */
    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>(false, message, data, null);
    }

    /**
     * Creates a failure response without data.
     */
    public static <T> ApiResponse<T> failure(String message) {
        return new ApiResponse<>(false, message, null, null);
    }

    /**
     * Creates a validation failure response.
     */
    public static <T> ApiResponse<T> validationFailure(
            String message,
            Map<String, String> errors) {

        return new ApiResponse<>(false, message, null, errors);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}