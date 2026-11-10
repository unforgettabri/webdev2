package com.example.tasktracker.week9.capstone.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePetRequest(
    @NotBlank String name,
    @NotBlank String species,
    @NotNull @Min(0) Integer age,
    @NotNull @Positive Long ownerId
) {}
