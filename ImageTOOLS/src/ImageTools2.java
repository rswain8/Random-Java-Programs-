import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.nio.Buffer;

public class ImageTools2 {

    public static int HORIZONTAL_FLIP = 1, VERTICAL_FLIP = 2, DOUBLE_FLIP = 3;

    /**
     * Loads an image.
     *
     * @param fileName The name of a file with an image
     * @return Returns the loaded image. null is returned if the image cannot be loaded.
     */
    public static BufferedImage load(String fileName) {
        BufferedImage b=null;
        try{
            b= ImageIO.read(new File(fileName));
        }catch(Exception e){}
        return b;
    }

    /**
     * Copies and returns an image.
     *
     * @param img Receives a buffered image
     * @return Returns a copy of the received image. null is returned if the received image is null.
     */
    public static BufferedImage copy(BufferedImage img) {
        return img;
    }

    /**
     * Returns a new image with transparency enabled.
     *
     * @param img Receives a buffered image
     * @return returns a copy of the received image with a color mode that allows transparency.
     * null is returned if the received image is null.
     */
    public static BufferedImage copyWithTransparency(BufferedImage img) {
        BufferedImage i=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g=i.getGraphics();
        g.drawImage(img,0,0,null);
        return i;
    }

    /**
     * Checks if the provided image has transparency.
     *
     * @param img Receives a buffered image
     * @return returns true if the image has a transparent mode and false otherwise.
     */
    public static boolean hasTransparency(BufferedImage img) {
        return false;
    }

    /**
     * Scales an image.
     *
     * @param img Receives a buffered image and two positive double scale values (percentages)
     * @param horizontalScale Value to scale horizontal by.
     * @param verticalScale Value to scale vertical by.
     * @return creates and returns a scaled copy of the received image,
     * null is returned if the received image is null or if non-positive scales are provided
     */
    public static BufferedImage scale(BufferedImage img, double horizontalScale,
                                      double verticalScale) {

        BufferedImage retur=new BufferedImage((int)(img.getWidth()*horizontalScale),(int)(img.getHeight()*verticalScale),BufferedImage.TYPE_INT_RGB);
        int nW=(int)(img.getWidth()*horizontalScale);
        int nH=(int)(img.getHeight()*verticalScale);

        BufferedImage r = new BufferedImage( nW, nH, img.getColorModel().getTransparency());
        Graphics2D g = (Graphics2D) (r.getGraphics());
        Image i=img.getScaledInstance(nW,nH,0);
        g.drawImage(i,0,0,null);

        return r;


    }

    /**
     * Scales an image.
     *
     * @param img Receives a buffered image
     * @param newWidth New width to scale to.
     * @param newHeight New height to scale to.
     * @return creates and returns a scaled copy of the received image,
     * null is returned if the received image is null or if non-positive dimensions are provided
     */
    public static BufferedImage scale(BufferedImage img, int newWidth,
                                      int newHeight) {
        //BufferedImage i=new BufferedImage((int)(img.getWidth()*newWidth/img.getWidth()),(int)(img.getHeight()*newHeight/img.getHeight()),img.getType());
        BufferedImage n=new BufferedImage(newWidth,newHeight,img.getColorModel().getTransparency());
        Image i=img.getScaledInstance(newWidth,newHeight,0);
        Graphics2D g=(Graphics2D)(n.getGraphics());
        g.drawImage(i,0,0,null);
        return n;






    }

    /**
     * Rotates an image.
     *
     * @param img Receives a buffered image
     * @param angle The angle to rotate to.
     * @return The rotated image. null is returned if the received image is null.
     */
    public static BufferedImage rotate(BufferedImage img, double angle) {

        angle = angle%360;

        AffineTransform affineTransform = new AffineTransform();
        affineTransform.setToTranslation(0,0);
        affineTransform.rotate(Math.toRadians(angle), img.getWidth()/2, img.getHeight()/2);

        int transparency = img.getColorModel().getTransparency();

        BufferedImage rotated =
                new BufferedImage( img.getWidth(), img.getHeight(), transparency);

        Graphics2D g = (Graphics2D) (rotated.getGraphics());

        g.drawImage(img, affineTransform, null);
        return rotated;
    }

    /**
     * Flips an image.
     *
     * @param img Receives a buffered image
     * @param type Type of flip (int)
     * @return Creates and returns a flipped copy of the received image.
     * null is returned if the received image is null or if an invalid flipping value is provided
     */
    public static BufferedImage flip(BufferedImage img, int type) {


        if(type==DOUBLE_FLIP)
            return rotate(img,180);
        if(type==VERTICAL_FLIP){
            AffineTransform af=AffineTransform.getScaleInstance(1,-1);
            af.translate(0,-img.getHeight());
            int transparency = img.getColorModel().getTransparency();
            BufferedImage rotated =
                    new BufferedImage( img.getWidth(), img.getHeight(), transparency);

            Graphics2D g = (Graphics2D) (rotated.getGraphics());

            g.drawImage(img, af, null);
            return rotated;

        }
        if(type==HORIZONTAL_FLIP){
            AffineTransform af=AffineTransform.getScaleInstance(-1,1);
            af.translate(-img.getWidth(),0);
            int transparency = img.getColorModel().getTransparency();
            BufferedImage rotated =
                    new BufferedImage( img.getWidth(), img.getHeight(), transparency);

            Graphics2D g = (Graphics2D) (rotated.getGraphics());

            g.drawImage(img, af, null);
            return rotated;

        }





        return null;
    }

    /**
     * Blurs an image.
     *
     * @param img Receives a buffered image
     * @return creates and returns a blurred copy of the received image,
     * the blurring is created by blending each cell with its 8 neighbors.
     * Null is returned if the received image is null.
     */
    public static BufferedImage blur(BufferedImage img) {
       BufferedImage src=copyWithTransparency(img);
        float[]arr= {
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
                0.111f, 0.111f, 0.111f,
        };
        BufferedImageOp op=new ConvolveOp(new Kernel(3,3,arr),1,new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY));

        BufferedImage blur=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        op.filter(src,blur);



        return blur;
       /*int avR=0;
       int avB=0;
       int avG=0;
       int blurCount=0;
       for(int x=0;x<img.getWidth();x++)
           for(int y=0;y<img.getHeight();y++){

                Color c=new Color(img.getRGB(x,y));
                if(c.getAlpha()!=0){
                    avR+=c.getRed();
                    avB+=c.getBlue();
                    avG+=c.getGreen();
                    blurCount++;
                }
           }
           avR/=blurCount;
           avB/=blurCount;
           avG/=blurCount;
            Color newC=new Color(avR,avG,avB);
           for(int x=0;x<img.getWidth();x++)
               for(int y=0;y<img.getHeight();y++){
                src.setRGB(x,y,newC.getRGB());
               }
*/
               //return src;
    }

    /**
     * Inverts an image's colors.
     *
     * @param img Receives a buffered image
     * @return Image with inverted colors. null is returned if the received image is null.
     */
    public static BufferedImage invertColor(BufferedImage img) {

        BufferedImage fin=copyWithTransparency(img);

        for(int x=0;x<img.getWidth();x++)
            for(int y=0;y<img.getHeight();y++){

                int rgba=img.getRGB(x,y);
                Color first=new Color(rgba,true);
                Color las=new Color(255-first.getRed(),255-first.getGreen(),255-first.getBlue(),first.getAlpha());

                fin.setRGB(x,y,las.getRGB());

            }

        return fin;

    }

    /**
     * Removes a certain percentage of an image's pixels.
     *
     * @param img Receives a buffered image
     * @param percentToRemove Percent to remove of the image.
     * @return creates and returns a copy of the received image with the given
     * percentage in decimal form of the images remaining non-fully transparent
     * pixels changed to be completely transparent. null is returned if the
     * received image is null or if non-positive percentage is provided.
     */
    public static BufferedImage removePixels(BufferedImage img, double percentToRemove) {

        BufferedImage retur=copyWithTransparency(img);
        int pixelNumber=1;
        int per=(int)(100/(percentToRemove*100));




        for(int x=0;x<retur.getWidth();x++)
            for(int y=0;y<retur.getWidth();y++){
            if(pixelNumber%per==0){
                int xx=(int)(Math.random()*img.getWidth());
                int yy=(int)(Math.random()*img.getHeight());
                while(retur.getRGB(xx,yy)==0){
                    xx=(int)(Math.random()*img.getWidth());
                    yy=(int)(Math.random()*img.getHeight());
                }
                retur.setRGB(xx, yy, 0);}
                pixelNumber++;
            }







        return retur;

    }

    /**
     * Removes a certain amount of pixels from an image.
     *
     * @param img Receives a buffered image
     * @param numToRemove number of pixels to remove
     * @return creates and returns a copy of the received image with the given
     * number of the images remaining pixels removed.
     * Non-fully transparent pixels are changed to be completely transparent.
     * null is returned if the received image is null or if non-positive number
     * is provided. Note: is there are not enough pixels in the image it will
     * remove as many as it can.
     */
    public static BufferedImage removePixels(BufferedImage img, int numToRemove) {
        BufferedImage retur=copyWithTransparency(img);
        int chance=(int)(Math.random()*2+1);
        int num=(int)(Math.random()*2+1);


        for(int x=0;x<retur.getWidth();x++)
            for(int y=0;y<retur.getWidth();y++){
            if(numToRemove==0)
                break;

                if(num==chance){
                    int xx=(int)(Math.random()*img.getWidth());
                    int yy=(int)(Math.random()*img.getHeight());
                    while(retur.getRGB(xx,yy)==0){
                        xx=(int)(Math.random()*img.getWidth());
                        yy=(int)(Math.random()*img.getHeight());
                    }
                    retur.setRGB(xx, yy, 0);}
                num=(int)(Math.random()*2+1);
                numToRemove--;
            }







        return retur;
    }

    /**
     * Fades an image.
     *
     * @param img Receives a buffered image
     * @param fade Double percentage to fade
     * @return Creates and returns a copy of the received image that has been
     * faded the given percentage. Fading is done by multiply the alpha value by (1-fade)
     * Null is returned if the received image is null or if non-positive fade value is provided
     * Note: any fade greater than 1 will be reduced to 1
     */
    public static BufferedImage fade(BufferedImage img, double fade) {

        BufferedImage fin=copyWithTransparency(img);
        for(int x=0;x<img.getWidth();x++)
            for(int y=0;y<img.getHeight();y++){
                Color first=new Color(img.getRGB(x,y),true);
                Color sec=new Color(first.getRed(),first.getGreen(),first.getBlue(),(int)(first.getAlpha()*(1-fade)));
                fin.setRGB(x,y,sec.getRGB());
            }


        return fin;
    }

    /**
     * Lightens an image.
     *
     * @param img Receives a buffered image
     * @param lightenFactor double percentage to lighten
     * @return creates and returns a copy of the received image that has been
     * lightened by the given percentage + 1.
     * Null is returned if the received image is null or if non-positive lighten.
     * Factor value is provided.
     * Note: any colors that end up being larger than 255 will be changed to 255.
     */
    public static BufferedImage lighten(BufferedImage img, double lightenFactor) {

        BufferedImage fin=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g=fin.getGraphics();
        g.drawImage(img,0,0,null);


        float scale=1.3f;
        RescaleOp op=new RescaleOp(scale,0,null);
        op.filter(fin,fin);
        return fin;






    }

    /**
     * Darkens an image.
     *
     * @param img Receives a buffered image
     * @param darkenFactor double percentage to darken
     * @return creates and returns a copy of the received image that has been
     * darkened by 1 minus the given percentage.
     * null is returned if the received image is null or if non-positive.
     * Note: any colors that end up being larger than 255 will be
     * changed to 255.
     */
    public static BufferedImage darken(BufferedImage img, double darkenFactor) {
        BufferedImage fin=new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics g=fin.getGraphics();
        g.drawImage(img,0,0,null);


        float scale=.6f;
        RescaleOp op=new RescaleOp(scale,0,null);
        op.filter(fin,fin);
        return fin;


    }
}