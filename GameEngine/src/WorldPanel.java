import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class WorldPanel extends JPanel implements KeyListener {

    private ArrayList<gameObject>gameObjects;

    private BufferedImage currentBackround;

    private Player p;


    private int playerRow;
    private int playerCol;

    private int worldRow=0;
    private int worldCol=10;

    private int rowsToDisplay=20;
    private int colsToDisplay=20;

    private int width=(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private int height=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private int blockWidth=(int)(0.05*width);
    private int blockHeight=(int)(0.05*height);

    private mapBlockGrid blockMap=new mapBlockGrid();

    public WorldPanel(){
        super();
        try {
            currentBackround = ImageIO.read(new File("src\\skyBackround.png"));
        }catch(Exception e){e.printStackTrace();}
        p=new Player("Swag");
        p.setX((int)(worldCol+colsToDisplay*.5));
        p.setY((int)(worldRow+rowsToDisplay*.5));

        gameObjects=new ArrayList<>();
        gameObjects.add(p);

        setSize(width,height);


    }
    public void paint(Graphics g){
        g.drawImage(currentBackround,0,0,getWidth(),getHeight(),null);


        for(int r=worldRow;r<worldRow+rowsToDisplay;r++)
            for(int c=worldCol;c<worldCol+colsToDisplay;c++) {

            if(!(blockMap.getBlockMap()[r][c].getType()=="AIR"))
                g.drawImage(blockMap.getBlockMap()[r][c].getImg(), (c - worldCol) * blockWidth, (r - worldRow) * blockHeight,blockWidth,blockHeight, null);
            }

        for(gameObject x: gameObjects) {
            g.drawImage(x.getImg(), (x.getX() - worldCol) * blockWidth, (x.getY() - worldRow) * blockHeight, null);
       }

    }
    public void keyReleased(KeyEvent e){

    }
    public void keyPressed(KeyEvent e){

        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            System.exit(3);}
    }
    public void keyTyped(KeyEvent e){}

}
