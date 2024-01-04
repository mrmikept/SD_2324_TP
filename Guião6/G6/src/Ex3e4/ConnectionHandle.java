package Ex3e4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandle implements Runnable
{
    Server server;
    Socket client;

    public ConnectionHandle(Server server, Socket client)
    {
        this.server = server;
        this.client = client;
    }

    public void run()
    {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            String message;
            int sum = 0, count = 0;
            while ((message = input.readLine()) != null)
            {
                int value = Integer.parseInt(message);
                sum += value;
                server.addSum(value);

                writer.println(sum);
                writer.flush();
            }
            writer.println(server.getMed());
            writer.flush();

            client.shutdownOutput();
            client.shutdownInput();
            client.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
