package Project.src;

import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain{

    private ConnectPanel connectPanel;
    private WaitingForHostPanel waitingForHostPanel;
    private ClientListener clientListener;
    private Socket socket;
    private String serverAddress;
    private String clientName;

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920,1040);
            frame.setContentPane(new ConnectPanel(frame));
            frame.setVisible(true);
        });
    }

    public void setConnectPanel(ConnectPanel connectPanel){
        this.connectPanel = connectPanel;
    }
    public ConnectPanel getConnectPanel(){
        return connectPanel;
    }
    public WaitingForHostPanel setWaitingForHostPanel(WaitingForHostPanel waitingForHostPanel){
        this.waitingForHostPanel = waitingForHostPanel;
    }
    public WaitingForHostPanel getWaitingForHostPanel(){
        return waitingForHostPanel;
    }
    public void connectToServer(String ipAddress, String clientName){
        if(socket != null && !socket.isClosed()){
            System.out.println("Already Connected");
            return;
        }
        try{
            socket = new Socket(ipAddress, 12345);
            clientListener = new ClientListener(socket, this);
            new Thread(clientListener).start();
            CommandFromClient.sendMessage(clientName);
        }catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to Connect to Server", "Connection Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updatePlayerList(String playerNames){
        if(connectPanel != null){
            connectPanel.updatePlayerList(playerNames);
        }
    }
    public void handlePlayerLeft(String playerNames){
        if(connectPanel != null){
            connectPanel.handlePlayerLeft(playerNames);
        }
    }
}