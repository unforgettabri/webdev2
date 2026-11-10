package com.example.tasktracker.week9.capstone.api;

import com.example.tasktracker.week9.capstone.api.dto.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/pets")
public class ClinicPetApiController {

    private final ClinicPetApiService service;

    public ClinicPetApiController(ClinicPetApiService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PetResponseDTO> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String species) {

        return service.getAll(page, size, sort, direction, species);
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
            @Valid @RequestBody UpdatePetRequest request) {
        return service.replace(id, request);
    }

    @PatchMapping("/{id}")
    public PetResponseDTO patch(
            @PathVariable Long id,
            @Valid @RequestBody UpdatePetRequest request) {
        return service.patch(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
