package Ex3;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

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
    private ReentrantReadWriteLock bankLock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = bankLock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = bankLock.writeLock();

    // create account and return account id
    public int createAccount(int balance) {

        Account c = new Account(balance);
        writeLock.lock();
        try {
            int id = nextId;
            nextId += 1;
            map.put(id, c);
            return id;
        } finally {
            writeLock.unlock();
        }
    }

    // close account and return balance, or 0 if no such account
    public int closeAccount(int id) {
        Account c;
        writeLock.lock();
        try {
            c = map.remove(id);
            if (c == null) {
                writeLock.unlock();
                return 0;
            }
            c.AccLock.lock();
        } finally {
            writeLock.lock();
        }
        try {
            return c.balance();
        } finally {
            writeLock.unlock();
        }
    }

    // account balance; 0 if no such account
    public int balance(int id) {
        readLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            readLock.unlock();
            return 0;
        }
        c.AccLock.lock();
        readLock.unlock();
        try {
            return c.balance();
        } finally {
            c.AccLock.unlock();
        }
    }

    // deposit; fails if no such account
    public boolean deposit(int id, int value) {
        readLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            readLock.unlock();
            return false;
        }
        c.AccLock.lock();
        readLock.unlock();
        try {
            return c.deposit(value);
        } finally {
            c.AccLock.unlock();
        }

    }

    // withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        readLock.lock();
        Account c = map.get(id);
        if (c == null)
        {
            readLock.unlock();
            return false;
        }
        c.AccLock.lock();
        readLock.unlock();
        try {
            return c.withdraw(value);
        } finally {
            c.AccLock.unlock();
        }

    }

    // transfer value between accounts;
    // fails if either account does not exist or insufficient balance
    public boolean transfer(int from, int to, int value) {
        Account cMenor, cMaior;
        int menorId = Math.min(from, to);
        int maiorId = Math.max(from,to);
        readLock.lock();

        cMenor = map.get(menorId); // Importa a ordem neste caso.
        cMaior = map.get(maiorId);
        if (cMenor == null || cMaior ==  null)
        {
            readLock.unlock();
            return false;
        }
        cMenor.AccLock.lock();
        cMaior.AccLock.lock();
        readLock.unlock();
        try {
            return cMenor.withdraw(value) && cMaior.deposit(value);
        } finally {
            cMenor.AccLock.unlock();
            cMaior.AccLock.unlock();
        }
    }

    // sum of balances in set of accounts; 0 if some does not exist
    public int totalBalance(int[] ids) {
        readLock.lock();
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
        }
        readLock.unlock();
        return total;
  }

}
