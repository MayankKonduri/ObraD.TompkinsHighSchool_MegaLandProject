package Project.src;
import java.util.*;
import java.lang.*;

public class BuildingCards {

    public int buildingID = 0;
    public String buildingName = null;
    public int buildingCost = 0;
    public boolean isStarCard = false;
    public boolean isNightCard = false;


    public BuildingCards(int buildingID, String buildingName, int buildingCost, boolean isStarCard, boolean isNightCard){

        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.buildingCost = buildingCost;
        this.isStarCard = isStarCard;
        this.isNightCard = isNightCard;

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


}
