package concurrency;

import java.util.concurrent.*;

public class Wiederholung {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new MyCallable());
        executor.execute(new MyRunnable());
        System.out.println(future.get());
        System.out.println("the end");
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}

class MyCallable implements Callable<String>{

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return "Call finished";
    }
}

class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Running");
    }
}