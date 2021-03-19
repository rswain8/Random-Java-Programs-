import java.awt.Color;
import javax.swing.JFrame;
import java.io.File;
import java.util.ArrayList;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class TestingWindow2 extends JFrame
{
    BufferedImage original;
    BufferedImage[][] images = new BufferedImage[4][4];

    public TestingWindow2()
    {
        // comment in the bellow lines one by one after writing the method the line uses
        // After each line is written run the program and check it verse the key image in your lab
        // Note: after doing the load method you will need to uncomment the 1st 2 lines


        original		= ImageTools2.load("src\\circle.png");
        images[0][0]	= original;
        images[0][1]	= ImageTools2.copy(original);
        images[0][2]	= ImageTools2.scale(original,.25,.25);
        images[0][3]	= ImageTools2.scale(original,10,10);
        images[1][0]	= ImageTools2.rotate(original,25);
        images[1][1]	= ImageTools2.rotate(original,90);
        images[1][2]	= ImageTools2.removePixels(original,.25);
        images[1][3]	= ImageTools2.removePixels(original,100);
        images[2][0]	= ImageTools2.flip(original, ImageTools2.HORIZONTAL_FLIP);
        images[2][1]	= ImageTools2.flip(original, ImageTools2.VERTICAL_FLIP);
        images[2][2]	= ImageTools2.flip(original, ImageTools2.DOUBLE_FLIP);
        images[2][3]	= ImageTools2.blur(original);
        images[3][0]	= ImageTools2.fade(original,.30);
        images[3][1]	= ImageTools2.lighten(original,.3);
        images[3][2]	= ImageTools2.darken(original,.4);
        images[3][3]	= ImageTools2.invertColor(original);;

        setSize(400,400);
        setUndecorated(true);
        setVisible(true);

        repaint();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.fillRect(0,0,400,400);

        for(int r=0; r<images.length; r++)
        {
            for(int c = 0; c<images[0].length;c++)
            {
                if(images[r][c] != null)
                    g.drawImage(images[r][c],c*100,r*100,null);
            }
        }
    }


}