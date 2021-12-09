import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Optionals {

    public static void main(String[] args) {
        List<String> partners = List.of();
        Optional<String> optional = Stream.of("Franz", "Karl", "Michael")
                .filter(s -> partners.contains(s))
                .findAny();
        if (optional.isPresent()) {
            String content = optional.get();
            System.out.println(optional);
        } else
            throw new IllegalArgumentException("Error");
    }
}
