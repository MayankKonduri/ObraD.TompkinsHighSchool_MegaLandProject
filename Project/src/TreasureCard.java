package Project.src;

public class TreasureCard {
    public int treasureID = 0;
    public String treasureName = null;
    public boolean isSafe = false;


    public TreasureCard(int treasureID, String treasureName, boolean isSafe) {
        this.treasureID = treasureID;
        this.treasureName = treasureName;
        this.isSafe = isSafe;
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
