package com.example.tasktracker.week9.capstone;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class ClinicService {

    private final OwnerRepository ownerRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final ClinicPetRepository petRepository;
    private final AppointmentRepository appointmentRepository;
    private final VaccinationRecordRepository vaccinationRepository;

    public ClinicService(
        OwnerRepository ownerRepository,
        VeterinarianRepository veterinarianRepository,
        ClinicPetRepository petRepository,
        AppointmentRepository appointmentRepository,
        VaccinationRecordRepository vaccinationRepository
    ) {
        this.ownerRepository = ownerRepository;
        this.veterinarianRepository = veterinarianRepository;
        this.petRepository = petRepository;
        this.appointmentRepository = appointmentRepository;
        this.vaccinationRepository = vaccinationRepository;
    }

    @Transactional
    public void createSampleClinicRecords() {
        if (ownerRepository.count() > 0) {
            return;
        }

        Owner owner = new Owner("Alex", "Santos", "09123456789");
        ClinicPet pet = new ClinicPet("Mochi", "Dog", 3);
        owner.addPet(pet);

        Veterinarian vet = veterinarianRepository.save(
            new Veterinarian("Jamie", "Reyes", "General Practice")
        );

        Appointment appointment = new Appointment(
            LocalDate.now(),
            "Annual checkup",
            vet
        );
        pet.addAppointment(appointment);

        VaccinationRecord vaccination = new VaccinationRecord(
            "Rabies",
            LocalDate.now()
        );
        pet.addVaccinationRecord(vaccination);

        ownerRepository.save(owner);
    }

    @Transactional(readOnly = true)
    public String readSampleClinicRecords() {
        Owner owner = ownerRepository.findAll().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No clinic owner found"));

        ClinicPet pet = owner.getPets().stream()
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No pet found"));

        return owner.getFirstName() + " owns " + pet.getName()
            + " (" + pet.getSpecies() + "), with "
            + pet.getAppointments().size() + " appointment(s) and "
            + pet.getVaccinationRecords().size() + " vaccination record(s).";
    }
}