import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wiederholung {

    public static void main(String[] args) {
        System.out.println(Stream.of("Franz", "Koglbauer", "Maus")
                .mapToInt(String::length)
                .average()
                .orElseThrow()
        );
        var map = new Random().ints(20, 1, 100)
                .distinct()
                .boxed()
                .collect(Collectors.groupingBy(i -> i % 100 / 10, TreeMap::new, Collectors.toList()));
        System.out.println(map);
    }
}
