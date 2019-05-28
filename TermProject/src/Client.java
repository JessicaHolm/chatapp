import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    private BufferedReader input = null;
    //static BufferedWriter out = null;
    static DataOutputStream out = null;

    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new BufferedReader(new InputStreamReader(System.in));

            // sends output to the socket
            //out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException i)
        {
            System.out.println("init");
            i.printStackTrace();
        }

        // string to read message from input
        String line = "";

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

    public static void sendMessage(String line)
    {
        //while (UserInterface.isOpen())
        //{
            try
            {
                out.writeUTF(line);
            }
            catch (IOException i)
            {
                //i.printStackTrace();
            }
        //}
    }

    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        Client client = new Client("127.0.0.1", 5000);

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
