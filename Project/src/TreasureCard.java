package Project.src;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class TreasureCard implements Serializable {
    public int treasureID = 0;
    public String treasureName = null;
    public boolean isSafe = false;
    public transient BufferedImage image;
    private String imagePath;


    public TreasureCard(int treasureID, String treasureName, boolean isSafe, BufferedImage image, String imagePath) {
        this.treasureID = treasureID;
        this.treasureName = treasureName;
        this.isSafe = isSafe;
        this.image = image;
        this.imagePath = imagePath;

    }

    public BufferedImage getImage() {
        if (image == null && imagePath != null) {
            try {
                image = ImageIO.read(new File(imagePath)); // Load the image from the path
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }



    /*public BufferedImage getImage() {
        return image;
    }*/

    public int getTreasureID() {
        return treasureID;
    }
    public String getTreasureName() {
        return treasureName;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public void setTreasureID(int treasureID) {
        this.treasureID = treasureID;
    }

    public void setTreasureName(String treasureName) {
        this.treasureName = treasureName;
    }

    public void setSafe(boolean safe) {
        isSafe = safe;
    }
}
