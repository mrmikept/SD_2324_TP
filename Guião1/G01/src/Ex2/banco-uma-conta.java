package Ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class Bank implements Runnable
{
  Account account;

  public Bank()
  {
    this.account = new Account(0);
  }

  public static void main(String[] args) throws InterruptedException {
      int N = 10;
      Bank bank = new Bank();
      List<Thread> threadList = new ArrayList<>();
      for (int i = 0; i < N; i++)
      {
        Thread t = new Thread(bank);
        threadList.add(t);
        t.start();
      }

      for (Thread thread : threadList)
      {
        thread.join();
      }

      System.out.println("Final Amount: " + bank.account.balance);

  }

  @Override
  public void run()
  {
      int I = 1000;
      for (int i = 0; i < I; i++)
      {
          this.account.deposit(100);
      }
  }

  private static class Account {
    private int balance;
    private ReentrantLock l;
    Account(int balance) {
      this.balance = balance;
      this.l = new ReentrantLock();
    }
    int balance() {
      this.l.lock();
      try {
        return balance;
      } finally {
        this.l.unlock();
      }
    }
    boolean deposit(int value)
    {
      this.l.lock();
      try {
        balance += value;
        return true;
      } finally {
        this.l.unlock();
      }
    }
  }

  // Our single account, for now
  private Account savings = new Account(0);
  private ReentrantLock lock = new ReentrantLock();

  // Account balance
  public int balance() {
    this.lock.lock();
    try{
      return savings.balance();
    } finally {
      this.lock.unlock();
    }
  }

  // Deposit
  boolean deposit(int value)
  {
    this.lock.lock();
    try {
      return savings.deposit(value);
    } finally {
      this.lock.unlock();
    }
  }
}
