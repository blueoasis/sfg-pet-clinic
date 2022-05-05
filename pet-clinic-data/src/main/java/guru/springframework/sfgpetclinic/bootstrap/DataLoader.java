package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        // only load on no pet types in db

        int count = petService.findAll()
                .size();
        if (count == 0) {
            log.debug("No Pet Types found, inserting static data...");
            loadData();
        }
    }

    private void loadData() {
        // PET TYPES
        PetType dogType = new PetType();
        dogType.setName("Dog");
        PetType savedDogType = petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("cat");
        PetType savedCatType = petTypeService.save(catType);

        PetType birdType = new PetType();
        birdType.setName("bird");
        PetType savedBirdType = petTypeService.save(birdType);

        PetType lizardType = new PetType();
        lizardType.setName("lizard");
        PetType savedLizardType = petTypeService.save(lizardType);

        // PETS
        Pet dog1 = Pet.builder()
                .petType(savedDogType)
                .name("Fido")
                .birthDate(LocalDate.of(2003, 12, 01))
                .build();

        Pet cat1 = Pet.builder()
                .petType(savedCatType)
                .name("Sally")
                .birthDate(LocalDate.of(2013, 07, 13))
                .build();

        log.debug("## Pet 1: " + dog1);
        log.debug("## Pet 2: " + cat1);

        // OWNERS
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.getPets()
                .add(dog1);
        owner1.setAddress("111 Maple St");
        owner1.setCity("Mayberry");
        owner1.setTelephone("3032221111");
        dog1.setOwner(owner1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel St");
        owner2.setCity("Miami");
        owner2.setTelephone("7201113333");
        owner2.getPets()
                .add(cat1);

        ownerService.save(owner2);

        log.debug("## Loaded Owners...");

        log.debug("## owner1: " + owner1);
        log.debug("## owner2: " + owner2);

        // SPECIALTIES
        Specialty lovesDogs = new Specialty("Loves Dogs");
        Specialty canineHusbandry = new Specialty("Canine Husbandry");
        Specialty taxidermy = new Specialty("Taxidermy");

        Specialty savedLovesDogs = specialtyService.save(lovesDogs);
        Specialty savedCanineHusbandry = specialtyService.save(canineHusbandry);
        Specialty savedTaxidermy = specialtyService.save(taxidermy);

        // VETS
        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties()
                .add(savedLovesDogs);
        vet1.getSpecialties()
                .add(savedCanineHusbandry);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jack");
        vet2.setLastName("Trombone");
        vet2.getSpecialties()
                .add(savedTaxidermy);

        vetService.save(vet2);

        log.debug("Loaded Vets...");
        log.debug("vet1: " + vet1);
        log.debug("vet2: " + vet2);
    }
}

