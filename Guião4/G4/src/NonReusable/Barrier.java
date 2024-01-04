package NonReusable;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier implements Runnable
{
    private final int maxWaiters;
    private int waiters;
    private ReentrantLock lock;
    private Condition condition;
    public Barrier (int N)
    {
        this.maxWaiters = N;
        this.waiters = 0;
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    void await() throws InterruptedException
    {
        this.lock.lock();
        try {

            this.waiters++; // Incrementing number of waiters
            System.out.println("Entering barrier - number of waiters: " + this.waiters + " of max " + this.maxWaiters);

            if (this.waiters < this.maxWaiters)
            {
                while (this.waiters < this.maxWaiters) // In case the number of waiters is less than the number nedded of maxwaiters then await
                {
                    System.out.println("Waiting for waiters...");
                    this.condition.await(); // When a Thread enters this condition it goes to a "sleep" state and realeases the lock, when signaled it holds the lock again
                }
                System.out.println("I'm free!");
            }
            else // If the number of waiters is equals than the number nedded of maxwaiters then signall the resto of the Threads
            {
                System.out.println("Reached the max number of waiters... Realeasing Threads...");
                this.condition.signalAll();
            }

        } finally {
            this.lock.unlock();
        }
    }

    public void run()
    {
        try {
            this.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
