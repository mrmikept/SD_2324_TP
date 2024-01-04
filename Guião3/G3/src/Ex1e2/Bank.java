package Ex1e2;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

class Bank {

    private static class Account {
        private int balance;
        ReentrantLock accLock;
        Account(int balance) {
            this.balance = balance;
            this.accLock = new ReentrantLock();
        }
        int balance() { return balance; }
        boolean deposit(int value) {
            balance += value;
            return true;
        }
        boolean withdraw(int value) {
            if (value > balance)
                return false;
            balance -= value;
            return true;
        }
    }

    private Map<Integer, Account> map = new HashMap<Integer, Account>();
    private int nextId = 0;
    private ReentrantLock banckLock = new ReentrantLock();

    // create account and return account id
    public int createAccount(int balance) {
        this.banckLock.lock();
        try {
            Account c = new Account(balance);
            int id = nextId;
            nextId += 1;
            map.put(id, c);
            return id;
        } finally {
            this.banckLock.unlock();
        }
    }

    // close account and return balance, or 0 if no such account
    public int closeAccount(int id)
    {
        Account c;
        this.banckLock.lock();
        try {
            c = map.remove(id);
            if (c == null)
                return 0;
            c.accLock.lock();
        } finally {
            this.banckLock.unlock();
        }
        try {
            return c.balance();
        } finally {
            c.accLock.unlock();
        }
    }

    // account balance; 0 if no such account
    public int balance(int id) {
        Account c;
        this.banckLock.lock();
        try {
            c = map.get(id);
            if (c == null)
                return 0;
            c.accLock.lock();
        } finally {
            this.banckLock.unlock();
        }
        try {
            return c.balance();
        } finally {
            c.accLock.unlock();
        }
    }

    // deposit; fails if no such account
    public boolean deposit(int id, int value) {
        Account c;
        this.banckLock.lock();
        try {
            c = map.get(id);
            if (c == null)
                return false;
            c.accLock.lock();
        } finally {
            this.banckLock.unlock();
        }
        try {
            return c.deposit(value);
        } finally {
            c.accLock.unlock();
        }
    }

    // withdraw; fails if no such account or insufficient balance
    public boolean withdraw(int id, int value) {
        Account c;
        this.banckLock.lock();
        try {
            c = map.get(id);
            if (c == null)
                return false;
            c.accLock.lock();
        } finally {
            this.banckLock.unlock();
        }
        try
        {
            return c.withdraw(value);
        } finally {
            c.accLock.unlock();
        }
    }

    // transfer value between accounts;
    // fails if either account does not exist or insufficient balance
    public boolean transfer(int from, int to, int value) {
        Account cfrom, cto;
        this.banckLock.lock();
        try {
            cfrom = map.get(from);
            cto = map.get(to);
            if (cfrom == null || cto ==  null)
                return false;
            cfrom.accLock.lock();
            cto.accLock.lock();
        } finally {
            this.banckLock.unlock();
        }
        try {
            return cfrom.withdraw(value) && cto.deposit(value);
        } finally {
            cfrom.accLock.unlock();
            cto.accLock.unlock();
        }
    }

    // sum of balances in set of accounts; 0 if some does not exist
    public int totalBalance(int[] ids) {
        this.banckLock.lock();
        Queue<Account> accounts = new ArrayDeque<>();
        try {
            for (int i : ids) {
                Account c = map.get(i);
                if (c != null) {
                    c.accLock.lock();
                    accounts.offer(c);
                }
            }
        } finally {
            this.banckLock.unlock();
        }
        int total = 0;
        for (int i : ids) {
            Account c = accounts.poll();
            if (c != null)
            {
                total += c.balance();
                c.accLock.unlock();
            }
        }
        return total;
  }

}
