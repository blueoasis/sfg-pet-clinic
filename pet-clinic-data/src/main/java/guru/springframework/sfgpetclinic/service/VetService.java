package guru.springframework.sfgpetclinic.service;

import guru.springframework.sfgpetclinic.model.Pet;

import java.util.Set;

public interface VetService {

    Pet findById(Long id);

    Pet findByLastName(String lastName);

    Pet findByFirstName(String firstName);

    Pet save(Pet Pet);

    Set<Pet> findAll();
}
