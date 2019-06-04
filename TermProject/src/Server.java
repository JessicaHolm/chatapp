import java.net.*;
import java.io.*;

public class Server implements Runnable
{
    private ServerSocket serverSoc = null;
    private Thread thread = null;
    //******************List list = new List();
    //private ServerThread[] clients = new ServerThread[10];
    //private int clientCount = 0;

    //public ArrayList<String> regs = new ArrayList<>();
    //public static int f = 0;
    //public static Server server = new Server();
    //public static ArrayList<String> regs = new ArrayList<String>();
    //ArrayList<ServerThread> online = new ArrayList<>();
    //public static int f = 0;

    Server(int port)
    {
        try
        {
            serverSoc = new ServerSocket((port));
            System.out.println("Server started");

            //*****************if (thread == null)
            //{
                thread = new Thread(this);
                thread.start();
            //}

            //System.out.println("Waiting for a client ...");


        }
        catch(IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void run()
    {
        while (thread != null)
        {
            try
            {
                System.out.println("Waiting for a client ...");
                ServerThread st = new ServerThread(this, serverSoc.accept());
                List.online.add(st);
                st.start();
                System.out.println("Client accepted");
            }
            catch (IOException i)
            {
                System.out.println("IO error: " + i.getMessage());
            }
        }
    }

    /*
    public void startServer(int port)
    {
        try
        {
            serverSoc = new ServerSocket((port));
            System.out.println("Server started");

            if (thread == null)
            {  thread = new Thread(this);
                thread.start();
            }

            //System.out.println("Waiting for a client ...");


        }
        catch(IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }
     */

    //3
    public synchronized void receiveMessage(String input)
    {
        //System.out.println("3");

        for (ServerThread st : List.online)
        {
                //System.out.println("hi");
                //System.out.println(st);
            st.sendMessage(input);
        }

    }

    //3
    public synchronized void receiveUser()
    {
        for (ServerThread st : List.online)
        {
            st.sendUser(List.online);
            //UserInterface.updateUserList(user);
        }
    }

    public synchronized void logout(String user)
    {
        for (ServerThread st : List.online)
        {
            st.sendLogout(user);
            //UserInterface.updateUserList(user);
        }
    }

    public synchronized void receivePMessage(String input)
    {
        int indexSender = input.indexOf(':');
        int indexRecipient = input.indexOf(';');
        String sender = input.substring(1, indexSender);
        String recipient = input.substring(indexRecipient + 1);
        input = input.substring(0, indexRecipient);
        System.out.println(sender);
        System.out.println(recipient);
        //System.out.println("3");
        for (ServerThread st : List.online)
        {
            if(st.getUserName().equals(recipient) || st.getUserName().equals(sender))
                //System.out.println("hi");
                //System.out.println(st);
                st.sendPMessage(input);
        }

    }

    public synchronized void remove(String username)
    {
        //for (ServerThread st : List.online)
        for (int i = 0; i < List.online.size(); i++)
        {
            ServerThread st = List.online.get(i);
            if(st.getUserName().equals(username))
                List.online.remove(st);
        }
    }

    public static void main(String[] args)
    {
        //List list = new List();
        //list.readFromFile();
        new Server(5000);

        //server.startServer(5000);
        //Server.server.startServer(5000);
        //System.out.println(regs.get(0));
        //System.out.println(f);
        //Server.server.readFromFile();
        //System.out.println("Closing connection");
        //writeToFile();
    }
}
