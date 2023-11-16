package nonReutilizavel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier
{
    int awaiters;
    int N;
    ReentrantLock l;
    Condition condition;

    Barrier(int N)
    {
        this.N = N;
        this.awaiters = 0;
        this.l = new ReentrantLock();
        this.condition = l.newCondition();
    }

    void await() throws InterruptedException
    {
        try {
            l.lock();
            this.awaiters++;
            if (this.awaiters < this.N)
            {
                while (this.awaiters < this.N)
                {
                    this.condition.await();
                }
            } else {
                this.condition.signalAll();
            }
            System.out.println("Done.\nN = " + this.awaiters);
        } finally {
            l.unlock();
        }
    }
}
