package Project.src;

import java.awt.image.BufferedImage;

public class BuildingCards {
    private int buildingID = 0;
    private String buildingName = "";
    private BufferedImage image;
    private int buildingCost = 0;
    private boolean isNightCard = false;
    private String buildingPower = "";

    public BuildingCards(){
        System.out.print("");
    }

    public BuildingCards(int buildingID, String buildingName, BufferedImage image, int buildingCost, boolean isNightCard){
        this.buildingID = buildingID;
        this.buildingName = buildingName;
        this.image = image;
        this.buildingCost = buildingCost;
        this.isNightCard = isNightCard;
        this.buildingPower = buildingPower;

    }
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
        return isNightCard;
    }
    public String getBuildingPower(){
        return buildingPower;
    }
    //needs to give id to remove a specific
    public void removeFromAvailableBuilding(int removeID){
        System.out.print(buildingName + "was Removed from Building Deck.");
    }
}
