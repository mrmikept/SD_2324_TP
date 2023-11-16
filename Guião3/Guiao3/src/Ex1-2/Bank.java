import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Bank {

    private static class Account {
        private int balance;
        public ReentrantLock AccLock;
        Account(int balance)
        {
            this.AccLock = new ReentrantLock();
            this.balance = balance;
        }

        int balance()
        {
            return balance;
        }

        boolean deposit(int value)
        {
            balance += value;
            return true;
        }

        boolean withdraw(int value)
        {
            if (value > balance)
                return false;
            balance -= value;
            return true;
        }
    }

    private Map<Integer, Account> map = new HashMap<Integer, Account>();
    private int nextId = 0;
    private ReentrantLock bankLock = new ReentrantLock();

    // create account and return account id
    public int createAccount(int balance) {

        Account c = new Account(balance);
        bankLock.lock();
        try {
            int id = nextId;
            nextId += 1;
            map.put(id, c);
            return id;
        } finally {
            bankLock.unlock();
        }
    }

    // close account and return balance, or 0 if no such account
    public int closeAccount(int id) {
        Account c;
        bankLock.lock();
        try {
            c = map.remove(id);
            if (c == null) {
                bankLock.unlock();
                return 0;
            }
            c.AccLock.lock();
        } finally {
            bankLock.unlock();
        }
        try {
            return c.balance();
        } finally {
            c.AccLock.unlock();
        }
    }

    // account balance; 0 if no such account
    public int balance(int id) {
        bankLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            bankLock.unlock();
            return 0;
        }
        c.AccLock.lock();
        bankLock.unlock();
        try {
            return c.balance();
        } finally {
            c.AccLock.unlock();
        }

    }

    // deposit; fails if no such account
    public boolean deposit(int id, int value) {
        bankLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            bankLock.unlock();
            return false;
        }
        c.AccLock.lock();
        bankLock.unlock();
        try {
            return c.deposit(value);
        } finally {
            c.AccLock.unlock();
        }

    }

    // withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        bankLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            bankLock.unlock();
            return false;
        }
        c.AccLock.lock();
        bankLock.unlock();
        try {
            return c.withdraw(value);
        } finally {
            c.AccLock.unlock();
        }

    }

    // transfer value between accounts;
    // fails if either account does not exist or insufficient balance
    public boolean transfer(int from, int to, int value) {
        Account cfrom, cto;
        bankLock.lock();
        cfrom = map.get(from);
        cto = map.get(to);
        if (cfrom == null || cto ==  null)
        {
            bankLock.unlock();
            return false;
        }
        cfrom.AccLock.lock();
        cto.AccLock.lock();
        bankLock.unlock();
        try {
            return cfrom.withdraw(value) && cto.deposit(value);
        } finally {
            cfrom.AccLock.unlock();
            cto.AccLock.unlock();
        }
    }

    // sum of balances in set of accounts; 0 if some does not exist
    public int totalBalance(int[] ids) {
        bankLock.lock();
        int total = 0;
        for (int i : ids) {
            map.get(i).AccLock.lock();
        }
        for (int i : ids) {
            Account c = map.get(i);
            if (c == null)
                return 0;
            total += c.balance();
            c.AccLock.unlock();
        }2
        bankLock.unlock();
        return total;
  }

}
