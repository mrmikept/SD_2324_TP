package Ex3;

public class Deposit implements Runnable
{
    private Bank bank;

    public Deposit(Bank bank)
    {
        this.bank = bank;
    }
    public void run()
    {
        int I = 1000;
        for (int i = 0; i < I; i++)
        {
            this.bank.deposit(100);
        }
    }
}
