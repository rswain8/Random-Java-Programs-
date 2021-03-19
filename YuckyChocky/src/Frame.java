import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.*;
public class Frame extends JFrame {


    YuckyPanel p;

    public Frame(int size,int width, int height){
        super("Yucky Chocky");

        setSize(width,height);
        //setUndecorated(true);
        p=new YuckyPanel(size,width,height);
        addKeyListener(p);
        add(p);
        setVisible(true);

    }

    public YuckyPanel getP() {
        return p;
    }

    public void setP(YuckyPanel p) {
        this.p = p;
    }
}
