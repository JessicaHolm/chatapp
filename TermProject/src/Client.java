import java.net.*;
import java.io.*;

public class Client implements Runnable
{
    private Socket socket = null;
    private Thread thread = null;
    private ClientThread client = null;
    private BufferedReader input = null;
    //private DataInputStream input = null;
    //static BufferedWriter out = null;
    static DataOutputStream out;
    private UserInterface ui;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        ui = new UserInterface();
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            //start();

            // takes input from terminal
            input = new BufferedReader(new InputStreamReader(System.in));
            //input = new DataInputStream(System.in);

            // sends output to the socket
            //out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            //if (thread == null)
            //{
                client = new ClientThread(this, socket);
                thread = new Thread(this);
                thread.start();
            //}
        }
        catch(IOException i)
        {
            //System.out.println("init");
            System.out.println("IO error: " + i.getMessage());
        }

        // string to read message from input
        //String line = "";

        // keep reading until "Over" is input
        /*while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }*/

        // close the connection
    }

    //1
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


    //1
    public void run()
    {
        /*
        while (thread != null)
        {

            try
            {
                System.out.println("1");
                out.writeUTF(input.readLine());
                out.flush();
            }
            catch(IOException ioe)
            {
                System.out.println("Sending error: " + ioe.getMessage());
                //stop();
            }

        }

         */
    }


    //6
    public void sendMessage(String line)
    {
        //while (UserInterface.isOpen())
        //{
        //System.out.println("6");
        ui.displayMessage(line);
        //System.out.println(line);
            //try
            //{
                //out.writeUTF(line);
            //}
            //catch (IOException i)
            //{
                //i.printStackTrace();
            //}
        //}
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

    /*
    public void start() throws IOException
    {
        input = new DataInputStream(System.in);
        out = new DataOutputStream(socket.getOutputStream());
        if (thread == null)
        {
            client = new ClientThread(this, socket);
            thread = new Thread(this);
            thread.start();
        }
    }
     */

    public static void main(String[] args)
    {
        //Server server = new Server();
        //List list = new List();
        new Client("127.0.0.1", 5000);
        //UserInterface ui = new UserInterface();
        //Client client = new Client("127.0.0.1", 5000);
        //server.writeToFile();
        //System.out.println(Server.regs.get(0));
        //System.out.println(Server.f);

        // close the connection
        /*try
        {
            client.input.close();
            out.close();
            client.socket.close();
        }
        catch(IOException i)
        {
            System.out.println("closed");
            i.printStackTrace();
        }*/
    }
}
