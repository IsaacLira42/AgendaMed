package com.AgendaMed.Backend.exception;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiError {

    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }
}