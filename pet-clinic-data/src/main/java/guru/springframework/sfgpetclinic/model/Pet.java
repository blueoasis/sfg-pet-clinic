package guru.springframework.sfgpetclinic.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Pet extends BaseEntity{
    private LocalDate birthDate;
    private PetType petType;
    private Owner owner;
    private String name;
}
