package Ex1e2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(9090);

        while (true)
        {
            Socket client = socket.accept();
            System.out.println("New client.");
            BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter output = new PrintWriter(client.getOutputStream());

            String message;
            int sum = 0;
            int messageCount = 0;
            while ((message = input.readLine()) != null)
            {
                System.out.println("New message");
                sum += Integer.parseInt(message);
                messageCount++;
                output.println(sum);
                output.flush();
            }

            int med = sum/messageCount;
            output.println(med);
            output.flush();

            client.shutdownInput();
            client.shutdownOutput();
            client.close();
        }
    }
}
