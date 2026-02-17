package com.AgendaMed.Backend.exception;

import lombok.Getter;

@Getter
public class ApiError {

    private final int status;
    private final String message;

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
    }
}