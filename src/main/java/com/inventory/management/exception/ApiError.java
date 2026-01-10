package com.inventory.management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiError {
    private final LocalDateTime timestamp;
    private final int status;
    private final String message;
    private final String path;

    public ApiError(int status, String message, String path) {
        this(LocalDateTime.now(), status, message, path);
    }
}
