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
    private CharacterSelectPanel characterSelectPanel;
    private ClientListener clientListener;
    private Socket socket;
    private ObjectOutputStream out;
    private ArrayList<String> gamePlayerNames_ClientSide = new ArrayList<>();
    private final String clientName;

    public ClientMain(String clientName){
        this.clientName = clientName;
    }

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

    public void connectToServer(String ipAddress, String clientName){
        if(socket != null && !socket.isClosed()){
            System.out.println("Already Connected");
            return;
        }
        try{
            socket = new Socket(ipAddress, 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            clientListener = new ClientListener(socket, this);
            new Thread(clientListener).start();
            gamePlayerNames_ClientSide.add(clientName);
            connectPanel.playerListClientSide.add(clientName);
            CommandFromClient.notify_CLIENT_NAME(out, clientName);
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to Connect to Server", "Connection Error",JOptionPane.ERROR_MESSAGE);
            connectPanel.confirmButton.setEnabled(true);
            connectPanel.confirmButton.setBackground(null);
            connectPanel.confirmButton.setForeground(null);
        }
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