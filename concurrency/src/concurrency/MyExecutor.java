package concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executor;

public class MyExecutor implements Executor {

    Collection<Worker> threads = new ArrayList<>();

    @Override
    public void execute(Runnable command) {
        //command.run();
        //new Thread(command).start();
        var worker = threads
                .stream()
                .filter(t -> !t.isWorking())
                .findAny()
                .orElse(new Worker());
        worker.command = command;
        synchronized (worker) {
            worker.notify();
        }
        if (!worker.isWorking())
            worker.start();
    }

    private static class Worker extends Thread {

        private Runnable command;
        private boolean firstRunning;

        @Override
        public synchronized void run() {
            while (true) {
                while(command == null) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                command.run();
                command = null;
            }
        }

        public boolean isWorking() {
            return command == null;
        }
    }
}
