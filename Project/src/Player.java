package Project.src;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{
    private int playerID;
    private String playerName;
    private boolean playerTurn;
    public int playerCoins;
    public int playerHearts;
    public int playerJumps;
    public boolean isPlayerActive;
    public boolean canDrawLevel;
    public boolean isHost;
    public String playerImage;

    public ArrayList<BuildingCards> playerBuildings;
    public ArrayList<TreasureCard> playerTreasures;
    public ArrayList<LevelCard> playerLevelCards;

    public ArrayList<Player> allPlayers = new ArrayList<>();
    //    public ArrayList<TreasureCards> playerOwnedTreasureCards;
//    public ArrayList<BuildingCards> playerOwnedBuildingCards;
    public Player(int playerID, String playerName, boolean playerTurn, int playerCoins, int playerHearts, int playerJumps, boolean isPlayerActive, boolean canDrawLevel, boolean isHost, ArrayList<BuildingCards> playerBuildings, ArrayList<TreasureCard> playerTreasures, ArrayList<LevelCard> playerLevelCards, String characterImage) {
        this.playerID= playerID;
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        this.playerCoins = playerCoins;
        this.playerHearts = playerHearts;
        this.playerJumps = playerJumps;
        this.isPlayerActive = isPlayerActive;
        this.canDrawLevel = canDrawLevel;
        this.isHost = isHost;
        this.playerBuildings = playerBuildings;
        this.playerTreasures = playerTreasures;
        this.playerLevelCards = playerLevelCards;
        this.playerImage = characterImage;
    }

    public String getPlayerImage(){
        return playerImage;
    }
    public void setPlayerImage(String playerImage){
        this.playerImage = playerImage;
    }
    public ArrayList<LevelCard> getPlayerLevelCards(){
        return playerLevelCards;
    }
    public void setPlayerLevelCards(ArrayList<LevelCard> playerLevelCards){
        this.playerLevelCards = playerLevelCards;
    }
    public int getPlayerID() {
        return playerID;
    }
    public void setPlayerID(int id) {
        playerID = id;
    }

    public String getPlayerName() {
        return playerName;
    }
    public void setPlayerName(String name) {
        playerName = name;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayerCoins() {
        return playerCoins;
    }

    public void setPlayerCoins(int playerCoins) {
        this.playerCoins = playerCoins;
    }

    public int getPlayerHearts() {
        return playerHearts;
    }

    public void setPlayerHearts(int playerHearts) {
        this.playerHearts = playerHearts;
    }

    public int getPlayerJumps() {
        return playerJumps;
    }

    public void setPlayerJumps(int playerJumps) {
        this.playerJumps = playerJumps;
    }

    public boolean isPlayerActive() {
        return isPlayerActive;
    }

    public void setPlayerActive(boolean playerActive) {
        isPlayerActive = playerActive;
    }

    public boolean isCanDrawLevel() {
        return canDrawLevel;
    }

    public void setCanDrawLevel(boolean canDrawLevel) {
        this.canDrawLevel = canDrawLevel;
    }

    public boolean getisHost(){
        return isHost;
    }
    public ArrayList<Player> getNames() {
        return allPlayers;
    }

    public void setPlayerBuildings(ArrayList<BuildingCards> playerBuildings1){
        this.playerBuildings = playerBuildings1;
    }
    public ArrayList<BuildingCards> getPlayerBuildings(){
        return this.playerBuildings;
    }
    public void setPlayerTreasures(ArrayList<TreasureCard> playerTreasures1) {
        this.playerTreasures = playerTreasures1;
    }

    public ArrayList<TreasureCard> getPlayerTreasures() {
        return playerTreasures;
    }
}