import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    WorldPanel p=new WorldPanel();

    public Frame(){
        super("Engine Practice");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        addKeyListener(p);
        add(p);
        setUndecorated(true);
        setVisible(true);
    }

}
