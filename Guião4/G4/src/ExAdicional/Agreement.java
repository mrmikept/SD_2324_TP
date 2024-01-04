package ExAdicional;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Agreement
{
    int maxWaiters;
    int waiters;
    int maxPropose;
    int epoch;
    ReentrantLock lock;
    Condition condition;

    Agreement(int N)
    {
        this.maxWaiters = N;
        this.waiters = 0;
        this.maxPropose = 0;
        this.epoch = 0;
        this.lock = new ReentrantLock();
        this.condition = this.lock.newCondition();
    }

    int propose(int choice) throws InterruptedException
    {
        this.lock.lock();
        try {
            this.waiters++;
            int myEpoch = this.epoch;
            if (this.maxPropose < choice)
            {
                this.maxPropose = choice;
            }
            if (this.waiters < this.maxWaiters)
            {
                while (myEpoch == this.epoch)
                {
                    this.condition.await();
                }
            }
            else
            {
                this.epoch++;
                this.condition.signalAll();
            }
            this.waiters--;
            return this.maxPropose;
        } finally {
            if (this.waiters == 0) // If the number of waiters is 0 then reset the proposed value...
            {
                this.maxPropose = 0;
            }
            this.lock.unlock();
        }
    }
}
