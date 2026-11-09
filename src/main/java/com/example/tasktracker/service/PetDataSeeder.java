package com.example.tasktracker.service;

import com.example.tasktracker.model.Pet;
import com.example.tasktracker.repository.PetRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PetDataSeeder implements CommandLineRunner {

    private final PetRepository petRepository;

    public PetDataSeeder(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) {

        if (petRepository.count() == 0) {

            petRepository.save(new Pet(
                    "CHIP001", "Buddy", "Dog", "Golden Retriever"));

            petRepository.save(new Pet(
                    "CHIP002", "Mochi", "Cat", "Persian"));

            petRepository.save(new Pet(
                    "CHIP003", "Max", "Dog", "Labrador"));

            System.out.println("Sample pets successfully inserted!");
        }
    }
}