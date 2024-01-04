package Ex3e4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9090);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        String message;

        while ((message = input.readLine()) != null)
        {
            output.println(message);
            output.flush();

            System.out.println(socket_in.readLine());
        }
        socket.shutdownOutput();

        System.out.println("Total med: " + socket_in.readLine());
        socket.shutdownInput();
        socket.close();

    }
}
