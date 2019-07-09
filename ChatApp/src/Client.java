import java.net.*;
import java.io.*;

public class Client implements Runnable
{
    static DataOutputStream out;
    private UserInterface ui;

    public Client(String address, int port)
    {
        ui = new UserInterface();
        try
        {
            Socket socket = new Socket(address, port);
            System.out.println("Connected");

            out = new DataOutputStream(socket.getOutputStream());

            new ClientThread(this, socket);
            Thread thread = new Thread(this);
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
    }

    public static void fromUI(String msg)
    {
        try
        {
            out.writeUTF(msg);
            out.flush();
        }
        catch(IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
            //stop();
        }
    }

    public void sendMessage(String line)
    {
        ui.displayMessage(line);
    }

    public void sendUser(String user)
    {
        ui.addUser(user);
    }

    public void sendLogout(String user)
    {
        ui.removeUser(user);
    }

    public void sendPMessage(String line)
    {
        ui.displayPMessage(line);
    }

    public void sendLoginPopup(String line)
    {
        ui.loginPopup(line);
    }

    public void sendLogoutPopup(String line)
    {
        ui.logoutPopup(line);
    }

    public static void main(String[] args)
    {
        new Client("127.0.0.1", 5000);
    }
}
