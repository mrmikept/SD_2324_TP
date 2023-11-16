package Ex1;

class Increment implements Runnable {
    public void run() {
        final long I=100;

        for (long i = 0; i < I; i++)
            System.out.println(i);
    }

    public static void main(String[] args)
    {
        int N = 10;
        Thread[] threads = new Thread[10];
        for (int i = 0; i < N; i++)
        {
            Thread t1 = new Thread(new Increment());
            t1.start();
            threads[i] = t1;
        }
        for (int j = 0; j < N; j++)
        {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Fim");
    }
}