package domain;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Person {

    @EqualsAndHashCode.Include
    private int id;
    private String lastName;

    private String firstName;
    private Set<String> phoneNumbers;

}
