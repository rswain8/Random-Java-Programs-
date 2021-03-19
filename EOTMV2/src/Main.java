import javax.swing.*;
import java.awt.*;

public class Main extends Frame implements Runnable {

    JLabel lbl_health;
    JLabel lbl_mana;
    JLabel lbl_unexploredSpaces;

    JButton btn_save;
    JButton btn_exit;

    Panel p;

    Thread t=new Thread(this);

    public Main(){
        super("Eye Of The Moon");
        setSize(650,750);

        setLayout(null);

        p=new Panel();

        lbl_health=new JLabel("Health: "+p.getHealth());
        lbl_mana=new JLabel("Mana: "+p.getMana());
        lbl_unexploredSpaces=new JLabel("Unexplored Spaces: "+p.getUnexploredSpaces());

        btn_save=new JButton("Save");
        btn_save.addActionListener(e -> save());

        btn_exit=new JButton("Exit");
        btn_exit.addActionListener(e -> System.exit(3));


        p.setBounds(75,75,500,500);

        btn_save.setBounds(0,575,100,75);
        btn_exit.setBounds(100,575,100,75);

        lbl_unexploredSpaces.setBounds(220,585,300,10);
        lbl_mana.setBounds(220,595,300,10);
        lbl_health.setBounds(220,605,300,10);



        add(p);
        add(lbl_health);
        add(lbl_unexploredSpaces);
        add(lbl_mana);
        add(btn_exit);
        add(btn_save);

        setBackground(Color.GRAY);
        setVisible(true);
        t.start();

    }
    public void run(){


            while(true) {

                update();

                try {
                    Thread.sleep(1000/60 );
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            }

    }
    public void update(){
        p.update();
        lbl_health.setText("Health: "+p.getHealth());
        lbl_unexploredSpaces.setText("Explored Spaces: "+p.getUnexploredSpaces());
        lbl_mana.setText("Mana: "+p.getMana());


    }
    public void save(){}
    public static void main(String[]args){Main m=new Main();}
}

