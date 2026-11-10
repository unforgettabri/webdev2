package com.example.tasktracker.week9.capstone.api;

import com.example.tasktracker.week9.capstone.api.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class ClinicPetApiController {

    private final ClinicPetApiService service;

    public ClinicPetApiController(ClinicPetApiService service) {
        this.service = service;
    }

    @GetMapping
    public List<PetResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PetResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<PetResponseDTO> create(
            @Valid @RequestBody CreatePetRequest request) {
        PetResponseDTO created = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public PetResponseDTO replace(
            @PathVariable Long id,
            @RequestBody UpdatePetRequest request) {
        return service.replace(id, request);
    }

    @PatchMapping("/{id}")
    public PetResponseDTO patch(
            @PathVariable Long id,
            @RequestBody UpdatePetRequest request) {
        return service.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
