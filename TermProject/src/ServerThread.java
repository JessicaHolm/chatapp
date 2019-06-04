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
    private volatile boolean flag = true;

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

    public String getUserName()
    {
        return username;
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
            System.out.println("IO error: " + i.getMessage());
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
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void sendLogout(String line)
    {
        try
        {
            //System.out.println("4");
            //for (ServerThread st : online)
            //{
                out.writeUTF(line);
                out.flush();
            //}
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    //2
    public void run()
    {
        System.out.println("Running Server");
        String input = "";

        while (flag)
        {
            try
            {
                input = in.readUTF();
                if(input.contains("1"))
                {
                    System.out.println(input);
                    input = input.replace('1', '\0');
                    System.out.println(input);
                    setUserName(input);
                    //input = input.concat("1");
                    //System.out.println(input);
                    server.receiveUser(input);
                }
                else if(input.contains("2"))
                {
                    //System.out.println(input);
                    //input = input.replace('2', '\0');
                    //setUserName(input);
                    //input = input.concat("2");
                    server.receiveMessage(input);
                }
                else if(input.contains("3"))
                {
                    //input = input.replace('3', '\0');
                    server.logout(input);
                }
                //line = in.readUTF();
                //System.out.println(in.readUTF());
                //System.out.println("2");
                //server.receiveMessage(in.readUTF());
                //UserInterface.displayMessage(line);
            }
            catch (IOException i)
            {
                System.out.println("IO error: " + i.getMessage());
                System.out.println(this);
                server.remove(username);
                stopThread();
            }
        }
    }

    public void stopThread()
    {
        flag = false;
    }
}
