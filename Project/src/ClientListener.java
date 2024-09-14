package Project.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private ClientMain cLientMain;
    public static final String START_GAME = "START_GAME";
    public static final String DONE_WITH_CARD_SELECTION = "DONE_WITH_CARD_SELECTION";
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION";
    public static final String HOST_DISCONNECTED = "HOST_DISCONNECTED";
    public static final String HOST_NAME = "HOST_NAME:";
    public static final String CLIENT_NAME = "CLIENT_NAME:";


    public ClientListener(Socket socket, ClientMain clientMain){
        this.socket = socket;
        this.cLientMain = clientMain;
        try{
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            handleHostLeft();
        } else if(message.equals(START_GAME)) {
            switchingToWaitingForHostPane();
        } else if(message.equals(DONE_WITH_CARD_SELECTION)){
            switchingToCharacterSelectPane();
        } else{
            System.out.println("Received Message: " + message); //chat-feature
        }
    }
    public void handleUpdatePlayers_Host(String message){
        String hostName = message.substring(HOST_NAME.length());
        cLientMain.updatePlayerList(hostName);
        System.out.println("Updated player list received: " + hostName);
    }
    public void handleUpdatePlayers_Client(String message){
            String clientName = message.substring(CLIENT_NAME.length());
            cLientMain.updatePlayerList(clientName);
            System.out.println("Updated player list received: " + clientName);
    }
    public void handleHostLeft(){
        cLientMain.getConnectPanel().switchToLoadingPanel();
        System.out.println("Host Disconnected");
    }
    public void switchingToWaitingForHostPane(){
        cLientMain.getConnectPanel().switchToWaitingForHostPane();
        System.out.println("Waiting for Host to Select Cards");
    }
    public void switchingToCharacterSelectPane(){
        cLientMain.getWaitingForHostPanel().switchToCharacterSelectPane();
        System.out.println("Please Select Your Character");
    }
}