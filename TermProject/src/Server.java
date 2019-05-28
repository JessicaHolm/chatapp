import java.net.*;
import java.io.*;

public class Server
{
    private ServerSocket serverSoc = null;
    private ServerThread[] clients = new ServerThread[10];
    private int clientCount = 0;

    public static Server server = new Server();
    public static Node head;

    static class Node
    {
        Node next;
        String data;

        Node(String d)
        {
            data = d;
        }
    }

    Server()
    {
        try
        {
            FileReader file = new FileReader("UserInfo.txt");
            BufferedReader in = new BufferedReader(file);
            String line;

            while ((line = in.readLine()) != null)
                insert(line);

            in.close();

        } catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public void insert(String value)
    {
        if(head == null)
            head = new Node(value);
        else
        {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
        }
    }

    public void remove(String value)
    {
        Node curr = head;
        Node prev = head;
        while(curr != null)
        {
            if(curr.data.equals(value))
                prev.next = curr.next;

            prev = curr;
            curr = curr.next;
        }
    }

    public void display()
    {
        Node curr = head;
        while(curr != null)
        {
            if(curr.next == null)
                System.out.print(curr.data);
            else
                System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void startServer(int port)
    {
        try
        {
            serverSoc = new ServerSocket((port));
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            while (true)
            {
                try
                {
                    Socket socket = serverSoc.accept();
                    System.out.println("Client accepted");
                    ServerThread st = new ServerThread(socket);
                    st.start();
                } catch (IOException i)
                {
                    System.out.println("start");
                    i.printStackTrace();
                }
            }
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    public synchronized void receiveMessage(int ID, String input)
    {
        if (input.equals("Over"))
        {
            //clients[findClient(ID)].send("Over");
            //remove(ID);
            System.out.println("Goodbye Client number: " + ID);
        }
        else
            for (int i = 0; i < clientCount; i++)
                clients[i].sendMessage(ID + ": " + input);
    }

    /*public void startServer(int port)
    {
        // starts server and waits for a connection
        try
        {
            serverSoc = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = serverSoc.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                } catch (IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        } catch (IOException i)
        {
            System.out.println(i);
        }
    }*/

    public void register(String username, String password)
    {
        String userInfo = username + ":" + password;
        insert(userInfo);
    }

    public int login(String username, String password)
    {
        Node curr = head;
        String userInfo = username + ":" + password;

        while(curr != null)
        {
            if(curr.data.equals(userInfo))
                return 0;
            curr = curr.next;
        }
        return -1;
    }

    public static void writeToFile()
    {
        try
        {
            FileWriter out = new FileWriter("UserInfo.txt");
            Node curr = head;
            while (curr != null)
            {
                out.write(curr.data + "\n");
                curr = curr.next;
            }
            out.close();

        } catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Server.server.startServer(5000);
        System.out.println("Closing connection");
    }
}
