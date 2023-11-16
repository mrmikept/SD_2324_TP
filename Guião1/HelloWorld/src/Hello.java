public class Hello implements Runnable {

    public void run()
    {
        System.out.println("Hello world from thread: " + Thread.currentThread().getId());
    }

    public static void main (String[] args) throws InterruptedException { // Tudo o que está na main é executada pela thread principal
        System.out.println("Hello World from main(): " + Thread.currentThread().getId());

        Thread t1 = new Thread(new Hello()); //Para criar uma thread da classe Hello()
        t1.start(); // Para lançar a thread
        Thread.sleep(1000); //Para fazer a thread da main aguardar 1000 ms
        System.out.println("Another print..."); // Print para o que acontece primeiro, a thread t1 executar ou este print
        try {
            t1.join(); // Recolher a thread
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
