package Reutilizavel;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barrier
{
    int season;
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

    /* Apesar dos prints estarem todos desordenados no final, o exercicio está correto, pois é mais rápido criarr e executar as threads do que realizar os prints. */
    void await() throws InterruptedException
    {
        try {
            l.lock();
            int myseason = this.season;
            this.awaiters++;
            if (this.awaiters < this.N)
            {
                while (myseason == this.season)
                {
                    System.out.println("Thread waiting on season: " + myseason);
                    this.condition.await();
                }
            } else {
                this.condition.signalAll();
                System.out.println("Signaling all threads on season: " + myseason + "; waiters: " + this.awaiters);
                season++;
                awaiters = 0;
            }
            System.out.println("Done on season: " + myseason);
        } finally {
            l.unlock();
        }
    }
}
