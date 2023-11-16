package Reutilizavel;
public class Main
{

    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        Thread[] threads = new Thread[N];
        Barrier b = new Barrier(5);
        for (int i = 0; i < N; i++)
        {
            Thread thread = new Thread(new JustRun(b));
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
        System.out.println("Main done");
    }
}