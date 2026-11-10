package com.example.tasktracker.week9.capstone.api.dto;

public record PetResponseDTO(
    Long id,
    String name,
    String species,
    Integer age,
    Long ownerId
) {}
