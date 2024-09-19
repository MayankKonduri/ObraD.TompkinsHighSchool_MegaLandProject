package Project.src;

public class LevelCards {
    public int levelCardID = 0;
    public boolean isTreasureChest = false;
    public int numberSkulls = 0;
    public boolean isJumpAble = false;
    public boolean playerDrew = false;




    public void LevelCards(int leveCard, boolean isTreasureChest, int numberSkulls, boolean isJumpAle){
        this.levelCardID = levelCardID;
        this.isTreasureChest = isTreasureChest;
        this.numberSkulls = numberSkulls;
        this.isJumpAble = isJumpAble;
    }

    public boolean playerDrawn(int playerTurn){
        return playerDrew;
    }
    public void setPlayerHearts(int numberSkulls){
        this.numberSkulls = numberSkulls;
    }
    public boolean drawnTreasureLevel(){
        return
    }
}
