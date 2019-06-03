import java.io.*;
import java.util.*;

public class List
{
    public static ArrayList<String> regs = new ArrayList<>();
    public static ArrayList<ServerThread> online = new ArrayList<>();

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

    public void readFromFile()
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

    public void writeToFile()
    {
        try
        {
            FileWriter out = new FileWriter("UserInfo.txt");

            for (String i : regs)
                out.write(i + "\n");

            out.close();

        } catch (IOException i)
        {
            i.printStackTrace();
        }
    }

    public void register(String username, String password)
    {
        String userInfo = username + ":" + password;
        regs.add(userInfo);
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
}
