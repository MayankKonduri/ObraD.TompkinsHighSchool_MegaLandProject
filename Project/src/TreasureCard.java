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

    private boolean selected = false;


    public TreasureCard(int treasureID, String treasureName, boolean isSafe,  boolean selected) {
        this.treasureID = treasureID;
        this.treasureName = treasureName;
        this.isSafe = isSafe;
        this.selected = selected;

    }




    /*public BufferedImage getImage() {
        return image;
    }*/
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public boolean getSelected() {
        return selected;
    }

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