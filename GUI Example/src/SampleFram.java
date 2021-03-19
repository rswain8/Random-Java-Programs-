import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SampleFram extends JFrame implements ActionListener{

    JLabel lhl_username, lhl_password;
    JTextField txt_userName,txt_password;
    JButton btn_login;
    public SampleFram(){
        super("Login Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        btn_login=new JButton("Login");
        btn_login.setBounds(50,150,200,30);
        btn_login.addActionListener(new java.awt.event.ActionListener()
        {
             public void actionPerformed(ActionEvent e)
                {
                    login();
                }
        });
        lhl_username=new JLabel("Username");
        lhl_username.setBounds(50,50,100,20);

        txt_userName=new JTextField();
        txt_userName.setBounds(120,50,100,20);

        lhl_password=new JLabel("Password");
        lhl_password.setBounds(50,100,100,20);

        txt_password=new JTextField();
        txt_password.setBounds(120,100,100,20);

        setLayout(null);
        add(lhl_username);
        add(txt_userName);
        add(lhl_password);
        add(txt_password);
        add(btn_login);
        setVisible(true);
    }
    public void login(){
        if(txt_userName.getText().equals("reilly") && txt_password.getText().equals("pass"))

    }

}
