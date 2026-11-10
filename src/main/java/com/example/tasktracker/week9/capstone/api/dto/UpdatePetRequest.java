package com.example.tasktracker.week9.capstone.api.dto;

public record UpdatePetRequest(
    String name,
    String species,
    Integer age,
    Long ownerId
) {}
