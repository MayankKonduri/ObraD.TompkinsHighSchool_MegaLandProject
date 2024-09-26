package Project.src;
import java.awt.image.BufferedImage;
import java.util.*;
import java.lang.*;

public class TreasureCard {
    public int treasureID = 0;
    public String treasureName = null;
    public BufferedImage image;
    public boolean isSafe = false;
    public String removed = "";


    public TreasureCard(int treasureID, String treasureName, BufferedImage image, boolean isSafe){
        this.treasureID = treasureID;
        this.treasureName = treasureName;
        this.image = image;
        this.isSafe = isSafe;
    }

    public int getTreasureID(){
        return treasureID;
    }
    public boolean getIsSafe(){
        return isSafe;
    }
    public String removeFromAvailableTreasure(int treasureID){
        removed = "Removed: "+ treasureID + " with name: "+ treasureName;
        return removed;

    }
    public String getTreasureName(){
        return treasureName;
    }
}
