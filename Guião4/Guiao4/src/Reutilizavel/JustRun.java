package Reutilizavel;

public class JustRun implements Runnable
{
    Barrier b;

    public JustRun(Barrier b)
    {
        this.b = b;
    }

    @Override
    public void run()
    {
        try {
            this.b.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
