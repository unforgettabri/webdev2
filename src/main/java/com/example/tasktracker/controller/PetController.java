package com.example.tasktracker.controller;

import com.example.tasktracker.model.Pet;
import com.example.tasktracker.service.PetService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Pet> registerPet(@Valid @RequestBody Pet pet) {
        Pet saved = petService.registerPet(pet);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ ALL
    @GetMapping
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Pet getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Pet updatePet(
            @PathVariable Long id,
            @Valid @RequestBody Pet pet) {
        return petService.updatePet(id, pet);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.removePet(id);
        return ResponseEntity.noContent().build();
    }

    // DERIVED QUERY: SPECIES
    @GetMapping("/species")
    public List<Pet> getPetsBySpecies(@RequestParam String name) {
        return petService.getPetsBySpecies(name);
    }

    // DERIVED QUERY: NAME SEARCH
    @GetMapping("/search")
    public List<Pet> searchPets(@RequestParam String keyword) {
        return petService.searchPetsByName(keyword);
    }

    // CUSTOM JPQL QUERY
    @GetMapping("/species-list")
    public List<String> getAllSpecies() {
        return petService.getAllSpecies();
    }

    // TRANSACTIONAL BATCH
    @PostMapping("/batch")
    public ResponseEntity<?> batchRegister(@Valid @RequestBody List<@Valid Pet> pets) {
        try {
            petService.batchRegister(pets);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Pets registered successfully"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Duplicate microchip number or invalid data. Batch rolled back."));
        }
    }
}