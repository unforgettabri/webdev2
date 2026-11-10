package com.example.tasktracker.week9.capstone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicPetRepository extends JpaRepository<ClinicPet, Long> {

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);

    Page<ClinicPet> findBySpeciesIgnoreCase(String species, Pageable pageable);
}
