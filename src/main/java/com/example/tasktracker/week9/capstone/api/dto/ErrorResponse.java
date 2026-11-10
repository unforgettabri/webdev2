package com.example.tasktracker.week9.capstone.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
    int status,
    String message,
    LocalDateTime timestamp,
    List<FieldError> errors
) {
    public record FieldError(
        String field,
        String message
    ) {}
}
