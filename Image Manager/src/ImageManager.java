import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.TreeMap;
import java.util.Set;

public class ImageManager
{
    TreeMap<String, BufferedImage> images = null;
    boolean once=false;

    public ImageManager()
    {
        images =new TreeMap<String,BufferedImage>();
    }

    /* Pre: Receives a name of file
     * Post: Loads all this images in the file to map of images using the provided load type
     */
    public boolean loadImages(String fileName)
    {
        File f=null;
        Scanner s=null;
        try {
            f = new File(fileName);
            s=new Scanner(f);


        String[] start = s.nextLine().split(",");

        while (true) {
System.out.println(start[0]);
            if(start[0].equals("single")){
            BufferedImage img=ImageIO.read(new File("src\\"+start[2]));
            images.put(start[1],img);}
            if(start[0].equals("SNbL")){
                BufferedImage img=ImageIO.read(new File("src\\"+start[3]));
                BufferedImage firstStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                BufferedImage secondStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                BufferedImage thirdStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics g1=firstStrip.getGraphics();
                Graphics g2=secondStrip.getGraphics();
                Graphics g3=thirdStrip.getGraphics();
                g1.drawImage(img,0,0,null);
                g2.drawImage(img,-(int)(img.getWidth()/3),0,null);
                g3.drawImage(img,2*-(int)(img.getWidth()/3),0,null);
                images.put("strip0",firstStrip);
                images.put("strip1",secondStrip);
                images.put("strip2",thirdStrip);

            }
            else if(start[0].equals("GNbL")){

                BufferedImage img=ImageIO.read(new File("src\\"+start[4]));

                BufferedImage box00=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box01=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box02=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box10=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box11=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box12=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                Graphics g00= box00.getGraphics();
                Graphics g01= box01.getGraphics();
                Graphics g02= box02.getGraphics();
                Graphics g10= box10.getGraphics();
                Graphics g12= box12.getGraphics();
                Graphics g11= box11.getGraphics();
                int subW=(int)(-img.getWidth()/3);
                int subH=(img.getHeight()/2);
                g00.drawImage(img,0,0,null);
                g01.drawImage(img,subW,0,null);
                g02.drawImage(img,2*subW,0,null);
                g10.drawImage(img,0,-subH,null);
                g11.drawImage(img,subW,-subH,null);
                g12.drawImage(img,2*subW,-subH,null);
               images.put("gridr0c2",box02);
               images.put("gridr1c2",box12);
               images.put("gridr0c0",box00);
               images.put("gridr1c0",box10);
               images.put("gridr0c1",box01);
               images.put("gridr1c1",box11);

            }
            else if(start[0].equals("SSN")){
                BufferedImage img=ImageIO.read(new File("src\\"+start[5]));
                BufferedImage firstStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                BufferedImage secondStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                BufferedImage thirdStrip=new BufferedImage((int)(img.getWidth()/3),img.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics g1=firstStrip.getGraphics();
                Graphics g2=secondStrip.getGraphics();
                Graphics g3=thirdStrip.getGraphics();
                g1.drawImage(img,0,0,null);
                g2.drawImage(img,-(int)(img.getWidth()/3),0,null);
                g3.drawImage(img,2*-(int)(img.getWidth()/3),0,null);

                images.put("stripBlack",firstStrip);
                images.put("stripGreen",secondStrip);
                images.put("stripRed",thirdStrip);




            }
            else if(start[0].equals("GSN")){
                BufferedImage img=ImageIO.read(new File("src\\"+start[9]));

                BufferedImage box00=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box01=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box02=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box10=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box11=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                BufferedImage box12=new BufferedImage((int)(img.getWidth()/3),(int)(img.getHeight()/2),BufferedImage.TYPE_INT_ARGB);
                Graphics g00= box00.getGraphics();
                Graphics g01= box01.getGraphics();
                Graphics g02= box02.getGraphics();
                Graphics g10= box10.getGraphics();
                Graphics g12= box12.getGraphics();
                Graphics g11= box11.getGraphics();
                int subW=(int)(-img.getWidth()/3);
                int subH=(img.getHeight()/2);
                g00.drawImage(img,0,0,null);
                g01.drawImage(img,subW,0,null);
                g02.drawImage(img,2*subW,0,null);
                g10.drawImage(img,0,-subH,null);
                g11.drawImage(img,subW,-subH,null);
                g12.drawImage(img,2*subW,-subH,null);
                images.put("gridRed",box02);
                images.put("gridYellow",box12);
                images.put("gridBlack",box00);
                images.put("gridBrown",box10);
                images.put("gridGreen",box01);
                images.put("gridBlue",box11);















            }



            if(s.hasNextLine()==false )
               break;

            start = s.nextLine().split(",");
        }

        }catch(Exception e){e.printStackTrace();}

        return true;

    }

    /* Pre: Receives a key
     * Post: returns the image that corrisponds to the given key, null if the key is not found
     */
    public BufferedImage getImage(String key)
    {
        return images.get(key);
    }

    /* Pre: Receives a key and a file name
     * Post: loads the image in the given file to the map with the provided key
     * Note: null is returned if the file can not be opened
     */
    public BufferedImage loadImage(String key, String file)
    {   BufferedImage img=null;
        try {
             img = ImageIO.read(new File(file));
        }catch(Exception e){}
        images.put(key,img);
        return img;
    }

    /* Pre: Receives a key a
     * Post: removes the key and its image from the map, the removed image is returned.
     * null is returned if the map does not cantain the given key
     */
    public BufferedImage removeImage(String key)
    {
        return images.remove(key);
    }

    /* Pre:
     * Post: empties the map
     */
    public void clear()
    {
        images.clear();
    }

    /* Pre:
     * Post: returns a set of all the keys in the map
     */
    public Set<String> getKeys()
    {
        return images.keySet();
    }
}