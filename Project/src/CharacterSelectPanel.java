package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;

public class CharacterSelectPanel extends JPanel implements Serializable {
    private BufferedImage indianWoman, gandalf, cat, frog, white, loading;
    private JLabel title = new JLabel ("Choose Your Characters Wisely!");
    private JLabel selected = new JLabel ("");
    private JButton gandalfB = new JButton ("Available");
    private JButton whiteB = new JButton ("Available");
    private JButton indianWomanB = new JButton ("Available");
    private JButton catB = new JButton ("Available");
    private JButton frogB = new JButton ("Available");
    public ArrayList<Boolean> available= new ArrayList<Boolean>();
    private JButton done = new JButton ("Done");
    private boolean isSelected = false;
    private String characterSelected;
    private JFrame jFrame1;
    private ClientMain clientMain;
    private ServerMain serverMain;
    private Boolean isHost;
    private HostPanel hostPanel;
    private ConnectPanel connectPanel;
    private CardSelectPanel cardSelectPanel;
    public Player playerClient;

    private CommandFromClient commandFromClient;
    public Object[][] FINAL_ARRAY = new Object[5][2];


    public CharacterSelectPanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, Boolean isHost, CardSelectPanel cardSelectPanel) {
        setSize(1920, 1010);
        setLayout(null);
        if(!isHost) {
            System.out.println("ID Before Creating Object: " + clientMain.Final_gamePlayerNames_ClientSide);
            playerClient = new Player(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()), connectPanel.nameTextField.getText(), false, 0, 0, 0, true, true, false, new ArrayList<BuildingCards>(), new ArrayList<TreasureCard>(), new ArrayList<LevelCard>(), null);
            //waitingForHostPanel.setPlayerObject(playerClient);
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), playerClient);
        }
        FINAL_ARRAY[0][0] = "No_Player";
        FINAL_ARRAY[1][0] = "No_Player";
        FINAL_ARRAY[2][0] = "No_Player";
        FINAL_ARRAY[3][0] = "No_Player";
        FINAL_ARRAY[4][0] = "No_Player";
        FINAL_ARRAY[0][1] = "Available";
        FINAL_ARRAY[1][1] = "Available";
        FINAL_ARRAY[2][1] = "Available";
        FINAL_ARRAY[3][1] = "Available";
        FINAL_ARRAY[4][1] = "Available";

        this.jFrame1 = frame;
        this.clientMain = clientMain;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;
        this.connectPanel = connectPanel;
        this.isHost = isHost;
        this.cardSelectPanel = cardSelectPanel;

        if(clientMain != null){
            clientMain.setcharacterSelectPanel(this);
        } else{
            System.err.println("ClientMain is not Init.");
        }


        for(int i=0;i<=4;i++){
            available.add(false);
        }


        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\Character_v.jpg")));
            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(0, 50, 1920,75);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Georgia", Font.BOLD, 50));
        title.setForeground(Color.black);



        catB.setBounds(145, 800, 180, 30);
        catB.setBackground(Color.BLACK);
        catB.setForeground(Color.WHITE);
        catB.setFocusable(false);


        indianWomanB.setBounds(510, 800, 180, 30);
        indianWomanB.setBackground(Color.BLACK);
        indianWomanB.setForeground(Color.WHITE);
        indianWomanB.setFocusable(false);
        whiteB.setBounds(875, 800, 180, 30);
        whiteB.setBackground(Color.BLACK);
        whiteB.setForeground(Color.WHITE);
        whiteB.setFocusable(false);
        frogB.setBounds(1235, 800, 180, 30);
        frogB.setBackground(Color.BLACK);
        frogB.setForeground(Color.WHITE);
        frogB.setFocusable(false);
        gandalfB.setBounds(1600, 800, 180, 30);
        gandalfB.setBackground(Color.BLACK);
        gandalfB.setForeground(Color.WHITE);
        gandalfB.setFocusable(false);
        selected.setBounds(0, 900, 1920, 70);
        selected.setHorizontalAlignment(SwingConstants.CENTER);
        selected.setFont(new Font("Georgia", Font.BOLD, 40));
        selected.setForeground(Color.white);


        /*JLabel test;
        test = new JLabel("isHost");
        if(isHost) {
            test = new JLabel("isHostSquared");
            test.setBounds(600,900,700,70);
            add(test);
            gandalfB.setEnabled(false);
        }
        if(isHost == false){
            test = new JLabel("isClient");
            test.setBounds(600,900,700,70);
            add(test);
            gandalfB.setEnabled(true);
        }*/


        catB.addActionListener(e -> {
            if(catB.isEnabled()) {
                if(isSelected)
                {
                    notifyCharacterUNSelection(characterSelected);
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage(null);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage(null);
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }
                }
                if (catB.getText().equals("Available")) {
                    characterSelected = "catB";
                    reset(catB);
                    catB.setText("Selected");
                    catB.setBackground(Color.WHITE);
                    catB.setForeground(Color.BLACK);
                    available.set(0, true);
                    selected.setText("Your Character is Cat");
                    isSelected = true;
                    System.out.println("Selected: catB");
                    notifyCharacterSelection("catB");
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage("cat");
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage("cat");
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }
                    if(isHost){
                        FINAL_ARRAY[0][0] = hostPanel.nameTextField.getText();
                    } else {
                        FINAL_ARRAY[0][0] = connectPanel.nameTextField.getText();
                    }
                    FINAL_ARRAY[0][1] = "Mine";
                    updateAvailability(FINAL_ARRAY);
                } else {
                    characterSelected = "";
                    catB.setText("Available");
                    System.out.println("Unselected: catB");
                    isSelected = false;
                    catB.setBackground(Color.BLACK);
                    catB.setForeground(Color.white);
                    available.set(0, false);
                    selected.setText("Your Character is ");
                    notifyCharacterUNSelection("catB");
                    FINAL_ARRAY[0][0] = "No_Player";
                    FINAL_ARRAY[0][1] = "Available";
                    updateAvailability(FINAL_ARRAY);
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage(null);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage(null);
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }

                }
                System.out.println(available.toString());
            }
        });
        indianWomanB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            if(indianWomanB.getText().equals("Available")) {
                characterSelected = "indianWomanB";
                reset(indianWomanB);
                indianWomanB.setText("Selected");
                indianWomanB.setBackground(Color.white);
                indianWomanB.setForeground(Color.BLACK);
                available.set(1,true);
                selected.setText("Your Character is Woman");
                isSelected = true;
                System.out.println("Selected: indianwomanB");
                notifyCharacterSelection("indianWomanB");
                if(isHost){
                    hostPanel.playerHost.setPlayerImage("indianWoman");
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage("indianWoman");
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
                if(isHost){
                    FINAL_ARRAY[1][0] = hostPanel.nameTextField.getText();
                } else {
                    FINAL_ARRAY[1][0] = connectPanel.nameTextField.getText();
                }
                FINAL_ARRAY[1][1] = "Mine";
                updateAvailability(FINAL_ARRAY);

            } else {
                characterSelected = "";
                indianWomanB.setText("Available");
                System.out.println("Unselected: indianWomanB");
                isSelected = false;
                indianWomanB.setBackground(Color.black);
                indianWomanB.setForeground(Color.white);
                available.set(1,false);
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("indianWomanB");
                FINAL_ARRAY[1][0] = "No_Player";
                FINAL_ARRAY[1][1] = "Available";
                updateAvailability(FINAL_ARRAY);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            System.out.println(available.toString());
        });
        whiteB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            if(whiteB.getText().equals("Available")) {
                characterSelected = "whiteB";
                reset(whiteB);
                whiteB.setText("Selected");
                whiteB.setBackground(Color.white);
                whiteB.setForeground(Color.BLACK);
                available.set(2,true);
                selected.setText("Your Character is White Boy");
                isSelected = true;
                System.out.println("Selected: whiteB");
                notifyCharacterSelection("whiteB");
                if(isHost){
                    hostPanel.playerHost.setPlayerImage("white");
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage("white");
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
                if(isHost){
                    FINAL_ARRAY[2][0] = hostPanel.nameTextField.getText();
                } else {
                    FINAL_ARRAY[2][0] = connectPanel.nameTextField.getText();
                }
                FINAL_ARRAY[2][1] = "Mine";
                updateAvailability(FINAL_ARRAY);
            } else {
                characterSelected = "";
                whiteB.setText("Available");
                System.out.println("Unselected: whiteB");
                whiteB.setBackground(Color.black);
                whiteB.setForeground(Color.white);
                available.set(2,false);
                isSelected = false;
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("whiteB");
                FINAL_ARRAY[2][0] = "No_Player";
                FINAL_ARRAY[2][1] = "Available";
                updateAvailability(FINAL_ARRAY);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            System.out.println(available.toString());
        });
        frogB.addActionListener(e -> {
            if(frogB.isEnabled()) {
                if (isSelected) {
                    notifyCharacterUNSelection(characterSelected);
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage(null);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage(null);
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }
                }
                if (frogB.getText().equals("Available")) {
                    characterSelected = "frogB";
                    reset(frogB);
                    frogB.setText("Selected");
                    frogB.setBackground(Color.white);
                    frogB.setForeground(Color.BLACK);
                    available.set(3, true);
                    selected.setText("Your Character is Froggy");
                    isSelected = true;
                    System.out.println("Selected: frogB");
                    notifyCharacterSelection("frogB");
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage("frog");
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage("frog");
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }
                    if (isHost) {
                        FINAL_ARRAY[3][0] = hostPanel.nameTextField.getText();
                    } else {
                        FINAL_ARRAY[3][0] = connectPanel.nameTextField.getText();
                    }
                    FINAL_ARRAY[3][1] = "Mine";
                    updateAvailability(FINAL_ARRAY);
                } else {
                    characterSelected = "";
                    frogB.setText("Available");
                    System.out.println("Unselected: frogB");
                    isSelected = false;
                    frogB.setBackground(Color.black);
                    frogB.setForeground(Color.white);
                    available.set(3, false);
                    selected.setText("Your Character is ");
                    notifyCharacterUNSelection("frogB");
                    FINAL_ARRAY[3][0] = "No_Player";
                    FINAL_ARRAY[3][1] = "Available";
                    updateAvailability(FINAL_ARRAY);
                    if(isHost){
                        hostPanel.playerHost.setPlayerImage(null);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    }
                    else{
                        this.playerClient.setPlayerImage(null);
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                    }
                }
                System.out.println(available.toString());
            }
        });
        gandalfB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            if(gandalfB.getText().equals("Available")) {
                characterSelected = "gandalfB";
                reset(gandalfB);
                gandalfB.setText("Selected");
                gandalfB.setBackground(Color.white);
                gandalfB.setForeground(Color.BLACK);
                available.set(3,true);
                selected.setText("Your Character is Gandalf");
                isSelected = true;
                System.out.println("Selected: gandalfB");
                notifyCharacterSelection("gandalfB");
                if(isHost){
                    hostPanel.playerHost.setPlayerImage("gandalf");
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage("gandalf");
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
                if(isHost){
                    FINAL_ARRAY[4][0] = hostPanel.nameTextField.getText();
                } else {
                    FINAL_ARRAY[4][0] = connectPanel.nameTextField.getText();
                }
                FINAL_ARRAY[4][1] = "Mine";
                updateAvailability(FINAL_ARRAY);
            } else {
                characterSelected = "";
                gandalfB.setText("Available");
                System.out.println("Unselected: gandalfB");
                gandalfB.setBackground(Color.black);
                gandalfB.setForeground(Color.white);
                available.set(4,false);
                isSelected = false;
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("gandalfB");
                FINAL_ARRAY[4][0] = "No_Player";
                FINAL_ARRAY[4][1] = "Available";
                updateAvailability(FINAL_ARRAY);
                if(isHost){
                    hostPanel.playerHost.setPlayerImage(null);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }
                else{
                    this.playerClient.setPlayerImage(null);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), this.playerClient);
                }
            }
            System.out.println(available.toString());
        });
        done.setBounds(1770, 900, 100, 60);
        done.setFont(new Font ("Georgia", Font.BOLD, 20));
        done.setEnabled(false);
        done.setForeground(Color.white);
        done.setBackground(Color.black);
        done.setBorder(BorderFactory.createEmptyBorder());
        done.setFocusable(false);
        done.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                done.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                done.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        done.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                GamePanel gamePanel = new GamePanel(frame, null, serverMain, hostPanel, null, cardSelectPanel, this, true, null, null);
                gamePanel.setPreferredSize(new Dimension(1920,1040));
                serverMain.setGamePanel(gamePanel);
                JScrollPane scrollPane1 = new JScrollPane(gamePanel);
                scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                add(scrollPane1);
                frame.setContentPane(scrollPane1);

                frame.pack();
                frame.revalidate();
                frame.repaint();
                frame.setVisible(true);
            });
            serverMain.broadcastMessage(9, hostPanel.nameTextField.getText());
            serverMain.broadcastMessage(15, hostPanel.playerHost.getPlayerImage());
        });


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(serverMain != null) {
                serverMain.broadcastMessage(3, hostPanel.nameTextField.getText());
                serverMain.stopServer();
            }
        }));

        jFrame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame1.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(serverMain != null) {
                    serverMain.broadcastMessage(3, hostPanel.nameTextField.getText());
                    serverMain.stopServer();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1050, null);
        g.drawImage(cat, 65, 250, 360, 540, null);
        g.drawImage(indianWoman, 425, 250, 360, 540, null);
        g.drawImage(white, 785, 250, 360, 540, null);
        g.drawImage(frog, 1145, 250, 360, 540, null);
        g.drawImage(gandalf, 1505, 250, 360, 540, null);


        add(title);
        add(selected);
        add(gandalfB);
        add(indianWomanB);
        add(frogB);
        add(whiteB);
        add(catB);
        if(isHost) {
            add(done);
        }
        setVisible(true);
    }

    public void reset(JButton button) {
        available.clear();
        for(int i = 0; i <5; i++) {
            available.add(false);
        }
        gandalfB.setText("Available");
        indianWomanB.setText("Available");
        frogB.setText("Available");
        frogB.setForeground(Color.white);
        frogB.setBackground(Color.black);
        whiteB.setText("Available");
        catB.setText("Available");
        catB.setBackground(Color.BLACK);
        catB.setForeground(Color.white);
        gandalfB.setForeground(null);
        gandalfB.setBackground(Color.BLACK);
        indianWomanB.setForeground(Color.WHITE);
        indianWomanB.setBackground(Color.BLACK);
        whiteB.setForeground(Color.white);
        whiteB.setBackground(Color.BLACK);


    }
    public void switchToGamePanel() {
        GamePanel gamePanel = new GamePanel(jFrame1, clientMain, null, null, connectPanel, cardSelectPanel, this, false, null, null);
        gamePanel.setPreferredSize(new Dimension(1920,1040));
        if(!isHost){
            clientMain.setGamePanel(gamePanel);
        }
        JScrollPane scrollPane1 = new JScrollPane(gamePanel);
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane1);
        jFrame1.setContentPane(scrollPane1);

        jFrame1.pack();
        jFrame1.revalidate();
        jFrame1.repaint();
        jFrame1.setVisible(true);
    }
    public void notifyCharacterSelection(String characterName){
        try{
            if(isHost){
                String playerName1 = hostPanel.nameTextField.getText();
                serverMain.broadcastMessage(6, playerName1 + "-" + characterName);

            }
            else{
                ObjectOutputStream out = clientMain.getOut();
                String playerName1 = connectPanel.nameTextField.getText();
                System.out.println("NotifyingCharacterSelection" + characterName);
                CommandFromClient.notify_CHARACTER_SELECTION(out, playerName1, characterName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void notifyCharacterUNSelection(String characterName) {
        try{
            if(isHost){
                String playerName1 = hostPanel.nameTextField.getText();
                serverMain.broadcastMessage(7, playerName1 + "-" + characterName);

            }
            else{
                ObjectOutputStream out = clientMain.getOut();
                String playerName1 = connectPanel.nameTextField.getText();
                System.out.println("NotifyingCharacterUNSelection" + characterName);
                CommandFromClient.notify_UNCHARACTER_SELECTION(out, playerName1, characterName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateAvailability(Object[][] FINAL_ARRAYS){
        System.out.println("Received Message");
        for(int i=0;i<5;i++){
            if(FINAL_ARRAYS[i][1].equals("NotMine")){
                if(i==0){
                    catB.setText("Unavailable");
                    catB.setEnabled(false);
                }
                else if(i==1){
                    indianWomanB.setText("Unavailable");
                    indianWomanB.setEnabled(false);
                }
                else if(i==2){
                    whiteB.setText("Unavailable");
                    whiteB.setEnabled(false);
                }
                else if(i==3){
                    frogB.setText("Unavailable");
                    frogB.setEnabled(false);
                }
                else if(i==4){
                    gandalfB.setText("Unavailable");
                    gandalfB.setEnabled(false);

                }
            }
            if(FINAL_ARRAYS[i][1].equals("Available")){
                if(i==0){
                    catB.setEnabled(true);
                    catB.setText("Available");
                    catB.setForeground(Color.white);
                    catB.setBackground(Color.BLACK);
                }
                else if(i==1){
                    indianWomanB.setEnabled(true);
                    indianWomanB.setText("Available");
                    indianWomanB.setForeground(Color.white);
                    indianWomanB.setBackground(Color.BLACK);
                }
                else if(i==2){
                    whiteB.setEnabled(true);
                    whiteB.setText("Available");
                    whiteB.setForeground(Color.white);
                    whiteB.setBackground(Color.BLACK);
                }
                else if(i==3){
                    frogB.setEnabled(true);
                    frogB.setText("Available");
                    frogB.setForeground(Color.white);
                    frogB.setBackground(Color.BLACK);
                }
                else if(i==4){
                    gandalfB.setEnabled(true);
                    gandalfB.setText("Available");
                    gandalfB.setForeground(Color.white);
                    gandalfB.setBackground(Color.BLACK);
                }
            }
        }
        for(int i=0; i<5;i++){
            for (int j=0;j<2;j++){
                System.out.print(i+1 + ") " + FINAL_ARRAY[i][j] + "     ||     ");
            }
        }
        if(isHost){
            int NumTemp = hostPanel.selectedNumberOfPlayers[0];
            int temp = 0;
            for(int i=0;i<5;i++){
                if((FINAL_ARRAYS[i][1] == "Mine") || (FINAL_ARRAYS[i][1] == "NotMine")){
                    temp++;
                }
            }
            if(temp == NumTemp){
                done.setEnabled(true);
            }
            else{
                done.setEnabled(false);
            }
        }

    }

}