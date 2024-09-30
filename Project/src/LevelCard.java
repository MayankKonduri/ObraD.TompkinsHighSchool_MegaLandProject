package Project.src;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class LevelCard implements Serializable {
    public int levelCardID = 0;
    public boolean isTreasureChest = false;
    public int numberSkulls = 0;
    public boolean isJumpAble = false;
    public transient BufferedImage image;
    public boolean trade3;
    public String levelCardName;


    public LevelCard(String levelCardName, int levelCardID, int numberSkulls, boolean isTreasureChest, boolean isJumpAble, BufferedImage image,  boolean trade3) {
        this.levelCardName = levelCardName;
        this.levelCardID = levelCardID;
        this.numberSkulls = numberSkulls;
        this.isJumpAble = isJumpAble;
        this.isTreasureChest = isTreasureChest;
        this.image = image;
        this.trade3 = trade3;
    }

    public String getLevelCardName(){
        return levelCardName;
    }
    public void setLevelCardName(){
        this.levelCardName = levelCardName;
    }
    public boolean isTrade3() {
        return trade3;
    }
    public BufferedImage getImage() {
        return image;
    }

    public int getLevelCardID() {
        return levelCardID;
    }

    public int getNumberSkulls() {
        return numberSkulls;
    }

    public boolean isJumpAble() {
        return isJumpAble;
    }

    public boolean isTreasureChest() {
        return isTreasureChest;
    }

    public void setLevelCardID(int levelCardID) {
        this.levelCardID = levelCardID;
    }

    public void setJumpAble(boolean jumpAble) {
        isJumpAble = jumpAble;
    }

    public void setNumberSkulls(int numberSkulls) {
        this.numberSkulls = numberSkulls;
    }

    public void setTreasureChest(boolean treasureChest) {
        isTreasureChest = treasureChest;
    }
}
