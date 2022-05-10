package guru.springframework.sfgpetclinic.service.jpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repo.OwnerRepository;
import guru.springframework.sfgpetclinic.repo.PetRepository;
import guru.springframework.sfgpetclinic.repo.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerDataServiceJpaTest {

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    OwnerDataServiceJpa ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.ownerService = new OwnerDataServiceJpa(ownerRepository,petRepository,petTypeRepository);
        Set<Owner> owners = new HashSet<>();
        Owner john = Owner.builder().firstName("John").lastName("Jones").id(1L).build();
        Owner mark = Owner.builder().firstName("Mark").lastName("Adams").id(2L).build();
        owners.add(john);
        owners.add(mark);

        when(ownerRepository.findAll()).thenReturn(owners);
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(john));
        when(ownerRepository.findById(2L)).thenReturn(Optional.of(mark));
        when(ownerRepository.save(john)).thenReturn(john);
        when(ownerRepository.save(mark)).thenReturn(mark);
        when(ownerRepository.findByLastName("Adams")).thenReturn(mark);
        when(ownerRepository.findByLastName("Jones")).thenReturn(john);
    }

    @Test
    void findAll() {
        Set<Owner> myOwners = ownerService.findAll();
        assertNotNull(myOwners);
        assertFalse(myOwners.isEmpty());
    }

    @Test
    void findById() {
        Owner myMark = ownerService.findById(2L);
        assertNotNull(myMark);
        assertEquals("Mark",myMark.getFirstName());
    }

    @Test
    void save() {
        Owner myMark = ownerService.findById(2L);
        assertNotNull(myMark);
        Owner mark2 = ownerService.save(myMark);
        assertEquals("Mark",mark2.getFirstName());
    }

    @Test
    void findByLastName() {
        Owner myMark = ownerService.findByLastName("Adams");
        assertEquals("Mark",myMark.getFirstName());
        verify(ownerRepository, times(1)).findByLastName(anyString());
    }

    @Test
    void delete() {
        ownerRepository.delete(Owner.builder()
                .build());

        verify(ownerRepository,times(5)).delete(any());
    }

    @Test
    void deleteById() {
        ownerRepository.deleteById(5L);

        verify(ownerRepository).deleteById(anyLong());
    }

}
