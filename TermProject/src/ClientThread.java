import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
    private Socket socket = null;
    private Client client = null;
    private DataInputStream in = null;

    public ClientThread(Client c, Socket s) throws IOException
    {
        client = c;
        socket = s;

        in = new DataInputStream(socket.getInputStream());
        start();
    }

    public void run()
    {
        while (true)
        {
            try
            {
                //System.out.println("5");
                client.sendMessage(in.readUTF());
            }
            catch(IOException i)
            {
                System.out.println("Listening error: " + i.getMessage());
            }
        }
    }
}