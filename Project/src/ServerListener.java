package Project.src;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ServerListener implements Runnable{
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ServerMain serverMain;
    private volatile boolean isRunning = true;

    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static final String CLIENT_NAME_VERIFY = "CLIENT_NAME_VERIFY:";
    public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static final String CHARACTER_UNSELECTION = "CHARACTER_UNSELECTION:";
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static final String CLIENT_MESSAGE = "CLIENT_MESSAGE:";
    public static final String LEVEL_CARD_NAME = "LEVEL_CARD_NAME:";
    public static final String INTERCLICK = "INTERCLICK:";
    public static final String FINALCHARACTER = "FINALCHARACTER:";
    public static final String LEVELDISCONNECTION = "LEVELDISCONNECTION:";

    public ServerListener(Socket clientSocket, ServerMain serverMain, ObjectInputStream in, ObjectOutputStream out) {
        this.clientSocket = clientSocket;
        this.serverMain = serverMain;
        this.in = in;
        this.out = out;
    }
    public void run(){
        try{
            Object receivedObject;
            while (isRunning && (receivedObject = in.readObject()) != null) {
                if (receivedObject instanceof String) {
                    String message = (String) receivedObject;
                    System.out.println("Received message from server: " + message);
                    processMessage(message);
                } else if (receivedObject instanceof Player) {
                    Player playerGet = (Player) receivedObject;
                    processPlayer(playerGet);
                    System.out.println("DebugLib: " + playerGet.getPlayerName());
                }
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            stopListening();
        }
    }

    private synchronized void processPlayer(Player playerGet) {

        int index = -1;
        for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {
            String playerName = serverMain.playerArrayList_Host.get(i).getPlayerName();
            if (playerGet.getPlayerName().equals(playerName)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            if (index < serverMain.playerArrayList_Host.size()) {
                serverMain.playerArrayList_Host.set(index, playerGet);
            } else {
                System.err.println("Error: Mismatch between gamePlayerNames and playerArrayList_Host sizes.");
            }
        } else {
            serverMain.playerArrayList_Host.add(playerGet);
            //serverMain.gamePlayerNames.add(playerGet.getPlayerName());
            System.out.println("Success: New Player Added (H)");

            Collections.sort(serverMain.playerArrayList_Host, new Comparator<Player>() {
                @Override
                public int compare(Player p1, Player p2) {
                    // Assuming getPlayerID() returns an int, compare the player IDs
                    return Integer.compare(p1.getPlayerID(), p2.getPlayerID());
                }
            });
            for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {
                int playerID = serverMain.playerArrayList_Host.get(i).getPlayerID();
                System.out.println("Player ID In Order: " + playerID);
            }
        }
        if(serverMain.playerArrayList_Host.get(0).getCountBuildingCards().size()>0) {
            System.out.println("test " + serverMain.playerArrayList_Host.get(0).getCountBuildingCards().get(3).getNumber());
            //System.out.println("First: " + serverMain.playerArrayList_Host.get(0).getCountBuildingCards().get(0).getNumber());
            int len = serverMain.playerArrayList_Host.get(0).getCountBuildingCards().size();
            int num = serverMain.playerArrayList_Host.size();

            int arrayNum[];
            for (int j = 0; j < len; j++) {
                arrayNum = new int[serverMain.playerArrayList_Host.size()];
                int min;
                for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {
                    arrayNum[i] = serverMain.playerArrayList_Host.get(i).getCountBuildingCards().get(j).getNumber();
                }

                min = arrayNum[0];
                for (int i = 1; i < arrayNum.length; i++) {
                    if (min > arrayNum[i]) {
                        min = arrayNum[i];
                    }
                }

                for (int i = 0; i < arrayNum.length; i++) {
                    arrayNum[i] = min;
                }
                for (int xx : arrayNum) {
                    System.out.println("10/172222: " +  + j + "," + xx);
                }

                for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {
                    serverMain.playerArrayList_Host.get(i).getCountBuildingCards().get(j).setNumber(arrayNum[0]);
                }

            }
            System.out.println("test11 " + serverMain.playerArrayList_Host.get(0).getCountBuildingCards().get(3).getNumber());
            System.out.println("test12 " + serverMain.playerArrayList_Host.get(1).getCountBuildingCards().get(3).getNumber());
        }


        System.out.println("Sending Correct List to Clients After Final Join" + serverMain.playerArrayList_Host.size());
        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
        playerGet = null;
        serverMain.walletTemp();

    }


    private void processMessage(String message) {
        if (message.startsWith(CLIENT_NAME)) {
            handleUpdatePlayers_Client(message);
        } else if(message.startsWith(CLIENT_NAME_VERIFY)){
            handleUpdatePlayers_Client_Name(message);
        } else if (message.startsWith(CLIENT_DISCONNECTED)) {
            handleClientLeft(message);
        } else if(message.startsWith(CHARACTER_SELECTION)){
            handle_CharacterSelection(message);
        } else if(message.startsWith(CHARACTER_UNSELECTION)) {
            handle_CharacterUNSelection(message);
        } else if(message.startsWith(DONE_WITH_CHARACTER_SELECTION)) {
            halfway_CHARACTER_SELECTION(message);
        } else if(message.startsWith(CLIENT_MESSAGE)){
            send_Message_To_All(message);
        } else if(message.startsWith(LEVEL_CARD_NAME)){
            handleLCName(message);
        } else if(message.startsWith(INTERCLICK)){
            handle_Click(message);
        } else if(message.startsWith(FINALCHARACTER)){
            handleFinalCharacter(message);
        } else if(message.startsWith(LEVELDISCONNECTION)){
            handleLevelDisconnection(message);
        }
        else{
            System.out.println("Received Message: " + message); //chat-feature for Mr. Nischal and Mr. Ayan, as this is abstract Message
        }
    }

    private void handleLevelDisconnection(String message) {
        String tempLevelD = message.substring(LEVELDISCONNECTION.length());
        serverMain.finalizeLevelDisconnection(tempLevelD);
    }

    private void handleFinalCharacter(String message) {
        String fCharacter = message.substring(FINALCHARACTER.length());
        serverMain.handleFinalcharacter(fCharacter);
    }

    private void handle_Click(String message) {
        String clickName = message.substring(INTERCLICK.length());
        serverMain.processClickName(clickName);
    }

    private void handleLCName(String message) {
        String LCNAME = message.substring(LEVEL_CARD_NAME.length());
        serverMain.processLCNAME(LCNAME);
    }

    private void send_Message_To_All(String message) {
        String nameAndMessage = message.substring(CLIENT_MESSAGE.length());
        serverMain.tempFinalAndMessage(nameAndMessage);
    }
    public void handleUpdatePlayers_Client(String message){
        String clientName = message.substring(CLIENT_NAME.length());
        serverMain.addClientToList(clientName);
        System.out.println("New Client Joined");
    }
    private void handleUpdatePlayers_Client_Name(String message) {
        String clientName_Verify = message.substring(CLIENT_NAME_VERIFY.length());
        serverMain.addClientToList_Verify(clientName_Verify);
        System.out.println("New Client Verified Name: " + clientName_Verify);
    }
    public void handleClientLeft(String message){
        String clientName = message.substring(CLIENT_DISCONNECTED.length());
        serverMain.removeClientFromList(clientName);
        System.out.println("Host " + clientName + " Disconnected");
    }
    public void handle_CharacterSelection(String message){
        String playerChoosing = message.substring(CHARACTER_SELECTION.length());
        serverMain.characterTempChoose(playerChoosing);
    }
    private void handle_CharacterUNSelection(String message) {
        String playerChoosing = message.substring(CHARACTER_UNSELECTION.length());
        serverMain.characterTempUNChoose(playerChoosing);
    }
    public String[] halfway_CHARACTER_SELECTION(String message){
        String finalPlayerChoosing = message.substring(DONE_WITH_CHARACTER_SELECTION.length());
        String[] finalCharacterChosenInfo = finalPlayerChoosing.split("-");
        System.out.println("Player " + finalCharacterChosenInfo[0] + " Has Finalized Character " + finalCharacterChosenInfo[1]);
        return finalCharacterChosenInfo;
    }

    public void stopListening(){
        isRunning = false;
        serverMain.removeClientOutputStream(out);
        try{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
            if(clientSocket!=null && !clientSocket.isClosed()){
                clientSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}