package Project.src;

import java.util.ArrayList;

public class Player{
    private int playerID;
    private String playerName;
    private boolean playerTurn;
    public int playerCoins;
    public int playerHearts;
    public int playerJumps;
    public boolean isPlayerActive;
    public boolean canDrawLevel;
    public ArrayList<Player> names = new ArrayList<>();
//    public ArrayList<TreasureCards> playerOwnedTreasureCards;
//    public ArrayList<BuildingCards> playerOwnedBuildingCards;
    public Player(int playerID, String playerName, boolean playerTurn, int playerCoins, int playerHearts, int playerJumps, boolean isPlayerActive, boolean canDrawLevel) {
        this.playerID= playerID;
        this.playerName = playerName;
        this.playerTurn = playerTurn;
        this.playerCoins = playerCoins;
        this.playerHearts = playerHearts;
        this.playerJumps = playerJumps;
        this.isPlayerActive = isPlayerActive;
        this.canDrawLevel = canDrawLevel;
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

    public ArrayList<Player> getNames() {
        return names;
    }
}
