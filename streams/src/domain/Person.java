package domain;

public record Person(String name, int age) {

    public static void main(String[] args) {
        var person = new Person("asd", 3);
        person.age();
        person.name();
        //person.name = "Willi";
    }
}
