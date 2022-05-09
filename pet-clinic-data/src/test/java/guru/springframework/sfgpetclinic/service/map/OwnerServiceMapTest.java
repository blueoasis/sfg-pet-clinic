package guru.springframework.sfgpetclinic.service.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
    }

    @Test
    void findAll() {
        Owner jack = Owner.builder().firstName("Jack").build();
        Owner sally = Owner.builder().firstName("Sally").build();
        ownerServiceMap.save(jack);
        ownerServiceMap.save(sally);
        Set<Owner> owners = ownerServiceMap.findAll();
        assertEquals(2,owners.size());
    }

    @Test
    void findById() {
        Owner steve = Owner.builder()
                .id(ownerId)
                .build();
        ownerServiceMap.save(steve);
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void save() {
        Owner john = Owner.builder().firstName("John").lastName("Thompson").build();
        Owner saved = ownerServiceMap.save(john);
        assertEquals(john.getFirstName(),saved.getFirstName());
        Set<Owner> all = ownerServiceMap.findAll();
        all.forEach(owner -> {
            System.out.println("owner:" + owner.getFirstName() + " " + owner.getId());
        });
        assertEquals(1, all.size());
    }

    @Test
    void saveNoId() {
        Owner john = Owner.builder().firstName("John").lastName("Thompson").build();
        Owner saved = ownerServiceMap.save(john);

        assertNotNull(saved.getId());
    }

    @Test
    void delete() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertTrue(all.isEmpty());

        Owner mark = Owner.builder().firstName("Mark").id(1L).build();
        Owner janet = Owner.builder().firstName("Janet").id(2L).build();
        ownerServiceMap.save(mark);
        ownerServiceMap.save(janet);

        all = ownerServiceMap.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(mark));
        assertTrue(all.contains(janet));

        ownerServiceMap.delete(mark);

        all = ownerServiceMap.findAll();
        assertEquals(1, all.size());
        assertTrue(all.contains(janet));
        assertFalse(all.contains(mark));

        ownerServiceMap.delete(janet);

        all = ownerServiceMap.findAll();
        assertEquals(0, all.size());
    }

    @Test
    void deleteById() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertTrue(all.isEmpty());

        Owner mark = Owner.builder().firstName("Mark").id(1L).build();
        Owner janet = Owner.builder().firstName("Janet").id(2L).build();
        ownerServiceMap.save(mark);
        ownerServiceMap.save(janet);

        all = ownerServiceMap.findAll();
        assertEquals(2, all.size());
        assertTrue(all.contains(mark));
        assertTrue(all.contains(janet));

        ownerServiceMap.deleteById(mark.getId());

        all = ownerServiceMap.findAll();
        assertEquals(1, all.size());
        assertTrue(all.contains(janet));
        assertFalse(all.contains(mark));

        ownerServiceMap.deleteById(janet.getId());

        all = ownerServiceMap.findAll();
        assertEquals(0, all.size());
    }

    @Test
    void findByLastName() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertTrue(all.isEmpty());

        Owner mark = Owner.builder().firstName("Mark").lastName("Allen").build();
        Owner janet = Owner.builder().firstName("Janet").lastName("Jackson").build();
        ownerServiceMap.save(mark);
        ownerServiceMap.save(janet);

        Owner mAllen = ownerServiceMap.findByLastName("allen");
        Owner jJackson = ownerServiceMap.findByLastName("JACKSON");

        assertEquals("Janet",jJackson.getFirstName());
        assertEquals("Mark",mAllen.getFirstName());
    }

    @Test
    void findByLastNameNotFound() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertTrue(all.isEmpty());

        Owner mark = Owner.builder().firstName("Mark").lastName("Allen").build();
        ownerServiceMap.save(mark);

        Owner mAllen = ownerServiceMap.findByLastName("James");

        assertNull(mAllen);
    }
}
