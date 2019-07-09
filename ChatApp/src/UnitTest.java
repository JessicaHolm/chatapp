import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.*;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest
{
    @Test
    public void server()
    {
        Server server = new Server(5000);
        assertNotNull(server);
    }

    @Test
    public void client()
    {
        Client client = new Client("127.0.0.1", 5000);
        assertNotNull(client);
    }

    @Test
    public void register() throws AWTException
    {
        String user = "jholm";
        String password = "password";
        UserInterface ui = new UserInterface();

        Robot bot = new Robot();
        bot.mouseMove(240,340);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.delay(500);

        for (char c : user.toCharArray())
        {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            bot.keyPress(keyCode);
            bot.delay(100);
            bot.keyRelease(keyCode);
            bot.delay(100);
        }
        bot.delay(100);
        bot.keyPress(KeyEvent.VK_TAB);
        bot.keyRelease(KeyEvent.VK_TAB);
        for (char c : password.toCharArray())
        {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            bot.keyPress(keyCode);
            bot.delay(100);
            bot.keyRelease(keyCode);
            bot.delay(100);
        }
        bot.delay(500);
        bot.keyPress(KeyEvent.VK_ENTER);
        bot.keyRelease(KeyEvent.VK_ENTER);
        bot.delay(500);
        bot.mouseMove(450,380);
        bot.delay(500);
        bot.mousePress(InputEvent.BUTTON1_MASK);
        bot.mouseRelease(InputEvent.BUTTON1_MASK);
        bot.delay(2000);

    }

    @Test
    public void loginList()
    {
        String user = "jholm";
        String password = "J168167h!";
        UserInterface ui = new UserInterface();

        assertTrue(ui.list.login(user, password));
    }

    @Test
    public void registerList()
    {
        String user = "jholm";
        String password = "password";
        UserInterface ui = new UserInterface();

        assertFalse(ui.list.register(user, password));
    }

}