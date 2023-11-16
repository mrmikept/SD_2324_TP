package myEchoClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TestingServer
{

    public static void main(String[] args) throws IOException {
        try
        {
            ServerSocket ss = new ServerSocket(12345);

            while (true)
            {
                Socket socket = ss.accept(); // O servidor fica "preso" até receber uma conexão
                System.out.println("New client is connected....");
                BufferedReader socket_in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socket_out = new PrintWriter(socket.getOutputStream());

                String message;

                while ((message = socket_in.readLine()) != null) // O servidor fica "preso" até receber uma mensagem do cliente
                {
                    Thread.sleep(1000);
                    socket_out.println(message); // Envia a mensagem recebida pelo cliente para o proprio cliente
                    socket_out.flush(); // Para obrigar que o que foi escrito no socket é imediatamente enviado e não ficar em cache
                }

                socket.shutdownInput(); // Fecha as streams de input
                socket.shutdownOutput(); // Fecha as streams de output
                socket.close(); // Fecha o socket

            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }

}
