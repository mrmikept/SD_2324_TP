package Ex3e4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Server
{
    private int totalCount = 0;
    private int totalSum = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void addSum(int value)
    {
        this.lock.lock();
        try {
            totalSum += value;
            totalCount++;
        } finally {
            this.lock.unlock();
        }
    }

    public int getMed()
    {
        this.lock.lock();
        try {
            return this.totalSum / this.totalCount;
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Thread> threads = new ArrayList<>();
        ServerSocket socket = new ServerSocket(9090);
        Server server = new Server();
        while (true)
        {
            Socket client = socket.accept();
            Thread thread = new Thread(new ConnectionHandle(server,client));
            thread.start();
            threads.add(thread);
        }
    }

}
