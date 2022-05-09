package guru.springframework.sfgpetclinic.repo;

import guru.springframework.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
