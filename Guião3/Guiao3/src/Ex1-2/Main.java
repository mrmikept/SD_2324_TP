public class Main {

    public void run() {

    }
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.createAccount(100);
        bank.createAccount(500);

        Thread t1 = new Thread();
        Thread t2 = new Thread();
    }
}