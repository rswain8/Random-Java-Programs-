import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends gameObject {

    private ArrayList<Block>inventory;
    private BufferedImage img;



    private String name;

    public Player(String name){
        this.name=name;

        try{
            img= ImageIO.read(new File("src\\player.png"));
        }catch (Exception e){e.printStackTrace();}

    }

    public ArrayList<Block> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Block> inventory) {
        this.inventory = inventory;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public BufferedImage getImg() {
        return img;
    }

    @Override
    public void setImg(BufferedImage img) {
        this.img = img;
    }
}
