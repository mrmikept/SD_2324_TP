package Ex3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("localhost",12345);

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socket_out = new PrintWriter(socket.getOutputStream());

            String message;

            while ((message = input.readLine()) != null)
            {
                socket_out.println(message);
                socket_out.flush();
                System.out.println("current sum is: " + socket_in.readLine());
            }
            socket.shutdownOutput();
            System.out.println("The total average is: " + socket_in.readLine());

            socket.shutdownInput();
            socket.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
