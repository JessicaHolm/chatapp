public class Main
{
    public static void main(String[] args)
    {
        UserInterface ui = new UserInterface();
        ui.setup();
        Server server = new Server(5000);
    }
}