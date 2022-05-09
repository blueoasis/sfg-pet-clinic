package guru.springframework.sfgpetclinic.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vet extends Person {
    private Set<Specialty> specialties = new HashSet<>();
}
