package EX4;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
    public static void main(String[] args)
    {
        try
        {
            ServerSocket ss = new ServerSocket(12345);
            ArrayList<Thread> threads = new ArrayList<>();

            while (true)
            {
                Socket socket = ss.accept();
                System.out.println("A new client connected... Creating thread...");

                Thread thread = new Thread(new ServerConnectionHandler(socket));
                thread.start();
                threads.add(thread);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
