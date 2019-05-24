import javax.swing.*;

public class UserInterface
{
    public void setup()
    {
        JFrame f = new JFrame();//creating instance of JFrame

        JLabel title = new JLabel("Welcome to the Java Chat Application!", SwingConstants.CENTER);
        JButton reg = new JButton("Register New User");//creating instance of JButton
        reg.setBounds(130,100,100, 40);//x axis, y axis, width, height

        f.add(reg);//adding button in JFrame

        f.setSize(600,800);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
    }
}
