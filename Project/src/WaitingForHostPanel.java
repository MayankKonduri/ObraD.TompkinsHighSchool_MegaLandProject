package Project.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WaitingForHostPanel extends JPanel {

    private JFrame jframe1;
    private ClientMain clientMain;
    private ConnectPanel connectPanel;
    private CharacterSelectPanel characterSelectPanel;
    private BufferedImage loading;
    private String connectionStatus = "Connecting...";
    private int retryAttempts = 0;
    private ArrayList<String> playerList = new ArrayList<>();
    private Cleaner.Cleanable cleanableResource;
    private boolean isServerFull = false;
    private int maxPlayers = 8;
    public ArrayList<String> waitingTips = new ArrayList<>();
    private Player playerObject;
    private String connectionStatus = "Connecting...";
    private int retryAttempts = 0;
    private ArrayList<String> playerList = new ArrayList<>();
    private Cleaner.Cleanable cleanableResource;

    public WaitingForHostPanel(JFrame frame, ClientMain clientMain, ConnectPanel connectPanel, Player player){
        setLayout(null);

        this.clientMain = clientMain;
        this.jframe1 = frame;
        this.connectPanel = connectPanel;
        this.playerObject = player;

        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\Waiting_v2.jpg")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        waitingTips.add("Balancing risk vs. reward is key: the further you go, the more treasures, but the more danger.");
        waitingTips.add("Keep an eye on your heart count and consider stopping early to avoid losing treasures.");
        waitingTips.add("Use your hearts strategically...");
        waitingTips.add("Remember there is only a certain amount of level cards...");
        waitingTips.add("Keep treasures you can use to buy buildings when faced with discarding.");
        waitingTips.add("Pay attention to your opponents, especially during the building phase...");
        waitingTips.add("Don’t hesitate to return early from an adventure if you feel the risks outweigh the rewards.");
        waitingTips.add("A balanced approach with a mix of income-generating, defensive, and heart-restoring buildings can be more resilient.");
        waitingTips.add("Some buildings are more valuable in the early game, such as those that give coins or hearts, while others become more useful in the late game.");

        Collections.shuffle(waitingTips);

        clientMain.setWaitingForHostPanel(this);
        JLabel waitingLabel = new JLabel("Waiting for Host to Finish Card Selection...");
        waitingLabel.setBounds(0,20,1920,70);
        waitingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        waitingLabel.setForeground(Color.black);
        waitingLabel.setFont(new Font("Georgia", Font.BOLD,50));
        add(waitingLabel);

        JLabel tip = new JLabel("Tip: " + waitingTips.get(0));
        tip.setBounds(0, 930, 1920, 40);
        tip.setFont(new Font("Georgia", Font.BOLD,25));
        tip.setHorizontalAlignment(SwingConstants.CENTER);
        tip.setForeground(Color.WHITE);
        add(tip);


        revalidate();
        repaint();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(clientMain != null && clientMain.getOut()!=null) {
                //sendDisconnectMessage();
            }
        }));

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.out.println("No Can Do");
            }
        });


    }
    public void setPlayerObject(Player playerObject){
        this.playerObject = playerObject;
    }

    public void switchToCharacterSelectPanel() {

        characterSelectPanel = new CharacterSelectPanel(jframe1, clientMain, null, null, connectPanel,false, null);
        connectPanel.setCharacterSelectPanel(characterSelectPanel);

        characterSelectPanel.setPreferredSize(new Dimension(1920,1040));

        JScrollPane scrollPane1 = new JScrollPane(characterSelectPanel);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane1);
        jframe1.setContentPane(scrollPane1);

        jframe1.pack();
        jframe1.revalidate();
        jframe1.repaint();
        jframe1.setVisible(true);
    }

    public CharacterSelectPanel getCharacterSelectPanel(){
        return characterSelectPanel;
    }
    public void paintCompnent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1050, null);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1050, null);
    }
    public void checkHostStatus() {
        if (clientMain != null && retryAttempts < 5) {
            retryAttempts++;
            connectionStatus = "Retrying connection... Attempt " + retryAttempts;
            revalidate();
            repaint();
        } else {
            connectionStatus = "Host not reachable";
        }
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void addPlayerToList(String playerName) {
        if (!playerList.contains(playerName)) {
            playerList.add(playerName);
            revalidate();
            repaint();
        }
    }

    public void releaseResources() {
        if (cleanableResource != null) {
            cleanableResource.clean();
        }
    }

    public boolean isConnectionStable() {
        return retryAttempts < 3;  // Arbitrary threshold for connection stability
    }

    public void resetConnectionStatus() {
        connectionStatus = "Reconnecting...";
        retryAttempts = 0;
    }

    public void updateMaxPlayers(int newMax) {
        if (newMax > 0) {
            this.maxPlayers = newMax;
        }
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
    public ArrayList<String> getPlayerList() {
        return playerList;
    }
    public void checkHostStatus() {
        if (clientMain != null && retryAttempts < 5) {
            retryAttempts++;
            connectionStatus = "Retrying connection... Attempt " + retryAttempts;
            revalidate();
            repaint();
        } else {
            connectionStatus = "Host not reachable";
        }
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }



    public void removePlayerFromList(String playerName) {
        playerList.remove(playerName);
        revalidate();
        repaint();
    }

    public void releaseResources() {
        if (cleanableResource != null) {
            cleanableResource.clean();
        }
    }

    public boolean isServerFull() {
        return isServerFull;
    }

    public ArrayList<String> getPlayerList() {
        return playerList;
    }

    ```java
    public void refreshPanel() {
        revalidate();
        repaint();
    }

    public void displayLoadingMessage() {
        System.out.println("Loading, please wait...");
    }

    public void toggleServerFullStatus() {
        isServerFull = !isServerFull;
    }

    public void resetRetryAttempts() {
        retryAttempts = 0;
    }

    public void incrementRetryAttempts() {
        retryAttempts++;
    }

    public void updateConnectionMessage(String newStatus) {
        connectionStatus = newStatus;
        revalidate();
        repaint();
    }
```

    public int getRemainingSlots() {
        return maxPlayers - playerList.size();
    }

    public void showChatPanel() {
        chatPanel1 = new ChatPanel();
        JFrame chatFrame = new JFrame("Chat");
        chatFrame.add(chatPanel1);
        chatFrame.setSize(300, 400);
        chatFrame.setVisible(true);
    }

    public void updateErrorArea(String message) {
        ErrorArea.setText(message);
    }

    public void toggleTakeOffButton() {
        takeOff.setEnabled(!takeOff.isEnabled());
    }

    public void displayPlayerHealth() {
        for (BuildingCards building : playerBuildings) {
            int health = building.getHealth();
            System.out.println("Building health: " + health);
        }
    }

    public void resetGameData() {
        playerBuildings.clear();
        players.clear();
    }

    public void logGameActions() {
        System.out.println("Game actions logged.");
    }

    public void setupLevelCardDeck() {
        deckLevelCard.add(new LevelCard("Level 1", 1));
        deckLevelCard.add(new LevelCard("Level 2", 2));
        // Add more level cards as needed
    }

}
