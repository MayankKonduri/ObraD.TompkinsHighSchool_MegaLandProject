package Project.src;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain{

    private ConnectPanel connectPanel;
    private WaitingForHostPanel waitingForHostPanel;
    private ClientListener clientListener;
    private Socket socket;
    private ObjectOutputStream out;
    private ArrayList<String> gamePlayerNames_ClientSide = new ArrayList<>();
    private final String clientName;
    private CharacterSelectPanel characterSelectPanel;
    public ClientMain(String clientName, CharacterSelectPanel characterSelectPanel){
        this.clientName = clientName;
        this.characterSelectPanel = characterSelectPanel;
    }
    //Boolean temp = characterSelectPanel.available.get(0);
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920,1040);
            frame.setContentPane(new ConnectPanel(frame));
            frame.setVisible(true);
        });
    }
    public ClientMain getClientMain(){
        return this;
    }
    public void setConnectPanel(ConnectPanel connectPanel){
        this.connectPanel = connectPanel;
    }
    public ConnectPanel getConnectPanel(){
        return connectPanel;
    }
    public void setWaitingForHostPanel(WaitingForHostPanel waitingForHostPanel){
        this.waitingForHostPanel = waitingForHostPanel;
    }
    public WaitingForHostPanel getWaitingForHostPanel(){
        return waitingForHostPanel;
    }
    public void setcharacterSelectPanel(CharacterSelectPanel characterSelectPanel){
        this.characterSelectPanel = characterSelectPanel;
    }

    public CharacterSelectPanel getCharacterSelectPanel() {
        return characterSelectPanel;
    }

    public boolean connectToServer(String ipAddress, String clientName){
        if(socket != null && !socket.isClosed()){
            System.out.println("Already Connected");
        }
        try{
            socket = new Socket(ipAddress, 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            clientListener = new ClientListener(socket, this);
            new Thread(clientListener).start();
            gamePlayerNames_ClientSide.add(clientName);
            connectPanel.playerListClientSide.add(clientName);
            CommandFromClient.notify_CLIENT_NAME(out, clientName);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to Connect to Server", "Connection Error",JOptionPane.ERROR_MESSAGE);
            connectPanel.confirmButton.setEnabled(true);
            connectPanel.confirmButton.setBackground(null);
            connectPanel.confirmButton.setForeground(null);
            return false;
        }
    }
    public void addHostToList(String hostName) {
        if(!gamePlayerNames_ClientSide.contains(clientName)){
            gamePlayerNames_ClientSide.add(0,clientName);
        }
        connectPanel.playerListClientSide.add(0,clientName);
        connectPanel.updatePlayerList();
    }
    public void addClientToList(String clientName) {
        if(!gamePlayerNames_ClientSide.contains(clientName)){
            gamePlayerNames_ClientSide.add(clientName);
        }
        connectPanel.playerListClientSide.add(clientName);
        connectPanel.updatePlayerList();
    }
    public void removeClientFromList(String clientName) {
        gamePlayerNames_ClientSide.remove(clientName);
        connectPanel.playerListClientSide.remove(clientName);
        connectPanel.updatePlayerList();
    }
    public void characterTempChoose(String playerChoosing) {
        String[] characterChosenInfo = playerChoosing.split("-");
        System.out.println("Player " + characterChosenInfo[0] + " Has Chosen Character " + characterChosenInfo[1]);
        if(!(characterChosenInfo[0].equals(connectPanel.nameTextField.getText()))){
        if(characterChosenInfo[1].equals("catB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[0][0] = characterChosenInfo[0];
            temp[0][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("indianWomanB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[1][0] = characterChosenInfo[0];
            temp[1][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("whiteB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[2][0] = characterChosenInfo[0];
            temp[2][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("frogB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[3][0] = characterChosenInfo[0];
            temp[3][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("gandalfB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[4][0] = characterChosenInfo[0];
            temp[4][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }}
    }
    public void characterTempUNChoose(String playerChoosing) {
        String[] characterUNChosenInfo = playerChoosing.split("-");
        System.out.println("Player " + characterUNChosenInfo[0] + " Has UNChosen Character " + characterUNChosenInfo[1]);
        if(!(characterUNChosenInfo[0].equals(connectPanel.nameTextField.getText()))){
            System.out.println("RUNNING THIS NOOOO!");
        if(characterUNChosenInfo[1].equals("catB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[0][0] = "No_Player";
            temp[0][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("indianWomanB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[1][0] = "No_Player";
            temp[1][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("whiteB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[2][0] = "No_Player";
            temp[2][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("frogB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[3][0] = "No_Player";
            temp[3][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("gandalfB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[4][0] = "No_Player";
            temp[4][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }} else{
            System.out.println("BYPASSED BECAUSE CLIENT RECEIVING SAME");
        }
    }
    public ArrayList<String> getGamePlayerNames_ClientSide(){
        return gamePlayerNames_ClientSide;
    }
    public ObjectOutputStream getOut(){
        return out;
    }
    public Socket getSocket(){
        return socket;
    }
    public void setOut(ObjectOutputStream out){
        this.out = out;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
}