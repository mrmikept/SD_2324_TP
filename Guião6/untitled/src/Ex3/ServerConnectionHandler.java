package Ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnectionHandler implements Runnable
{
    Server server;
    private Socket socket;
    private int totalValue;
    private int messageCount;


    public ServerConnectionHandler(Server server, Socket socket)
    {
        this.server = server;
        this.socket = socket;
        this.totalValue = 0;
        this.messageCount = 0;
    }

    public int addValue(int value)
    {
        this.totalValue += value;
        return this.totalValue;
    }

    public int average()
    {
        return (this.totalValue / this.messageCount);
    }

    @Override
    public void run()
    {
        try {
            while (true)
            {
                BufferedReader socket_in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                PrintWriter socket_out = new PrintWriter(socket.getOutputStream());

                String message;

                while ((message = socket_in.readLine()) != null)
                {
                    this.server.addMessage();
                    this.messageCount++;
                    int value = this.addValue(Integer.valueOf(message));
                    this.server.addValue(value);
                    socket_out.println(value);
                    socket_out.flush();
                }

                socket_out.println(this.server.average());
                socket_out.flush();
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
                break;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
