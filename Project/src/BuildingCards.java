package Project.src;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;
import java.lang.*;

public class BuildingCards implements Serializable {

    public int buildingID = 0;
    public String buildingName = null;
    public int buildingCost = 0;
    public boolean isStarCard = false;
    public boolean isNightCard = false;
    public int number = 0;


    public BuildingCards(int buildingID, String buildingName, int buildingCost, boolean isStarCard, boolean isNightCard, int number){

        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.buildingCost = buildingCost;
        this.isStarCard = isStarCard;
        this.isNightCard = isNightCard;
        this.number = number;

    }


    //get classes

    public int getBuildingID(){
        return buildingID;
    }
    public String getBuildingName(){
        return buildingName;
    }
    public int getBuildingCost(){
        return buildingCost;
    }
    public boolean getIsStarCard(){
        return isStarCard;
    }
    public boolean getIsNightCard(){
        return isNightCard;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
