package myEchoClientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TestingClient
{
    public static void main(String[] args) {
        try
        {
            Socket socket = new Socket("localhost",12345);

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // Para ler o input do utilizador (teclado)
            BufferedReader socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socket_out = new PrintWriter(socket.getOutputStream());

            String message;
            while ((message = input.readLine()) != null)
            {
                socket_out.println(message);
                socket_out.flush();

                String response = socket_in.readLine(); // Para ler a resposta do servidor
                System.out.println(response);
            }
            socket.shutdownOutput();
            socket.shutdownInput();
            socket.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
