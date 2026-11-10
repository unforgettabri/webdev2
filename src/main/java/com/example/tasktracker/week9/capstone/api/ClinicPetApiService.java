package com.example.tasktracker.week9.capstone.api;

import com.example.tasktracker.week9.capstone.*;
import com.example.tasktracker.week9.capstone.api.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class ClinicPetApiService {

    private final ClinicPetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public ClinicPetApiService(
            ClinicPetRepository petRepository,
            OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @Transactional(readOnly = true)
    public List<PetResponseDTO> getAll() {
        return petRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public PetResponseDTO getById(Long id) {
        return toDTO(findPet(id));
    }

    public PetResponseDTO create(CreatePetRequest request) {
        checkDuplicateName(request.name(), null);

        Owner owner = findOwner(request.ownerId());
        ClinicPet pet = new ClinicPet(
            request.name(), request.species(), request.age()
        );
        pet.setOwner(owner);

        return toDTO(petRepository.save(pet));
    }

    public PetResponseDTO replace(Long id, UpdatePetRequest request) {
        ClinicPet pet = findPet(id);
        validateUpdate(request, true);
        checkDuplicateName(request.name(), id);

        pet.setName(request.name());
        pet.setSpecies(request.species());
        pet.setAge(request.age());
        pet.setOwner(findOwner(request.ownerId()));

        return toDTO(petRepository.save(pet));
    }

    public PetResponseDTO patch(Long id, UpdatePetRequest request) {
        ClinicPet pet = findPet(id);

        if (request.name() == null && request.species() == null
                && request.age() == null && request.ownerId() == null) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "At least one field is required"
            );
        }

        validateUpdate(request, false);

        if (request.name() != null) {
            checkDuplicateName(request.name(), id);
            pet.setName(request.name());
        }
        if (request.species() != null) pet.setSpecies(request.species());
        if (request.age() != null) pet.setAge(request.age());
        if (request.ownerId() != null) pet.setOwner(findOwner(request.ownerId()));

        return toDTO(petRepository.save(pet));
    }

    public void delete(Long id) {
        petRepository.delete(findPet(id));
    }

    private ClinicPet findPet(Long id) {
        return petRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pet not found"
            ));
    }

    private Owner findOwner(Long id) {
        return ownerRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Owner not found"
            ));
    }

    private void checkDuplicateName(String name, Long id) {
        boolean duplicate = id == null
            ? petRepository.existsByNameIgnoreCase(name)
            : petRepository.existsByNameIgnoreCaseAndIdNot(name, id);

        if (duplicate) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Pet name already exists"
            );
        }
    }

    private void validateUpdate(UpdatePetRequest request, boolean full) {
        if (full && (request.name() == null || request.name().isBlank()
                || request.species() == null || request.species().isBlank()
                || request.age() == null || request.ownerId() == null)) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "All fields are required for PUT"
            );
        }

        if (request.name() != null && request.name().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be blank");
        }
        if (request.species() != null && request.species().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Species cannot be blank");
        }
        if (request.age() != null && request.age() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Age cannot be negative");
        }
        if (request.ownerId() != null && request.ownerId() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid owner ID");
        }
    }

    private PetResponseDTO toDTO(ClinicPet pet) {
        return new PetResponseDTO(
            pet.getId(),
            pet.getName(),
            pet.getSpecies(),
            pet.getAge(),
            pet.getOwner().getId()
        );
    }
}
