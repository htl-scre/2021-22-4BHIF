package threads;

public class IdGenerator {
    int lastUsedId;

    public int incrementValue() {
        return ++lastUsedId;
    }
}
