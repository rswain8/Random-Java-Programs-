import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener{

    int row;
    int col;

    int[][]map;
    int [][]exploredSpaces;

    int panelSize=500;
    int mapSize=25;

    int health;
    int mana;


    public Panel(){
        super();
        setSize(panelSize,panelSize);
        addKeyListener(this);
        setFocusable(true);
        map=new int[mapSize][mapSize];
        exploredSpaces=new int[mapSize][mapSize];
        exploredSpaces[row][col]=1;
        generateMap();
        row=10;
        col=10;
        setVisible(true);

    }
    public void paint(Graphics g){


        for(int r=0;r<map.length;r++)
            for(int c=0;c<map[r].length;c++){
                if(map[r][c]==0){
                    g.setColor(Color.BLUE);
                    g.fillRect(c*mapSize+2,r*mapSize+2,panelSize/mapSize,panelSize/mapSize);}
                else if(map[r][c]==1){
                    g.setColor(Color.RED);
                    g.fillRect(c*mapSize+2,r*mapSize+2,panelSize/mapSize,panelSize/mapSize);
                }
                else if(map[r][c]==2){
                    g.setColor(Color.CYAN);
                    g.fillRect(c*mapSize+2,r*mapSize+2,panelSize/mapSize,panelSize/mapSize);
                }

            }


        g.setColor(Color.BLACK);
        for(int r=0;r<exploredSpaces.length;r++)
            for(int c=0;c<exploredSpaces[r].length;c++)
                if(exploredSpaces[r][c]==0)
                    g.fillRect(c*mapSize+2,r*mapSize+2,panelSize/mapSize,panelSize/mapSize);


        g.setColor(Color.GREEN);
        g.fillRect(col*mapSize+2,row*mapSize+2,panelSize/mapSize,panelSize/mapSize);



    }
    public void generateMap(){
        for(int r=0;r<map.length;r++)
            for(int c=0;c<map[r].length;c++)
             map[r][c]=(int)(Math.random()*3);
        for(int r=0;r<map.length;r++)
            for(int c=0;c<map[r].length;c++)
                exploredSpaces[r][c]=0;

    }
    public void update(){
        exploredSpaces[row][col]=1;
        repaint();
    }






    public void keyPressed(KeyEvent e){
        char c=e.getKeyChar();
        if(c=='w')
            row--;
        if(c=='s')
            row++;
        if(c=='a')
            col--;
        if(c=='d')
            col++;
        repaint();
    }
    public void keyReleased(KeyEvent e){}
    public void keyTyped(KeyEvent e){}

    public int getHealth(){
        return health;
    }
    public int getMana(){
        return mana;
    }
    public int getUnexploredSpaces(){
        int total=0;
        for(int r=0;r<exploredSpaces.length;r++)
            for(int c=0;c<exploredSpaces[r].length;c++)
                if(exploredSpaces[r][c]==1)
                    total++;

        return total;
    }


}
