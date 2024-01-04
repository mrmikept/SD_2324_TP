import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class TaggedConnection implements AutoCloseable
{
    Socket socket;
    FramedConnection framedConnection;

    public TaggedConnection(Socket socket) throws IOException {
        this.socket = socket;
        this.framedConnection = new FramedConnection(socket);
    }

    public void send(Frame frame)
    {
        this.send(frame.tag,frame.data);
    }

    public void send(int tag, byte[] data)
    {
        
    }

    public Frame receive()
    {
        return new Frame();
    }

    @Override
    public void close() throws Exception
    {
        this.socket.close();
    }

    public static class Frame
    {
        public final int tag;
        public final byte[] data;

        public Frame(int tag, byte[] data)
        {
            this.tag = tag;
            this.data = data;
        }
    }

}
