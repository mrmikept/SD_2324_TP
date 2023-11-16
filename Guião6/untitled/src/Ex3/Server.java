package Ex3;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Server
{
    private int totalValue;
    private int messageCount;
    private ReentrantReadWriteLock lock;
    private ReentrantReadWriteLock.WriteLock writeLock;
    private ReentrantReadWriteLock.ReadLock readLock;

    public Server()
    {
        this.totalValue = 0;
        this.messageCount = 0;
        this.lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
    }

    public void addValue(int value)
    {
        try {
            this.writeLock.lock();
            this.totalValue += value;
            System.out.println("Total value received: " + this.totalValue);
        } finally {
            this.writeLock.unlock();
        }
    }

    public void addMessage()
    {
        try
        {
            this.writeLock.lock();
            this.messageCount++;
            System.out.println("Total Number of messages: " + this.messageCount);
        } finally {
            this.writeLock.unlock();
        }
    }

    public int average()
    {
        try{
            this.readLock.lock();
            return (this.totalValue / this.messageCount);
        } finally {
            this.readLock.unlock();
        }
    }

    public static void main(String[] args)
    {
        Server server = new Server();
        try
        {
            ServerSocket ss = new ServerSocket(12345);
            ArrayList<Thread> threads = new ArrayList<>();

            while (true)
            {
                Socket socket = ss.accept();
                System.out.println("A new client connected... Creating thread...");

                Thread thread = new Thread(new ServerConnectionHandler(server, socket));
                thread.start();
                threads.add(thread);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
