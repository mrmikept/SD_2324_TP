import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private static class Account {
        private int balance;
        private ReentrantLock lock;
        Account(int balance)
        {
            this.balance = balance;
            this.lock = new ReentrantLock();
        }

        int balance()
        {
            lock.lock();
            try
            {
                return balance;
            }
            finally {
                lock.unlock();
            }
        }
        boolean deposit(int value)
        {
            lock.lock();
            try
            {
                balance += value;
            }
            finally {
                lock.unlock();
                return true;
            }
        }
        boolean withdraw(int value)
        {
            lock.lock();
            try
            {
                if (value > balance)
                    return false;
                balance -= value;
                return true;
            }
            finally {
                lock.unlock();
            }
        }
    }

    // Bank slots and vector of accounts
    private int slots;
    private Account[] av;
    private ReentrantLock lock;

    public Bank(int n)
    {
        slots=n;
        av=new Account[slots];
        lock = new ReentrantLock();
        for (int i=0; i<slots; i++) av[i]=new Account(0);
    }

    // Account balance
    public int balance(int id)
    {
        if (id < 0 || id >= slots)
            return 0;
        return av[id].balance();
    }

    // Deposit
    public boolean deposit(int id, int value) {
        if (id < 0 || id >= slots)
            return false;
        return av[id].deposit(value);
    }

    // Withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        if (id < 0 || id >= slots)
            return false;
        return av[id].withdraw(value);
    }

    public boolean transfer(int from, int to, int value)
    {
        int menorId = Math.min(from,to);
        int maiorId = Math.max(from,to);

        av[menorId].lock.lock();
        av[maiorId].lock.lock();

        try {
            return this.withdraw(from,value) && this.deposit(to,value);
        } finally {
            av[menorId].lock.unlock();
            av[maiorId].lock.unlock();
        }
    }

    public int totalBalance()
    {
        int value = 0;
        for (int i = 0; i < slots; i++)
        {
            av[i].lock.lock();
        }
        for (int i = 0; i < slots; i++)
        {
            value += av[i].balance();
            av[i].lock.unlock();
        }
        return value;
    }
}
