import java.io.*;
import java.net.*;

public class ServerThread extends Thread
{
    private Server server = null;
    private Socket socket = null;
    private DataInputStream in  = null;
    private DataOutputStream out  = null;
    static String username = null;

    public ServerThread(Server serve, Socket s) throws IOException
    {
        server = serve;
        socket = s;

        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public static void setUserName(String user)
    {
        username = user;
    }

    public void sendMessage(String line)
    {
        //System.out.println("4");
        try
        {
            //System.out.println("4");
            out.writeUTF(line);
            out.flush();
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public void run()
    {
        System.out.println("Running Server");

        while (true)
        {
            try
            {
                //line = in.readUTF();
                //System.out.println(in.readUTF());
                //System.out.println("2");
                server.receiveMessage(username, in.readUTF());
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
