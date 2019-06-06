import java.net.*;
import java.io.*;

public class Server implements Runnable
{
    private ServerSocket server = null;
    private Thread thread = null;

    Server(int port)
    {
        try
        {
            server = new ServerSocket((port));
            System.out.println("Server started");

            thread = new Thread(this);
            thread.start();

        }
        catch(IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    @Override
    public void run()
    {
        while (thread != null)
        {
            try
            {
                System.out.println("Waiting for a client ...");
                ServerThread st = new ServerThread(this, server.accept());
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

    public synchronized void receiveMessage(String input)
    {
        for (ServerThread st : List.online)
            st.sendMessage(input);
    }

    public synchronized void receiveUser()
    {
        for (ServerThread st : List.online)
            st.sendUser(List.online);
    }

    public synchronized void receiveLoginPopup(String user)
    {
        String temp = user.substring(1);
        for (ServerThread st : List.online)
        {
            if(!st.getUserName().equals(temp))
                st.sendMessage(user);
        }
    }

    public synchronized void receiveLogoutPopup(String user)
    {
        String temp = user.substring(1);
        for (ServerThread st : List.online)
        {
            if(!st.getUserName().equals(temp))
                st.sendMessage(user);
        }
    }

    public synchronized void logout(String user)
    {
        for (ServerThread st : List.online)
            st.sendMessage(user);
    }

    public synchronized void receivePMessage(String input)
    {
        int indexSender = input.indexOf(':');
        int indexRecipient = input.indexOf(';');
        String sender = input.substring(1, indexSender);
        String recipient = input.substring(indexRecipient + 1);
        input = input.substring(0, indexRecipient);

        for (ServerThread st : List.online)
        {
            if(st.getUserName().equals(recipient) || st.getUserName().equals(sender))
                st.sendPMessage(input);
        }
    }

    public synchronized void remove(String username)
    {
        for (int i = 0; i < List.online.size(); i++)
        {
            ServerThread st = List.online.get(i);
            if(st.getUserName().equals(username))
                List.online.remove(st);
        }
    }

    public static void main(String[] args)
    {
        new Server(5000);
    }
}