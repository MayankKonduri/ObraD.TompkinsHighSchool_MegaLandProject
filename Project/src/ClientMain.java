package Project.src;

import javax.swing.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientMain{
    JFrame frame;
    private ConnectPanel connectPanel;
    private ArrayList<String> playerList = new ArrayList<>();
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Client");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1920,1040);
            frame.setContentPane(new ConnectPanel(frame));
            frame.setVisible(true);
        });
    }
    public void updatePlayerList(String[] playerNames){
        playerList.clear();
        for(String playerName: playerNames){
            playerList.add(playerName);
        }
        if(connectPanel != null){
            SwingUtilities.invokeLater(() -> connectPanel.updatePlayerList(playerNames));
        }
    }
    public void setConnectPanel(ConnectPanel connectPanel){
        this.connectPanel = connectPanel;
    }
    public void handlePLayerLeft(String playerName) {
        playerList.remove(playerName);
        if(connectPanel != null){
            SwingUtilities.invokeLater(() -> connectPanel.handlePlayerLeft(playerName));
        }
    }
    public boolean playerNameExists(String name){
        return playerList.contains(name);
    }
}