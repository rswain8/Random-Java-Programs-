import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectOutputStream;

public class AddingFrame extends JFrame implements WindowListener{

    private JTextField txt_number;
    private JLabel lbl_result;
    private JButton btn_add,btn_subtract;
    private ObjectOutputStream os;


    public AddingFrame(ObjectOutputStream os){
        super("Network Mather");
        setSize(300,300);
        setLayout(null);
        this.os=os;
        lbl_result=new JLabel("Value :");
        lbl_result.setBounds(20,20,100,20);
        add(lbl_result);

        txt_number=new JTextField("");
        txt_number.setBounds(20,50,100,20);
        add(txt_number);

        btn_add=new JButton("Add");
        btn_add.setBounds(20,100,100,30);
        add(btn_add);

        btn_subtract=new JButton("Subtract");
        btn_subtract.setBounds(140,100,100,30);
        add(btn_subtract);
        setVisible(true);


        btn_add.addActionListener(e -> {

            try{
                if(!txt_number.getText().equals("")){
                    os.writeObject(new Command(Command.ADD,Integer.parseInt(txt_number.getText())));
                    os.flush();
                    txt_number.setText("");
                }
            }catch(Exception ee){
                System.out.println("Error Sending Add");
                ee.printStackTrace();
            }

        });
        btn_subtract.addActionListener(e -> {

            try{
                if(!txt_number.getText().equals("")){
                    os.writeObject(new Command(Command.SUBTRACT,Integer.parseInt(txt_number.getText())));
                    os.flush();
                    txt_number.setText("");
                }
            }catch(Exception ee){
                System.out.println("Error Sending Subtract");
                ee.printStackTrace();
            }

        });



    }
    public void updateValue(int x){
        lbl_result.setText("Value :"+x);
        repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        try{

                os.writeObject(new Command(Command.LOGOFF));
                os.flush();


        }catch(Exception ee){
            System.out.println("Error Sending Add");
            ee.printStackTrace();
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
