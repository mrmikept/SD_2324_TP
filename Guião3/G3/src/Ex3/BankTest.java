package Ex3;

import java.util.Random;

public class BankTest {

    private static class Closer implements Runnable {
        Bank b;
        int s; // Number of accounts

        public Closer(Bank b, int s)
        {
            this.b = b;
            this.s = s;
        }

        public void run()
        {
            Random random = new Random();
            int lostValue = 0;
            for (int m = 0; m < s/2; m++)
            {
                int accId = random.nextInt(10);
                lostValue += b.closeAccount(accId);
            }
            System.out.println("Close accounts balance: " + lostValue);
        }

    }

    private static class Mover implements Runnable {
        Bank b;
        int s; // Number of accounts

        public Mover(Bank b, int s) { this.b=b; this.s=s; }

        public void run() {
            final int moves=100000;
            int from, to;
            Random rand = new Random();

            for (int m=0; m<moves; m++) {
                from=rand.nextInt(s); // Get one
                while ((to=rand.nextInt(s))==from); // Slow way to get distinct
          b.transfer(from,to,1);
        }
      }
    }

    public static void main(String[] args) throws InterruptedException {
        final int N=10;

        Bank b = new Bank();
        int[] accounts = new int[N];

        for (int i=0; i<N; i++)
            accounts[i] = b.createAccount(1000);

        System.out.println("Total before mover: " + b.totalBalance(accounts));

        Thread t1 = new Thread(new Mover(b,10)); 
        Thread t2 = new Thread(new Mover(b,10));

        t1.start(); t2.start(); t1.join(); t2.join();

        System.out.println("Total after mover: " + b.totalBalance(accounts));

        Thread t3 = new Thread(new Closer(b,N));
        t3.start();
        t3.join();

        System.out.println("Total after closing accounts: " + b.totalBalance(accounts));
  }
}
