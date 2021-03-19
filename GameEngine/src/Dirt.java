import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Dirt extends Block {

    String type="DIRT";

    public Dirt(){
        super();
        try{
            img= ImageIO.read(new File("src\\dirt.png"));
         }catch(Exception e){e.printStackTrace();}
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
