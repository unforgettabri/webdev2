package com.example.tasktracker.week9.capstone.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UpdatePetRequest(

    @Size(min = 1, max = 100, message = "Pet name must contain 1-100 characters")
    String name,

    @Size(min = 1, max = 50, message = "Species must contain 1-50 characters")
    String species,

    @Min(value = 0, message = "Age cannot be negative")
    Integer age,

    @Positive(message = "Owner ID must be positive")
    Long ownerId

) {}
