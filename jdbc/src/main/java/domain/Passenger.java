package domain;

public record Passenger(Integer id, String name, int age) {

    public Passenger(String name, int age) {
        this(null, name, age);
    }
}
