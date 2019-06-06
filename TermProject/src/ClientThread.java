import java.net.*;
import java.io.*;

public class ClientThread extends Thread
{
    private Client client;
    private DataInputStream in;
    private volatile boolean flag = true;

    public ClientThread(Client c, Socket s) throws IOException
    {
        client = c;

        in = new DataInputStream(s.getInputStream());
        start();
    }

    @Override
    public void run()
    {
        String input;

        while (flag)
        {
            try
            {
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
                else if(input.contains("5"))
                {
                    input = input.substring(1);
                    client.sendLoginPopup(input);
                }
                else if(input.contains("6"))
                {
                    input = input.substring(1);
                    client.sendLogoutPopup(input);
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