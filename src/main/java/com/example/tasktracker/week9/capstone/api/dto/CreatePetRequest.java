package com.example.tasktracker.week9.capstone.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreatePetRequest(

    @NotBlank(message = "Pet name is required")
    @Size(max = 100, message = "Pet name must not exceed 100 characters")
    String name,

    @NotBlank(message = "Species is required")
    @Size(max = 50, message = "Species must not exceed 50 characters")
    String species,

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age cannot be negative")
    Integer age,

    @NotNull(message = "Owner ID is required")
    @Positive(message = "Owner ID must be positive")
    Long ownerId

) {}
