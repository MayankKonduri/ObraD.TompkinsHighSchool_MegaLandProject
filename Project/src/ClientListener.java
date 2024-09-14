package Project.src;

import java.io.*;
import java.net.Socket;

public class ClientListener implements Runnable{
    private Socket socket;
    private ObjectInputStream in;
    private ClientMain cLientMain;

    public static final String HOST_NAME = "HOST_NAME:";
    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static final String HOST_DISCONNECTED = "HOST_DISCONNECTED:";
    public static final String START_GAME = "START_GAME:";
    public static final String DONE_WITH_CARD_SELECTION = "DONE_WITH_CARD_SELECTION:";
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static final String ALL_DONE_WITH_CHARACTER_SELECTION = "ALL_DONE_WITH_CHARACTER_SELECTION:";


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
    public void run(){
        try{
            String message;
            while((message = in.readLine()) != null) {
                System.out.println("Received message from server: " + message); // Debug statement
                processMessage(message);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void processMessage(String message) {
        if (message.startsWith(HOST_NAME)) {
            handleUpdatePlayers_Host(message);
        }else if (message.startsWith(CLIENT_NAME)){
            handleUpdatePlayers_Client(message);
        }else if (message.startsWith(HOST_DISCONNECTED)) {
            handleHostLeft(message);
        } else if(message.startsWith(START_GAME)) {
            switchingToWaitingForHostPanel(message);
        } else if(message.startsWith(DONE_WITH_CARD_SELECTION)){
            switchingToCharacterSelectPanel(message);
        } else if(message.startsWith(CHARACTER_SELECTION)){
            handle_CharacterSelection(message);
        } else if(message.startsWith(DONE_WITH_CHARACTER_SELECTION)){
            halfway_CHARACTER_SELECTION(message);
        } else if (message.startsWith(ALL_DONE_WITH_CHARACTER_SELECTION)){
            switchingToGamePanel(message);
        }
        else{
            System.out.println("Received Message: " + message); //chat-feature for Mr. Nischal and Mr. Ayan, as this is abstract Message
        }
    }
    public void handleUpdatePlayers_Host(String message){
        String hostName = message.substring(HOST_NAME.length());
        cLientMain.updatePlayerList(hostName);
        System.out.println("New Host Joined: " + hostName);
    }
    public void handleUpdatePlayers_Client(String message){
            String clientName = message.substring(CLIENT_NAME.length());
            cLientMain.updatePlayerList(clientName);
            System.out.println("New Client Joined: " + clientName);
    }
    public void handleHostLeft(String message){
        String hostName = message.substring(HOST_DISCONNECTED.length());
        cLientMain.getConnectPanel().switchToLoadingPanel();
        System.out.println("Host " + hostName + " Disconnected");
    }
    public void switchingToWaitingForHostPanel(String message){
        String hostName = message.substring(START_GAME.length());
        cLientMain.getConnectPanel().switchToWaitingForHostPane();
        System.out.println("Waiting for Host " + hostName + " to Select Cards");
    }
    public void switchingToCharacterSelectPanel(String message){
        String hostName = message.substring(DONE_WITH_CARD_SELECTION.length());
        cLientMain.getWaitingForHostPanel().switchToCharacterSelectPane();
        System.out.println("Host " + hostName + " Done With Cards Selection, Please Choose Character");
    }
    public String[] handle_CharacterSelection(String message){
        String playerChoosing = message.substring(CHARACTER_SELECTION.length());
        String[] characterChosenInfo = playerChoosing.split("-");
        System.out.println("Player " + characterChosenInfo[0] + " Has Chosen Character " + characterChosenInfo[1]);
        return characterChosenInfo;
    }
    public String[] halfway_CHARACTER_SELECTION(String message){
        String finalPlayerChoosing = message.substring(DONE_WITH_CHARACTER_SELECTION.length());
        String[] finalCharacterChosenInfo = finalPlayerChoosing.split("-");
        System.out.println("Player " + finalCharacterChosenInfo[0] + " Has Finalized Character " + finalCharacterChosenInfo[1]);
        return finalCharacterChosenInfo;
    }
    public void switchingToGamePanel(String message){
        cLientMain.getCharacterSelectPanel().switchToGamePanel();
    }

}