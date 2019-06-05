import java.io.*;
import java.util.*;

public class List
{
    public static ArrayList<String> regs = new ArrayList<>();
    public static ArrayList<ServerThread> online = new ArrayList<>();
    public static ArrayList<String> messages = new ArrayList<>();

    /*
    public List()
    {
        try
        {
            FileReader file = new FileReader("UserInfo.txt");
            BufferedReader in = new BufferedReader(file);
            String line;

            while ((line = in.readLine()) != null)
                regs.add(line);

            in.close();

        }
        catch (IOException i)
        {
            i.printStackTrace();
        }
    }
     */

    public void readUsersFromFile()
    {
        try
        {
            FileReader file = new FileReader("UserInfo.txt");
            BufferedReader in = new BufferedReader(file);
            String line;

            while ((line = in.readLine()) != null)
                regs.add(line);

            in.close();

        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void readMessagesFromFile(String username)
    {
        try
        {
            FileReader file = new FileReader(username + ".txt");
            BufferedReader in = new BufferedReader(file);
            String line;

            while ((line = in.readLine()) != null)
                messages.add(line);

            in.close();

        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void writeToFile(String username)
    {
        try
        {
            FileWriter outUser = new FileWriter("UserInfo.txt");
            FileWriter outMessage = new FileWriter(username + ".txt");

            for (String i : regs)
                outUser.write(i + "\n");
            outUser.close();

            for (String i : messages)
                outMessage.write(i + "\n");
            outMessage.close();

        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public void register(String username, String password)
    {
        try
        {
            FileWriter outMessage = new FileWriter(username + ".txt");
            String userInfo = username + ":" + password;
            regs.add(userInfo);
            outMessage.close();
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
    }

    public int login(String username, String password)
    {
        String userInfo = username + ":" + password;

        for (String i : regs)
        {
            if(i.equals(userInfo))
            {
                //ServerThread.setUserName(username);
                return 0;
            }
        }
        return -1;
    }

    public void record(String message)
    {
        int index;
        message = message.substring(1);
        if(message.contains(";"))
        {
            index = message.indexOf(';');
            message = message.substring(0, index);
        }
        messages.add(message);
    }
}
