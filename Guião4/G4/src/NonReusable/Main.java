package NonReusable;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int N = 10;
        List<Thread> threads = new ArrayList<>();
        Barrier barrier = new Barrier(N);

        for (int i = 0; i < N; i++)
        {
            Thread thread = new Thread(barrier);
            threads.add(thread);
            thread.start();
        }

        for (Thread t : threads)
        {
            System.out.println("Joining Threads.");
            t.join();
        }

        System.out.println("End.");

    }
}