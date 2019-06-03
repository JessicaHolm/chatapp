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

    //5
    public void run()
    {
        String input = "";

        while (true)
        {
            try
            {
                //System.out.println("5");
                input = in.readUTF();
                //System.out.println(input);
                if(input.contains(":"))
                {
                    client.sendMessage(input);
                }
                else
                    client.sendUser(input);
            }
            catch(IOException i)
            {
                System.out.println("Listening error: " + i.getMessage());
                //System.exit(0);
            }
        }
    }
}