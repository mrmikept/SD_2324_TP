package ExAdicional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main
{
    public static void main(String[] args) throws InterruptedException {
        int N = 10;
        Agreement agreement = new Agreement(N);
        List<Thread> threadList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < N * 2; i++)
        {
            Thread t = new Thread(() -> {
                int propose = random.nextInt(0,1000);
                System.out.println("Proposing Value: " + propose);
                try {
                    System.out.println("Max proposed value: " + agreement.propose(propose));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                propose = random.nextInt(0,1000);
                System.out.println("Proposing new value: " + propose);
                try {
                    System.out.println("Max proposed value: " + agreement.propose(propose));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threadList.add(t);
            t.start();
        }

        for (Thread t : threadList)
        {
            t.join();
        }

        System.out.println("Fim.");
    }
}
