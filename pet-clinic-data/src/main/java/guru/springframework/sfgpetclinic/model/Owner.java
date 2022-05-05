package guru.springframework.sfgpetclinic.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"pets"})
public class Owner extends Person {
    private Set<Pet> pets = new HashSet<>();
    private String address;
    private String city;
    private String telephone;
}
