package Reusable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReusableBarrier implements Runnable
{
    private int maxWaiters;
    private int epoch;
    private int waiters;
    private ReentrantLock lock;
    private Condition condition;

    public ReusableBarrier(int N)
    {
        this.maxWaiters = N;
        this.epoch = 0;
        this.waiters = 0;
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    public void await() throws InterruptedException
    {
        this.lock.lock();
        try
        {
            int myEpoch = this.epoch;
            this.waiters++;
            System.out.println("Entering barrier - number of waiters: " + this.waiters + " of max " + this.maxWaiters + "; My Epoch: " + myEpoch);
            if (this.waiters < this.maxWaiters)
            {
                while (this.epoch == myEpoch)
                {
                    System.out.println("Waiting for waiters...");
                    this.condition.await();
                }
                System.out.println("Signaled... My Epoch: " + myEpoch + "; Current Epoch: " + this.epoch);
            }
            else
            {
                System.out.println("Reached the number of waiters... Signal Threads... " + "Waiters: " + this.waiters + "; Epoch: " + this.epoch);
                this.epoch++;
                this.condition.signalAll();
                this.waiters -= maxWaiters;
            }
        } finally {
            this.lock.unlock();
        }
    }

    public void run()
    {
        try {
            System.out.println(Thread.currentThread().getName() + " - Entering Barrier...");
            this.await();
            System.out.println(Thread.currentThread().getName() + " - Going to sleep...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " - Woke...");
        System.out.println(Thread.currentThread().getName() + " - Entering Barrier Again...");
        try {
            this.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " - Finished...");
    }

}
