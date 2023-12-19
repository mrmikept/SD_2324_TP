import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FramedConnection implements AutoCloseable
{
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    ReentrantLock readLock;
    ReentrantLock writeLock;

    public FramedConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());

        this.writeLock = new ReentrantLock();
        this.readLock = new ReentrantLock();
    }

    public void send(byte[] data)
    {
        writeLock.lock();
        try
        {
            output.writeInt(data.length);
            output.write(data);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public byte[] receive()
    {
        readLock.lock();
        try {
            byte[] data = new byte[input.readInt()]; // Cria um byte array com o tamanho da mensagem
            input.readFully(data); //Lê o ‘byte’ array da stream de dados.
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public void close() throws Exception
    {
        socket.close();
    }
}
