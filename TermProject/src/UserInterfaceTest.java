import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest
{

    @org.junit.jupiter.api.Test
    void displayPMessage()
    {
        new Server(5000);
        new Client("127.0.0.1", 5000);
        UserInterface ui = new UserInterface();
        ui.mainChat();
    }

    @org.junit.jupiter.api.Test
    void privateMessage()
    {
    }
}