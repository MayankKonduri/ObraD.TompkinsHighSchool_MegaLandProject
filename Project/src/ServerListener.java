package Project.src;

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



    /*
    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static void notify_CLIENT_NAME(ObjectOutputStream out, String clientName){
        sendMessage(out, CLIENT_NAME + clientName);
    }

    public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
    public static void notify_CLIENT_DISCONNECTED(ObjectOutputStream out, String clientName) {
        sendMessage(out, CLIENT_DISCONNECTED + clientName);
    }
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static void notify_CHARACTER_SELECTION(ObjectOutputStream out, String playerName, String characterName){
        sendMessage(out, CHARACTER_SELECTION + playerName + "-" + characterName);
    }
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static void notify_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out, String playerName) {
        sendMessage(out, DONE_WITH_CHARACTER_SELECTION + playerName);
    }
     */

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
                    System.out.println("Received message from server: " + message); // Debug statement
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
        System.out.println("Debug Lib: " + playerGet.getPlayerID());
        int index = -1;
        System.out.println(serverMain.playerArrayList_Host.size());
        for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {

            String playerName = serverMain.playerArrayList_Host.get(i).getPlayerName();
            System.out.println("Player Name In Order: " + playerName);
            if (playerGet.getPlayerName().equals(playerName)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            if (index < serverMain.playerArrayList_Host.size()) {
                System.out.println(playerGet.getPlayerName());
                serverMain.playerArrayList_Host.set(index, playerGet);
                System.out.println("Player updated at index: " + index);
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


        for(int i=0;i<serverMain.playerArrayList_Host.size();i++){
            System.out.println(serverMain.playerArrayList_Host.get(i).getPlayerName());
            System.out.println(serverMain.playerArrayList_Host.get(i).getPlayerID());
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
    /*
    public void handleClientConnection(Socket clientSocket){
        try {
            String clientID = clientSocket.getInetAddress().toString();

            if(clientHandlers.stream().anyMatch(handler -> handler.getClientSocket().getInetAddress().toString().equals(clientID))){
                System.out.print("Client Already Connected: " + clientID);
                clientSocket.close();
                return;

        }}
    public synchronized void addClient(ClientHandler clientHandler){
        clientHandlers.add(clientHandler);
        System.out.println("Client added: " + clientHandler.getName());
        printClientHandlers();
        if(clientUpdateListener != null){
            clientUpdateListener.onClientAdded(clientHandler);
        }
    }

    public void printClientHandlers() {
        System.out.println("Current Clients:");
        for (ClientHandler clientHandler : clientHandlers) {
            System.out.println("Client: " + clientHandler.getName() + " at " + clientHandler.getClientSocket().getInetAddress());
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler){
        synchronized (clientHandlers){
            clientHandlers.remove(clientHandler);}
        if(clientUpdateListener!=null){
            clientUpdateListener.onClientRemoved(clientHandler);
        }
        notifyClientOfNewConnection(clientHandler);
    }
    public synchronized void notifyClientOfNewConnection(ClientHandler newClient){
        synchronized (clientHandlers){
            for(ClientHandler handler : clientHandlers){
                if(!handler.equals(newClient)){
                    handler.sendMessage("NEW_CLIENT" + newClient.getName());
                    System.out.println("Notified client of new connection: " + newClient.getName());

                }
            }
        }
    }
    private synchronized void notifyClientOfDisconnection(ClientHandler disconnectedClient){
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                handler.getOut().println("PLAYER_LEFT\n" + disconnectedClient.getName());
            }
        }
    }
    public synchronized void stop(){

    }
    public synchronized void notifyClientOfHostDisconnection(){
        synchronized (clientHandlers){
            for(ClientHandler handler : clientHandlers){
                handler.getOut().println("HOST_DISCONNECTED");
            }
        }
    }
    public ArrayList<ClientHandler> getClientHandlers(){
        return clientHandlers;  }*/
}