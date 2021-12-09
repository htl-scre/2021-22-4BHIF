package forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class QuickSortTask extends RecursiveTask<List<Integer>> {

    private final List<Integer> items;

    public QuickSortTask(List<Integer> items) {
        this.items = items;
    }

    @Override
    protected List<Integer> compute() {
        if (items.size() == 1)
            return items.subList(0, 1);
        int pivot = items.get(0);
        var smaller = new ArrayList<Integer>();
        var larger = new ArrayList<Integer>();
        for (int i : items) {
            if (i <= pivot)
                smaller.add(i);
            else
                larger.add(i);
        }

        var taskSmaller = new QuickSortTask(smaller);
        var taskLarger = new QuickSortTask(larger);
        var smallerSorted = taskSmaller.fork();
        var largerSorted = taskLarger.fork();
        var result = new ArrayList<Integer>();
        try {
            result.addAll(smallerSorted.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        result.add(pivot);
        try {
            result.addAll(largerSorted.get());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
