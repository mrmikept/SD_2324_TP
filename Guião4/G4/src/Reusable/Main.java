package Reusable;

import NonReusable.Barrier;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        final int N = 10;
        List<Thread> threads = new ArrayList<>();
        ReusableBarrier barrier = new ReusableBarrier(N);

        for (int i = 0; i < N; i++)
        {
            Thread thread = new Thread(barrier);
            thread.setName("Thread" + i);
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
