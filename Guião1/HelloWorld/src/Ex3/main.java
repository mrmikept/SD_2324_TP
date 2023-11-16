package Ex3;

public class main
{
    public static void main(String[] args)
    {
        int N = 10;
        Thread[] threads = new Thread[N];
        Bank bank = new Bank();
        for (int i = 0; i < N; i++)
        {
            Thread thread = new Thread(new Deposit(bank));
            thread.start();
            threads[i] = thread;
        }

        for (int j = 0; j < N; j++)
        {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("The account balance is: " + bank.balance());
    }
}
