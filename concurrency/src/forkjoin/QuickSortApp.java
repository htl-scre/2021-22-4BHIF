package forkjoin;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class QuickSortApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = new ForkJoinPool();
        var items = new Random()
                .ints()
                .limit(100)
                .boxed()
                .toList();

        var submitted = executor.submit(new QuickSortTask(items));
        System.out.println(submitted.get());
        executor.shutdown();
    }
}
