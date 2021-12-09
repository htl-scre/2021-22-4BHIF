package singleton;


import java.util.HashMap;
import java.util.Map;

/**
 * Desired effect: Exactly ONE object of class Singleton
 */
public class Singleton {

    private static Singleton instance = null;
    private static Map<String, Singleton> instances = new HashMap<>();
    private String name;

    private Singleton(String name) {
        this.name = name;
    }

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        //if (instances.get(name) == null)
        //    instances.put(name, new Singleton(name));
        return instance;
    }
}

class Main {

    public static void main(String[] args) {
        var one = Singleton.getInstance();
        var two = Singleton.getInstance();
        System.out.println(one == two);
    }
}
