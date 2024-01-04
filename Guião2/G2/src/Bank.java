import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    private static class Account {
        private ReentrantLock lock;
        private int balance;
        Account(int balance) {
            this.lock = new ReentrantLock();
            this.balance = balance;
        }
        int balance() {
            this.lock.lock();
            try {
                return balance;
            }finally {
                this.lock.unlock();
            }
        }
        boolean deposit(int value) {
            this.lock.lock();
            try {
                balance += value;
                return true;
            } finally {
                this.lock.unlock();
            }

        }
        boolean withdraw(int value) {
            this.lock.lock();
            try {
                if (value > balance)
                    return false;
                balance -= value;
                return true;
            } finally {
                this.lock.unlock();
            }

        }
    }

    // Bank slots and vector of accounts
    private int slots;
    private Account[] av;

    public Bank(int n) {
        slots=n;
        av=new Account[slots];
        for (int i=0; i<slots; i++) av[i]=new Account(0);
    }

    // Account balance
    public int balance(int id) {
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
            if (this.withdraw(from,value))
            {
                if (this.deposit(to,value))
                {
                    return true;
                }
            }
            return false;
    }

    public int totalBalance()
    {
            int total = 0;
            for (int i = 0; i < this.slots; i++)
            {
                total += this.av[i].balance();
            }
            return total;
    }



}
