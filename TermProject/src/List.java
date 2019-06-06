import java.io.*;
import java.util.*;

public class List
{
    public static ArrayList<String> regs = new ArrayList<>();
    public static ArrayList<ServerThread> online = new ArrayList<>();
    public static ArrayList<String> messages = new ArrayList<>();

    public void readUsersFromFile()
    {
        try
        {
            FileReader file = new FileReader("ExtraFiles\\UserInfo.txt");
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
            FileReader file = new FileReader("ExtraFiles\\" + username + ".txt");
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
            FileWriter outUser = new FileWriter("ExtraFiles\\UserInfo.txt");
            FileWriter outMessage = new FileWriter("ExtraFiles\\" + username + ".txt");

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

    public boolean register(String username, String password)
    {
        for (String i : regs)
        {
            int index = i.indexOf(':');
            String user = i.substring(0, index);
            if(user.equals(username))
                return false;
        }
        try
        {
            FileWriter out = new FileWriter("ExtraFiles\\" + username + ".txt");
            String userInfo = username + ":" + password;
            regs.add(userInfo);
            out.close();
        }
        catch (IOException i)
        {
            System.out.println("IO error: " + i.getMessage());
        }
        return true;
    }

    public boolean login(String username, String password)
    {
        String userInfo = username + ":" + password;

        for (String i : regs)
        {
            if(i.equals(userInfo))
                return true;
        }
        return false;
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
