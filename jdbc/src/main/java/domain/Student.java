package domain;

import lombok.*;
import lombok.experimental.Delegate;

import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@NoArgsConstructor
public class Student {

    @EqualsAndHashCode.Include
    private int id;
    @Delegate
    private Personalia personalia;
    private Set<String> phoneNumbers;
    private int number;

    public static void main(String[] args) {
        new Student().getLastName();
    }
}
