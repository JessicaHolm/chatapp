import java.io.*;
import java.net.*;

public class ServerThread extends Thread
{
    private Socket socket = null;
    private DataInputStream in  = null;
    private DataOutputStream out  = null;
    private int ID = -1;

    public ServerThread(Socket s)
    {
        socket = s;
    }

    public void sendMessage(String line)
    {
        try
        {
            out.writeUTF(line);
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public void run()
    {
        System.out.println("Running Server with Thread number:" + ID);

        while (true)
        {
            try
            {
                //line = in.readUTF();
                System.out.println(in.readUTF());
                //UserInterface.displayMessage(line);
            }
            catch (IOException i)
            {
                System.out.println("run");
                i.printStackTrace();
            }
        }
    }
}
