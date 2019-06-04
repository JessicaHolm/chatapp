import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
    private Socket socket = null;
    private Client client = null;
    private DataInputStream in = null;
    private volatile boolean flag = true;

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

        while (flag)
        {
            try
            {
                //System.out.println("5");
                input = in.readUTF();
                if(input.contains("2"))
                {
                    input = input.substring(1);
                    client.sendMessage(input);
                }
                else if(input.contains("3"))
                {
                    input = input.substring(1);
                    client.sendLogout(input);
                }
                else if(input.contains("4"))
                {
                    input = input.substring(1);
                    client.sendPMessage(input);
                }
                else
                    client.sendUser(input);
            }
            catch(IOException i)
            {
                System.out.println("IO error: " + i.getMessage());
                stopThread();
            }
        }
    }

    public void stopThread()
    {
        flag = false;
    }
}