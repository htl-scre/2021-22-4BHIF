package domain;

import java.util.StringJoiner;

public class Joining {

    public static void main(String[] args) {
        var joiner = new StringJoiner(" - ");
        System.out.println(joiner.add("Baumann")
                .add("Kartal")
                .add("Schally")
                .toString());

    }
}
