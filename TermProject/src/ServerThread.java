import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread
{
    private Server server = null;
    private Socket socket = null;
    private DataInputStream in  = null;
    private DataOutputStream out  = null;
    private String username = null;

    public ServerThread(Server serve, Socket s) throws IOException
    {
        server = serve;
        socket = s;

        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void setUserName(String user)
    {
        username = user;
    }

    //4
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

    //4
    public void sendUser(ArrayList<ServerThread> online)
    {
        try
        {
            //System.out.println("4");
            for (ServerThread st : online)
            {
                out.writeUTF(st.username);
                out.flush();
            }
        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    //2
    public void run()
    {
        System.out.println("Running Server");
        String input = "";

        while (true)
        {
            try
            {
                input = in.readUTF();
                if(input.contains(":"))
                    server.receiveMessage(input);
                else
                {
                    setUserName(input);
                    server.receiveUser(input);
                }
                //line = in.readUTF();
                //System.out.println(in.readUTF());
                //System.out.println("2");
                //server.receiveMessage(in.readUTF());
                //UserInterface.displayMessage(line);
            }
            catch (IOException i)
            {
                System.exit(0);
                System.out.println("run");
                i.printStackTrace();

            }
        }
    }
}
