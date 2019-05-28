import java.awt.event.*;
import javax.swing.*;

public class UserInterface implements ActionListener
{
    IntroWindow intro = new IntroWindow();
    Register reg = new Register();
    Login log = new Login();
    MainWindow main = new MainWindow();

    static class IntroWindow
    {
        JFrame frame = new JFrame("Chat Application");
    }

    static class Register
    {
        JFrame frame = new JFrame("Register");
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
    }

    static class Login
    {
        JFrame frame = new JFrame("Login");
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
    }

    static class MainWindow
    {
        JFrame frame = new JFrame("Chat Application");

    }

    public UserInterface()
    {
        intro.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        intro.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                Server.writeToFile();
                intro.frame.dispose();
                System.exit(0);
            }
        });

        JPanel panel = (JPanel) intro.frame.getContentPane();
        JLabel title = new JLabel("Welcome to the Java Chat Application!", SwingConstants.CENTER);
        JButton reg = new JButton("Register New User");
        JButton login = new JButton("Login");

        reg.setBounds(130,300,200, 40);
        login.setBounds(630,300,100, 40);
        title.setBounds(300, 25, 300, 10);
        reg.setActionCommand("register");
        login.setActionCommand("login");
        reg.addActionListener(this);
        login.addActionListener(this);

        intro.frame.add(reg);
        intro.frame.add(login);
        panel.add(title);

        intro.frame.setSize(1000,800);
        intro.frame.setLayout(null);
        intro.frame.setVisible(true);
    }

    public void mainChat()
    {
        main.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                Server.writeToFile();
                main.frame.dispose();
                System.exit(0);
            }
        });

        JPanel panel = (JPanel) main.frame.getContentPane();

        JLabel textFieldLabel = new JLabel("Logged in as: " + reg.textField.getText());
        textFieldLabel.setBounds(25, 110, 300, 10);

        panel.add(textFieldLabel);
        main.frame.setSize(1000,800);
        main.frame.setLayout(null);
        main.frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if("register".equals(e.getActionCommand()))
        {
            JPanel panel = (JPanel) reg.frame.getContentPane();

            reg.textField.setBounds(100,100, 200,30);

            reg.passwordField.setBounds(100,150, 200,30);
            reg.passwordField.setActionCommand("registerPassword");
            reg.passwordField.addActionListener(this);

            JLabel textFieldLabel = new JLabel("Username: ");
            textFieldLabel.setBounds(25, 110, 300, 10);

            JLabel passwordFieldLabel = new JLabel("Password: ");
            passwordFieldLabel.setBounds(25, 160, 300, 10);

            panel.add(reg.textField);
            panel.add(textFieldLabel);
            panel.add(reg.passwordField);
            panel.add(passwordFieldLabel);
            reg.frame.setSize(500,500);
            reg.frame.setLayout(null);
            reg.frame.setVisible(true);
        }
        if("login".equals(e.getActionCommand()))
        {
            JPanel panel = (JPanel) log.frame.getContentPane();

            log.textField.setBounds(100,100, 200,30);

            log.passwordField.setBounds(100,150, 200,30);
            log.passwordField.setActionCommand("loginPassword");
            log.passwordField.addActionListener(this);

            JLabel textFieldLabel = new JLabel("Username: ");
            textFieldLabel.setBounds(25, 110, 300, 10);

            JLabel passwordFieldLabel = new JLabel("Password: ");
            passwordFieldLabel.setBounds(25, 160, 300, 10);

            panel.add(log.textField);
            panel.add(textFieldLabel);
            panel.add(log.passwordField);
            panel.add(passwordFieldLabel);
            log.frame.setSize(500,500);
            log.frame.setLayout(null);
            log.frame.setVisible(true);
        }
        if("registerPassword".equals(e.getActionCommand()))
        {
           Server.server.register(reg.textField.getText(), new String(reg.passwordField.getPassword()));
           JOptionPane.showMessageDialog(reg.frame,"Your account has been created!","Register", JOptionPane.INFORMATION_MESSAGE);
           reg.frame.dispose();
        }
        if("loginPassword".equals(e.getActionCommand()))
        {
            int rc;
            rc = Server.server.login(log.textField.getText(), new String(log.passwordField.getPassword()));
            if(rc == 0)
            {
                JOptionPane.showMessageDialog(log.frame, "Login Successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
                log.frame.dispose();
                intro.frame.dispose();
                mainChat();
            }
            else
                JOptionPane.showMessageDialog(log.frame, "Login Failed. Please try again.", "Login", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}