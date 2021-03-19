import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class YuckyPanel extends JPanel implements MouseListener, KeyListener {

    int[][]grid;
    int row;
    int col;

    int bWidth;
    int bHeight;

    int size;

    int turn=2;

    public final int EATEN=0;
    public final int NORMAL=1;
    public final int SOAP=2;
    public final int HIGHLIGHTED=3;

    boolean highlight=false;
    boolean gameOver=false;
    boolean humanWins;
    boolean reset=false;
    boolean info=true;

    int height;
    int width;

    public YuckyPanel(int s,int width,int height){
        super();
        addMouseListener(this);

        grid=new int[s][s];
        size=s;

        //initializeGrid();
        for(int r=0;r<size;r++)
            for(int c=0;c<size;c++){
                grid[r][c]=NORMAL;
                if(r==0 && c==0)
                    grid[r][c]=SOAP;}




        setSize(width,height);
        size=s;
        bWidth=getWidth()/size;
        bHeight=getWidth()/size;
        this.width=width;
        this.height=height;



        setVisible(true);
    }
    public void initializeGrid(){
        for(int r=0;r<=size;r++)
            for(int c=0;c<size;c++){
               grid[r][c]=NORMAL;
                if(r==0 && c==0)
                 grid[r][c]=SOAP;


            }
    }
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,(int)(getHeight()*.8),(int)(getWidth()*.800),(int)(getHeight()*.200));
        g.setColor(Color.BLACK);
        g.drawRect(0,(int)(getHeight()*.8),(int)(getWidth()*.800),(int)(getHeight()*.200));

        if(info){
            g.drawString("Welcome to Yucky Chocky!",(int)(getWidth()*.010),(int)(getWidth()*.850));
            g.drawString("Valid moves are only blocks in row or column 1",(int)(getWidth()*.10),(int)(getHeight()*.870));
            g.drawString("The computer moves immediately after you",(int)(getWidth()*.10),(int)(getHeight()*.890));
            g.drawString("Make your first move to remove this text",(int)(getWidth()*.10),(int)(getHeight()*.910));
            g.drawString("Press the 'X' key to close the game",(int)(getWidth()*.10),(int)(getHeight()*.930));
        }
        else if(!gameOver){
        if(turn%2==0)
            g.drawString("Your Turn!",(int)(getWidth()*.400),(int)(getHeight()*.870));
        else
            g.drawString("Computers Turn",(int)(getWidth()*.400),(int)(getHeight()*.870));
        }

        for(int r=0;r<grid.length;r++)
            for(int c=0;c<grid[0].length;c++) {
                if (grid[r][c] == HIGHLIGHTED) {
                    g.setColor(Color.RED);
                    g.fillRect(getWidth() / size * c, getWidth() / size * r, bWidth, bHeight);
                }
                else if (grid[r][c] ==NORMAL) {
                    g.setColor(new Color(150, 75, 0));
                    g.fillRect(getWidth() / size * c, getWidth() / size * r, bWidth, bHeight);
                }
                else if (grid[r][c] == SOAP) {
                    g.setColor(Color.GREEN);
                    g.fillRect(getWidth() / size * c, getWidth() / size * r, bWidth, bHeight);
                }
                else if (grid[r][c] == EATEN) {
                    g.setColor(Color.WHITE);
                    g.fillRect(getWidth() / size * c, getWidth() / size * r, bWidth, bHeight);
                }

            }
            g.setColor(Color.BLACK);


        if(gameOver){g.setColor(Color.BLACK);
            if(humanWins)
                g.drawString("You Win!",(int)(getWidth()*.350),(int)(getHeight()*.900));
            else
                g.drawString("Computer Wins!",(int)(getWidth()*.350),(int)(getHeight()*.900));
                g.drawString("Press any key to restart",(int)(getWidth()*.350),(int)(getHeight()*.950));
            }
        for(int r=0;r<uneatenRows()+1;r++) {
            g.setColor(Color.BLACK);
            g.drawLine(0, getWidth() / size * r, (uneatenColumns()+1)*bWidth, getWidth() / size * r);
        }
        for(int c=0;c<uneatenColumns()+1;c++) {
            g.setColor(Color.BLACK);
            g.drawLine(getWidth()/size*c, 0, getWidth()/size*c, (uneatenRows()+1)*bHeight);
        }


    }
    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mousePressed(MouseEvent e){


        int x=e.getX();
        int y=e.getY();

        int c=x/(getWidth()/size);
        int r=y/(getWidth()/size);
        if(c==0 || r==0) {
            boolean cuttingVertically;
            if (r == 0)
                cuttingVertically = true;
            else
                cuttingVertically = false;

            if (cuttingVertically) {

                for (int row = 0; row < grid.length; row++)
                    for (int col = 0; col < grid[0].length; col++)

                        if (col >= c && grid[row][col]!=EATEN){
                            grid[row][col] = HIGHLIGHTED;}


            }
            else if (!cuttingVertically) {

                for (int row = 0; row < grid.length; row++)
                    for (int col = 0; col < grid[0].length; col++)
                        if (row >= r && grid[row][col]!=EATEN)
                            grid[row][col] = HIGHLIGHTED;


            }
        }

            repaint();

    }
    public void mouseReleased(MouseEvent e){
        if(containsHighlighted()){
        info=false;

        bite();
        paintComponent(this.getGraphics());
        try{Thread.sleep(1000);}catch(Exception ee){ee.printStackTrace();}
        computerMove();
            paintComponent(this.getGraphics());}
    }
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar()=='x')
            System.exit(3);

    }
    public void keyReleased(KeyEvent e) {
        if(gameOver)
            reset=true;
    }

    public void bite(){

        for(int row=0;row<grid.length;row++)
            for(int col=0;col<grid[0].length;col++)
            {
                if(grid[row][col]==HIGHLIGHTED){
                    grid[row][col]=EATEN;}
            }

            checkWin();
        turn++;
        paintComponent(this.getGraphics());

    }
    public boolean containsHighlighted()
    {
        for(int r=0;r<grid.length;r++)
            for(int c=0;c<grid[0].length;c++)
                if(grid[r][c]==HIGHLIGHTED)
                    return true;
        return false;
    }
    public void computerMove(){
        int x;
        if(grid[1][0]==EATEN)
            x=0;
        else if(grid[0][1]==EATEN)
            x=1;
        else
         x=(int)(Math.random()*2);

        if(x==0){

            int col=(int)(Math.random()*uneatenColumns()+1);
            while(col==0)
                col=(int)(Math.random()*uneatenColumns()+1);

            for (int row = 0; row < grid.length; row++)
                for (int c = 0; c < grid[0].length; c++)
                     if (col <= c )
                        grid[row][c] = HIGHLIGHTED;
        }
        else if(x==1){
            int row=(int)(Math.random()*uneatenRows()+1);



            for (int r = 0; r < grid.length; r++)
                for (int col = 0; col < grid[0].length; col++)
                    if (row <= r)
                        grid[r][col] = HIGHLIGHTED;

        }

        repaint();

        bite();
    }
    public void checkWin(){
        if(grid[0][1]==EATEN && grid[1][0]==EATEN){

            if(turn%2==0)
                humanWins=true;

            gameOver=true;



        }
    }
    public int uneatenColumns(){
            int total=0;
            for(int c=0;c<grid[0].length;c++)
            {
                if(grid[0][c]==NORMAL)
                    total++;
            }
            return total;
    }
    public int uneatenRows(){
        int total=0;
        for(int r=0;r<grid.length;r++)
        {
            if(grid[r][0]==NORMAL)
                total++;
        }
        return total;
    }
    public void printGrid(){
        for(int r=0;r<grid.length;r++){
            System.out.println();
            for(int c=0;c<grid[0].length;c++)
                System.out.print(grid[r][c]+",");
    }System.out.println();}

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isHumanWins() {
        return humanWins;
    }

    public void setHumanWins(boolean humanWins) {
        this.humanWins = humanWins;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }
}

