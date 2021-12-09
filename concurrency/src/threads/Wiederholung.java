package threads;

public class Wiederholung {

    private boolean safe;
    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        var instance = new Wiederholung();
        new Thread(() -> instance.setSafe(true))
                .start();
        new Thread(() -> instance.setSafe(false))
                .start();
        Thread.sleep(10);
        System.out.println(instance.i);
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
        i++;
    }
}
