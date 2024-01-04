package Ex1;

import java.util.ArrayList;
import java.util.List;

class Increment implements Runnable {

  public static void main(String[] args) throws InterruptedException {
    int N = 10;
    List<Thread> threadList = new ArrayList<>();

    for (int i = 0; i < N; i++)
    {
      Thread t = new Thread(new Increment());
      threadList.add(t);
      t.start();
    }

    for (Thread t : threadList)
    {
      t.join();
    }

    System.out.println("Fim.");


  }

  public void run() {
    final long I=100;

    for (long i = 0; i < I; i++)
      System.out.println(i);
  }
}
