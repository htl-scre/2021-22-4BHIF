package threads;

public class Threads {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Outputter());
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}

class Outputter implements Runnable {

    private int i;

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println(i);
                i++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("INTERRUPTED");
            }
        }
    }
}
