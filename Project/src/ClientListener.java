package Project.src;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientListener implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private ClientMain cLientMain;

    public static final String HOST_NAME = "HOST_NAME:";
    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static final String HOST_DISCONNECTED = "HOST_DISCONNECTED:";
    public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
    public static final String START_GAME = "START_GAME:";
    public static final String DONE_WITH_CARD_SELECTION = "DONE_WITH_CARD_SELECTION:";
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static final String CHARACTER_UNSELECTION = "CHARACTER_UNSELECTION:";

    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static final String ALL_DONE_WITH_CHARACTER_SELECTION = "ALL_DONE_WITH_CHARACTER_SELECTION:";
    public static final String CARDPANEL_TOSTRING = "CARDPANEL_TOSTRING:";
    public static final String CHAT_MESSAGE_HOST = "CHAT_MESSAGE_HOST:";
    public static final String PLAYER_LIST = "PLAYER_LIST:";
    public static final String LEVEL_CARD_NAME = "LEVEL_CARD_NAME:";
    public static final String INTERCLICK = "INTERCLICK:";
    public static final String FINALCHARACTER = "FINALCHARACTER:";
    public static final String LEVELDISCONNECTION = "LEVELDISCONNECTION:";
    public static final String CHANGEMAIN = "CHANGEMAIN:";
    public static final String ENTERNIGHT = "ENTERNIGHT:";
    public static final String DONERUNALL = "DONERUNALL:";
    public static final String DONENIGHT = "DONENIGHT:";








    /*
    public static final String START_GAME = "START_GAME:";
    public static void notify_START_GAME(ObjectOutputStream out, String hostName){
        sendMessage(out, START_GAME + hostName);
    }
    public static final String DONE_WITH_CARD_SELECTION = "DONE_WITH_CARD_SELECTION:";
    public static void notify_DONE_WITH_CARD_SELECTION(ObjectOutputStream out, String hostName) {
        sendMessage(out, DONE_WITH_CARD_SELECTION + hostName);
    }
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static void notify_CHARACTER_SELECTION(ObjectOutputStream out, String hostName, String characterName){
    sendMessage(out, CHARACTER_SELECTION + hostName + "-" + characterName);
}
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static void notify_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out, String hostName) {
        sendMessage(out, DONE_WITH_CHARACTER_SELECTION + hostName);
    }
    public static final String ALL_DONE_WITH_CHARACTER_SELECTION = "ALL_DONE_WITH_CHARACTER_SELECTION:";
    public static void notify_ALL_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out){
        sendMessage(out, ALL_DONE_WITH_CHARACTER_SELECTION);
    }
    public static final String HOST_DISCONNECTED = "HOST_DISCONNECTED:";
    public static void  notify_HOST_DISCONNECTED(ObjectOutputStream out, String hostName){
        sendMessage(out, HOST_DISCONNECTED + hostName);
    }
    public static final String HOST_NAME = "HOST_NAME:";
    public static void notify_HOST_NAME(ObjectOutputStream out, String playerName){
        sendMessage(out, HOST_NAME + playerName);
    }
     */

    public ClientListener(Socket socket, ClientMain clientMain){
        this.socket = socket;
        this.cLientMain = clientMain;
        try{
            this.in = new ObjectInputStream(socket.getInputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            Object receivedObject;
            while ((receivedObject = in.readObject()) != null) {
                if (receivedObject instanceof String) {
                    String message = (String) receivedObject;
                    System.out.println("Received message from server: " + message); // Debug statement
                    processMessage(message);
                } else if (receivedObject instanceof ArrayList<?>) {
                    ArrayList<Player> players = (ArrayList<Player>) receivedObject;
                    processPlayerList(players);
                    setLeaderBoardGameClient1();
                    cLientMain.walletTemp();
                }
            }
        } catch (EOFException e) {
            System.out.println("Connection Closed by Server - Special");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private synchronized void processPlayerList(ArrayList<Player> playerArrayList) {
        cLientMain.handleNewList(playerArrayList);
        if(cLientMain.playerArrayList_client.get(0).getCountBuildingCards().size()>0 && cLientMain.playerArrayList_client.get(1).getCountBuildingCards().size()>0) {
            System.out.println("First: " + cLientMain.playerArrayList_client.get(0).getCountBuildingCards().get(3).getNumber());
            System.out.println("Second: " + cLientMain.playerArrayList_client.get(1).getCountBuildingCards().get(3).getNumber());
            System.out.println(cLientMain.playerArrayList_client.get(1).getPlayerBuildings().isEmpty());
        }
    }

    private void processMessage(String message) {
        if (message.startsWith(HOST_NAME)) {
            handleUpdatePlayers_Host(message);
        }else if (message.startsWith(CLIENT_NAME)){
            handleAddition_Players_Client(message);
        }else if (message.startsWith(HOST_DISCONNECTED)) {
            handleHostLeft(message);
        } else if (message.startsWith(CLIENT_DISCONNECTED)) {
            handleRemoval_Players_Client(message);
        } else if(message.startsWith(START_GAME)) {
            switchingToWaitingForHostPanel(message);
        } else if(message.startsWith(DONE_WITH_CARD_SELECTION)){
            switchingToCharacterSelectPanel(message);
        } else if(message.startsWith(CHARACTER_SELECTION)){
            handle_CharacterSelection(message);
        } else if(message.startsWith(CHARACTER_UNSELECTION)) {
            handle_CharacterUNSelection(message);
        } else if(message.startsWith(DONE_WITH_CHARACTER_SELECTION)){
            halfway_CHARACTER_SELECTION(message);
        } else if (message.startsWith(ALL_DONE_WITH_CHARACTER_SELECTION)){
            switchingToGamePanel(message);
        } else if(message.startsWith(CARDPANEL_TOSTRING)){
            receivedCardPanel(message);
        } else if(message.startsWith(CHAT_MESSAGE_HOST)){
            send_Message_Clients(message);
        } else if(message.startsWith(PLAYER_LIST)) {
            send_List(message);
        } else if(message.startsWith(LEVEL_CARD_NAME)){
            handleLevelCard(message);
        } else if(message.startsWith(INTERCLICK)){
            handleInterClick(message);
        } else if(message.startsWith(FINALCHARACTER)){
            handleFinalCharacter(message);
        } else if(message.startsWith(LEVELDISCONNECTION)){
            handleLevelDisconnection(message);
        } else if(message.startsWith(CHANGEMAIN)){
            handleChange(message);
        } else if(message.startsWith(DONERUNALL)){
            handleRunAll();
        } else if(message.startsWith(ENTERNIGHT)) {
            handleEnterNight();
        } else if(message.startsWith(DONENIGHT)){
            handleDoneNight();
        }
        else{
            System.out.println("Received Message: " + message); //chat-feature for Mr. Nischal and Mr. Ayan, as this is abstract Message
        }
    }

    private void handleDoneNight() {
        cLientMain.processDoneNight();
    }

    private void handleRunAll() {
        cLientMain.doneAll();
    }

    private void handleEnterNight() {
        cLientMain.processEnterNight();
    }

    private void handleChange(String message) {
        String tempChange = message.substring(CHANGEMAIN.length());
        cLientMain.processChange(tempChange);
    }

    public void setLeaderBoardGameClient1(){
        cLientMain.setLeaderBoardGameClient();
    }

    private void handleLevelDisconnection(String message) {
        String tempLevelD = message.substring(LEVELDISCONNECTION.length());
        cLientMain.finalizeLevelDisconnection(tempLevelD);
    }

    private void handleFinalCharacter(String message) {
        String fCharacter = message.substring(FINALCHARACTER.length());
        cLientMain.handleFinalCharacter(fCharacter);
    }
    private void handleInterClick(String message) {
        String nameClicked = message.substring(INTERCLICK.length());
        cLientMain.processInterClick(nameClicked);
    }

    private void handleLevelCard(String message) {
        String LCName = message.substring(LEVEL_CARD_NAME.length());
        cLientMain.processLCName(LCName);
    }

    private void send_List(String message) {
        String processed_List = message.substring(PLAYER_LIST.length());
        cLientMain.fix_List(processed_List);
    }

    private void receivedCardPanel(String message) {
        String arrayListCardPanel = message.substring(CARDPANEL_TOSTRING.length());
        cLientMain.makeCardChosenAccessible(arrayListCardPanel);
    }

    public void handleUpdatePlayers_Host(String message){
        String hostName = message.substring(HOST_NAME.length());
        cLientMain.addHostToList(hostName);
    }
    public void handleAddition_Players_Client(String message){
        String clientName = message.substring(CLIENT_NAME.length());
        cLientMain.addClientToList(clientName);
    }
    public void handleRemoval_Players_Client(String message){
        String clientName = message.substring(CLIENT_DISCONNECTED.length());
        cLientMain.removeClientFromList(clientName);
        System.out.println("New Client Joined: " + clientName);
    }
    public void handleHostLeft(String message){
        String hostName = message.substring(HOST_DISCONNECTED.length());
        cLientMain.getConnectPanel().switchToLoadingPanel();
        System.out.println("Host " + hostName + " Disconnected");
    }
    public void switchingToWaitingForHostPanel(String message){
        String hostName = message.substring(START_GAME.length());
        cLientMain.getConnectPanel().switchToWaitingForHostPanel();
        System.out.println("Waiting for Host " + hostName + " to Select Cards");
    }
    public void switchingToCharacterSelectPanel(String message){
        String hostName = message.substring(DONE_WITH_CARD_SELECTION.length());
        cLientMain.getWaitingForHostPanel().switchToCharacterSelectPanel();
        System.out.println("Host " + hostName + " Done With Cards Selection, Please Choose Character");
    }
    public void handle_CharacterSelection(String message){
        String playerChoosing = message.substring(CHARACTER_SELECTION.length());
        cLientMain.characterTempChoose(playerChoosing);
    }
    private void handle_CharacterUNSelection(String message) {
        String playerChoosing = message.substring(CHARACTER_UNSELECTION.length());
        cLientMain.characterTempUNChoose(playerChoosing);
    }
    public String[] halfway_CHARACTER_SELECTION(String message){
        String finalPlayerChoosing = message.substring(DONE_WITH_CHARACTER_SELECTION.length());
        String[] finalCharacterChosenInfo = finalPlayerChoosing.split("-");
        return finalCharacterChosenInfo;
    }
    public void send_Message_Clients(String message){
        String nameAndMessage = message.substring(CHAT_MESSAGE_HOST.length());
        cLientMain.tempFinalAndMessage(nameAndMessage);
    }

    public void switchingToGamePanel(String message){
        cLientMain.finalCharacterMethod();
        cLientMain.getCharacterSelectPanel().switchToGamePanel();
    }

}