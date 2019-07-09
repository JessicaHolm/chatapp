import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserInterface implements ActionListener
{
    private Font font = new Font("Helvetica Neue", Font.PLAIN, 16);
    private IntroWindow intro = new IntroWindow();
    public Register reg = new Register();
    private Login log = new Login();
    private MainWindow main = new MainWindow();
    private Records records = new Records();
    private PM pmWindow = new PM();
    public List list = new List();
    private String username = null;
    private String recipient = null;

    private class IntroWindow
    {
        JFrame frame = new JFrame("Chat Application");
    }

    private class Register
    {
        JFrame frame = null;
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
    }

    private class Login
    {
        JFrame frame = null;
        JTextField textField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
    }

    private class MainWindow
    {
        JFrame frame = new JFrame();
        JTextField messageBox = new JTextField("Enter a message");
        JTextArea chatBox = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(chatBox);
        DefaultListModel<String> listModel = new DefaultListModel<>();
    }

    private class Records
    {
        JFrame frame = new JFrame();
        JTextArea recordsBox = new JTextArea(5, 20);
    }

    private class PM
    {
        JFrame frame = null;
        JTextField messageBox = new JTextField(40);
        JTextArea chatBox = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(chatBox);
    }

    private class UserListRenderer extends DefaultListCellRenderer
    {
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
        {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setIcon(new ImageIcon("ExtraFiles\\green.png"));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            return label;
        }
    }

    public UserInterface()
    {
        list.readUsersFromFile();
        intro.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        intro.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                list.writeToFile(username);
                intro.frame.dispose();
                System.exit(0);
            }
        });

        JPanel panel = (JPanel) intro.frame.getContentPane();
        JLabel title = new JLabel("Welcome to the Java Chat Application!");
        title.setFont(new Font("Helvetica Neue", Font.BOLD, 24));
        JButton reg = new JButton("Register New User");
        JButton login = new JButton("Login");

        reg.setBounds(130,300,200, 40);
        login.setBounds(630,300,100, 40);
        title.setBounds(225, 25, 600, 70);
        reg.setActionCommand("register");
        login.setActionCommand("login");
        reg.addActionListener(this);
        login.addActionListener(this);

        intro.frame.add(reg);
        intro.frame.add(login);
        panel.add(title);

        intro.frame.setSize(900,700);
        intro.frame.setLayout(null);
        intro.frame.setVisible(true);
    }

    public void mainChat()
    {
        main.frame.setTitle("Chat Application Logged in as " + username);

        main.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                Client.fromUI("3" + username);
                list.writeToFile(username);
                main.frame.dispose();
                System.exit(0);
            }
        });

        JPanel panel = (JPanel) main.frame.getContentPane();
        JButton logout = new JButton("Logout");
        logout.addActionListener(this);
        logout.setActionCommand("logout");

        JButton records = new JButton("Chat Records");
        records.addActionListener(this);
        records.setActionCommand("records");

        main.messageBox.addActionListener(this);
        main.messageBox.setActionCommand("send");
        main.chatBox.setEditable(false);
        main.messageBox.setFont(font);
        main.chatBox.setFont(font);

        main.messageBox.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                main.messageBox.setText("");
            }

            @Override
            public void focusLost(FocusEvent e)
            {
            }
        });

        main.listModel = new DefaultListModel<>();

        JList<String> list = new JList<>(main.listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setCellRenderer(new UserListRenderer());
        JScrollPane listScrollPane = new JScrollPane(list);

        list.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent evt)
            {
                if (evt.getClickCount() == 2)
                {
                    recipient = list.getSelectedValue();
                    if(!recipient.equals(username))
                        privateMessageWindow();
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(records);
        buttonPanel.add(logout);

        JPanel userPanel = new JPanel();
        userPanel.add(listScrollPane);

        panel.add(main.messageBox, BorderLayout.PAGE_END);
        panel.add(main.scrollPane, BorderLayout.CENTER);
        panel.add(userPanel, BorderLayout.LINE_END);
        panel.add(buttonPanel, BorderLayout.PAGE_START);

        main.frame.setSize(900,700);
        main.frame.setVisible(true);
    }

    public void displayMessage(String line)
    {
        main.chatBox.append(line + "\n");
    }

    public void loginPopup(String user)
    {
        JOptionPane.showMessageDialog(main.frame, user + " just logged in!", "New user", JOptionPane.INFORMATION_MESSAGE);
    }

    public void logoutPopup(String user)
    {
        JOptionPane.showMessageDialog(main.frame, user + " just logged out!", "User leaving", JOptionPane.INFORMATION_MESSAGE);
    }

    public void addUser(String user)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(!main.listModel.contains(user))
                {
                    main.listModel.addElement(user);
                }
            }
        });
    }

    public void removeUser(String user)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                main.listModel.removeElement(user);
            }
        });
    }

    public void displayRecords()
    {
        for (String line : List.messages)
           records.recordsBox.append(line + "\n");
    }

    public void displayPMessage(String line)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                int index = line.indexOf(':');
                if (recipient == null)
                    recipient = line.substring(0, index);
                privateMessageWindow();
                pmWindow.chatBox.append(line + '\n');
            }
        });
    }

    public void privateMessageWindow()
    {
        if (pmWindow.frame == null)
        {
            pmWindow.frame = new JFrame(username + "'s Private Chat with " + recipient);
            JPanel panel = (JPanel) pmWindow.frame.getContentPane();

            pmWindow.messageBox.addActionListener(this);
            pmWindow.messageBox.setActionCommand("sendPM");
            pmWindow.chatBox.setEditable(false);
            pmWindow.chatBox.setFont(font);

            panel.add(pmWindow.messageBox, BorderLayout.PAGE_END);
            panel.add(pmWindow.scrollPane, BorderLayout.CENTER);

            pmWindow.frame.setSize(500, 500);
            pmWindow.frame.setLocationRelativeTo(main.frame);
            pmWindow.frame.setVisible(true);
        }
        else
            pmWindow.frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if("register".equals(e.getActionCommand()))
        {
            if(reg.frame == null)
            {
                reg.frame = new JFrame("Register");
                JPanel panel = (JPanel) reg.frame.getContentPane();

                reg.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                reg.frame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        reg.textField.setText("");
                        reg.passwordField.setText("");
                        reg.frame.dispose();
                    }
                });

                reg.textField.setFont(font);
                reg.textField.setBounds(100, 100, 200, 30);

                reg.passwordField.setBounds(100, 150, 200, 30);
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
                reg.frame.setSize(400, 400);
                reg.frame.setLayout(null);
                reg.frame.setLocationRelativeTo(intro.frame);
                reg.frame.setVisible(true);
            }
            else
                reg.frame.setVisible(true);
        }
        if("login".equals(e.getActionCommand()))
        {
            if(log.frame == null)
            {
                log.frame = new JFrame("Login");
                JPanel panel = (JPanel) log.frame.getContentPane();

                log.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                log.frame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        log.textField.setText("");
                        log.passwordField.setText("");
                        log.frame.dispose();
                    }
                });

                log.textField.setFont(font);
                log.textField.setBounds(100, 100, 200, 30);

                log.passwordField.setBounds(100, 150, 200, 30);
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
                log.frame.setSize(400, 400);
                log.frame.setLayout(null);
                log.frame.setLocationRelativeTo(intro.frame);
                log.frame.setVisible(true);
            }
            else
                log.frame.setVisible(true);
        }
        if("registerPassword".equals(e.getActionCommand()))
        {
            if(list.register(reg.textField.getText(), new String(reg.passwordField.getPassword())))
            {
               JOptionPane.showMessageDialog(reg.frame, "Your account has been created!", "Register", JOptionPane.INFORMATION_MESSAGE);
               reg.frame.dispose();
            }
            else
                JOptionPane.showMessageDialog(reg.frame, "Username already taken.\nPlease try again.", "Register", JOptionPane.INFORMATION_MESSAGE);
        }
        if("loginPassword".equals(e.getActionCommand()))
        {
            if(list.login(log.textField.getText(), new String(log.passwordField.getPassword())))
            {
                JOptionPane.showMessageDialog(log.frame, "Login Successful!", "Login", JOptionPane.INFORMATION_MESSAGE);
                username = log.textField.getText();
                Client.fromUI("1" + username);
                log.frame.dispose();
                intro.frame.dispose();
                list.readMessagesFromFile(username);
                mainChat();
                Client.fromUI("5" + username);
            }
            else
                JOptionPane.showMessageDialog(log.frame, "Login Failed. Please try again.", "Login", JOptionPane.INFORMATION_MESSAGE);
        }
        if("send".equals(e.getActionCommand()))
        {
            String msg = "2" + username + ": " + main.messageBox.getText();
            Client.fromUI(msg);
            list.record(msg);
            main.messageBox.setText("");
        }
        if("sendPM".equals(e.getActionCommand()))
        {
            String msg = "4" + username + ": " + pmWindow.messageBox.getText() + ";" + recipient;
            Client.fromUI(msg);
            list.record(msg);
            pmWindow.messageBox.setText("");
        }
        if("logout".equals(e.getActionCommand()))
        {
            Client.fromUI("3" + username);
            list.writeToFile(username);
            main.frame.dispose();
            Client.fromUI("6" + username);
            System.exit(0);
        }
        if("records".equals(e.getActionCommand()))
        {
            records.recordsBox.setFont(font);
            records.frame.setTitle("Chat Records for " + username);

            records.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            records.frame.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    records.recordsBox.setText("");
                    records.frame.dispose();
                }
            });

            JPanel panel = (JPanel) records.frame.getContentPane();

            displayRecords();

            records.recordsBox.setEditable(false);
            panel.add(records.recordsBox);
            records.frame.pack();
            records.frame.setLocationRelativeTo(main.frame);
            records.frame.setVisible(true);
        }
    }
}