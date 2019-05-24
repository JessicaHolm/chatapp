import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface implements ActionListener
{
    JFrame frame = new JFrame("Chat Application");
    protected static final String textFieldString = "JTextField";
    protected static final String passwordFieldString = "JPasswordField";
    protected static final String buttonString = "JButton";

    public UserInterface()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = (JPanel) frame.getContentPane();
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

        frame.add(reg);
        frame.add(login);
        panel.add(title);

        frame.setSize(1000,1000);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if("register".equals(e.getActionCommand()))
        {
            JFrame frame = new JFrame("Register");
            JPanel panel = (JPanel) frame.getContentPane();

            JTextField textField = new JTextField(10);
            textField.setBounds(100,100, 200,30);
            //textField.setActionCommand(textFieldString);

            JPasswordField passwordField = new JPasswordField(10);
            passwordField.setBounds(100,150, 200,30);
            passwordField.setActionCommand(passwordFieldString);
            passwordField.addActionListener(this);

            JLabel textFieldLabel = new JLabel("Username: ");
            textFieldLabel.setBounds(25, 110, 300, 10);

            JLabel passwordFieldLabel = new JLabel("Password: ");
            passwordFieldLabel.setBounds(25, 160, 300, 10);

            panel.add(textField);
            panel.add(textFieldLabel);
            panel.add(passwordField);
            panel.add(passwordFieldLabel);
            frame.setSize(500,500);
            frame.setLayout(null);
            frame.setVisible(true);
        }
        if(passwordFieldString.equals(e.getActionCommand()))
        {

        }
    }
}