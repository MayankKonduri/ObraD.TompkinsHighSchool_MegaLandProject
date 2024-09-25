package Project.src;

import java.awt.image.BufferedImage;

public class TreasureCard {
    public int treasureID = 0;
    public String treasureName = null;
    public boolean isSafe = false;
    public BufferedImage image;


    public TreasureCard(int treasureID, String treasureName, boolean isSafe, BufferedImage image) {
        this.treasureID = treasureID;
        this.treasureName = treasureName;
        this.isSafe = isSafe;
        this.image = image;
    }



    public BufferedImage getImage() {
        return image;
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
