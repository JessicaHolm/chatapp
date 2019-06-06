import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServerThread extends Thread
{
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private volatile boolean flag = true;

    public ServerThread(Server s, Socket sk) throws IOException
    {
        server = s;

        in = new DataInputStream(new BufferedInputStream(sk.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(sk.getOutputStream()));
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
                if(input.contains("1"))
                {
                    input = input.substring(1);
                    setUserName(input);
                    server.receiveUser();
                }
                else if(input.contains("2"))
                    server.receiveMessage(input);
                else if(input.contains("3"))
                    server.logout(input);
                else if(input.contains("4"))
                    server.receivePMessage(input);
                else if(input.contains("5"))
                    server.receiveLoginPopup(input);
                else if(input.contains("6"))
                    server.receiveLogoutPopup(input);
            }
            catch (IOException i)
            {
                System.out.println("IO error: " + i.getMessage());
                server.remove(username);
                stopThread();
            }
        }
    }

    public void setUserName(String user)
    {
        username = user;
    }

    public String getUserName()
    {
        return username;
    }

    public void sendMessage(String line)
    {
        try
        {
            out.writeUTF(line);
            out.flush();
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void sendUser(ArrayList<ServerThread> online)
    {
        try
        {
            for (ServerThread st : online)
            {
                out.writeUTF(st.username);
                out.flush();
            }
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void sendPMessage(String line)
    {
        try
        {
            out.writeUTF(line);
            out.flush();
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void stopThread()
    {
        flag = false;
    }
}
