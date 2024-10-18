package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collections;


public class GamePanel extends JPanel {


    private JLabel WelcomeLabel = new JLabel("IT'S GAME TIME!");
    private JFrame jFrame;
    private ClientMain clientMain;
    private ServerMain serverMain;
    private HostPanel hostPanel;
    private ConnectPanel connectPanel;
    private CardSelectPanel cardSelectPanel;
    private CharacterSelectPanel characterSelectPanel;
    private Boolean isHost1;
    private String stringCardPanel;
    private String trimmed_stringCardPanel;
    private String[] array_trimmed_stringCardPanel;
    private Boolean booleanValue;
    private ArrayList<Boolean> cardSelectedList_g_client = new ArrayList<Boolean>();
    private ChatPanel chatPanel1;
    private InGameRulesPanel inGameRulesPanel1;
    private JButton chatButton;
    public ArrayList<BufferedImage> buildingNames = new ArrayList<>();
    public ArrayList<BufferedImage> buildingSelected = new ArrayList<>();
    private ArrayList<JButton> imageButtons = new ArrayList<>();
    private JButton rules = new JButton("Rules");
    public ArrayList<BuildingCards> buildingDeck = new ArrayList<>();
    public ArrayList<BuildingCards> buildingDeck1 = new ArrayList<>();
    public ArrayList<BuildingCards> playerBuildings = new ArrayList<>();
    public ArrayList<TreasureCard> unshuffledDeck = new ArrayList<TreasureCard>();
    public ArrayList<TreasureCard> shuffledDeck = new ArrayList<>();
    public ArrayList<TreasureCard> safeTreasuresList = new ArrayList();
    public ArrayList<TreasureCard> playerTreasures = new ArrayList<>();
    private JButton takeOff = new JButton("Drop Off Level");
    public ArrayList<LevelCard> deckLevelCard = new ArrayList<>();
    public ArrayList<LevelCard>  drawnLevelCard = new ArrayList<>();
    public int imageLevel = 0;
    private JButton view = new JButton("View");
    private JLabel playerLabel;
    public int current_player;
    private JPanel cardsPanel = new JPanel();
    private JPanel treasurePanel = new JPanel();
    private JPanel safeTreasurePanel = new JPanel();
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane;
    private static JLabel ErrorArea;
    private static JLabel ErrorArea1;
    private String playerImage;
    public ArrayList<String> tempChar = new ArrayList<String>();
    private JLabel unsafeTreasures = new JLabel ("Unsafe:");
    private JLabel safeTreasures = new JLabel ("Safe:");
    private JButton temp1 = new JButton("Coin Increase");
    private JButton temp2 = new JButton("Coin Decrease");
    private JButton temp3 = new JButton("Heart Increase");
    private JButton temp4 = new JButton("Heart Decrease");
    private JButton temp5 = new JButton("Jump Increase");
    private JButton temp6 = new JButton("Jump Decrease");
    public JLabel phase;
    public JLabel heartCount = new JLabel("4");
    public JLabel coinCount = new JLabel("0");
    public JLabel jumpCount = new JLabel("0");
    private JPanel personalWallet = new JPanel();
    public boolean hasJumpedLevel = false;
    public JButton buyBuildings = new JButton("Buy Buildings");
    public JButton buyHearts = new JButton("Buy Hearts");
    public ArrayList<TreasureCard> buyingCards = new ArrayList<>();

    JPanel LeaderBoard;
    //missing one\
    //skip 25 its a repeat
    private BufferedImage personalCard, heartCard, starCardBackground,
            sandwichStand, cafe, arcade, bazaarOfOddities, hotel, templeOfZoz,
            buildingCardBackground, reptileStable, herbHut, ostrichRanch, gym, hospital, laboratory, fishingPond, bowlingAlley, smithy, fishVendor, tollBooth,
            soapMakers, hallOfElders, lodge, rootMarket, endlessMine, arena,
            backOfLevelCard, levelCard31, levelCard32, levelCard33, levelCard34, levelCard35, levelCard36, levelCard37, levelCard38, levelCard39, levelCard40,
            treasureCardBackground, gear, cube, egg, carrot, mineral, fish,
            coin1, coin5, coin10, firstPlayerToken, heart, jump, indianWoman, gandalf, cat, frog, white, playerLevelCard, loading;
    int coinTemp;



    public GamePanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost, ChatPanel chatPanel, InGameRulesPanel inGameRulesPanel) {
        this.chatPanel1 = chatPanel;
        this.inGameRulesPanel1 = inGameRulesPanel;
        this.isHost1 = isHost;
        //this.cardSelectPanel = cardSelectPanel;

        //---------------------------------------------------------------------------------------------------------------------------------------------------------------
        /* TEST CASE BUTTONS*/
        buyBuildings.setBounds(1700,500,250,30);
        buyBuildings.addActionListener(e -> {
            buyBuildings.setText("Stop Buying");

        });
        buyHearts.setBounds(1700, 600, 250, 30);
        buyHearts.addActionListener( e-> {
            buyHearts.setText("Stop Buying");
        });
        add(buyBuildings);
        add(buyHearts);

//        heartCount.setBounds(360, 500, 70, 40);
        heartCount.setFont(new Font("Georgia", Font.BOLD, 20));
        heartCount.setForeground(Color.white);
//        add(heartCount);
//        coinCount.setBounds(440, 500, 70, 40);
        coinCount.setFont(new Font("Georgia", Font.BOLD, 20));
        coinCount.setForeground(Color.white);

//        add(coinCount);
//        jumpCount.setBounds(520, 500, 70, 40);
        jumpCount.setFont(new Font("Georgia", Font.BOLD, 20));
        jumpCount.setForeground(Color.white);

//        add(jumpCount);




        temp1.setBounds(1700,700,250,30);
        add(temp1);
        temp1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerCoins(hostPanel.playerHost.getPlayerCoins() +1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    LeaderBoardUpdateHost();
                }else{
                    //coinTemp = characterSelectPanel.playerClient.getPlayerCoins() + 1;
                    characterSelectPanel.playerClient.setPlayerCoins(characterSelectPanel.playerClient.getPlayerCoins()+1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                }
            }
        });

        temp2.setBounds(1700,730,250,30);
        add(temp2);
        temp2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerCoins(hostPanel.playerHost.getPlayerCoins() - 1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    LeaderBoardUpdateHost();
                }else{
                    characterSelectPanel.playerClient.setPlayerCoins(characterSelectPanel.playerClient.getPlayerCoins()-1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                }
            }
        });

        temp3.setBounds(1700,760,250,30);
        add(temp3);
        temp3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerHearts(hostPanel.playerHost.getPlayerHearts() +1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    LeaderBoardUpdateHost();
                    repaint();
                    revalidate();
                }else{
                    characterSelectPanel.playerClient.setPlayerHearts(characterSelectPanel.playerClient.getPlayerHearts()+1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                    repaint();
                    revalidate();
                }
            }
        });

        temp4.setBounds(1700,790,250,30);
        add(temp4);
        temp4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerHearts(hostPanel.playerHost.getPlayerHearts() -1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    LeaderBoardUpdateHost();
                    repaint();
                    revalidate();
                }else{
                    characterSelectPanel.playerClient.setPlayerHearts(characterSelectPanel.playerClient.getPlayerHearts()-1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                    repaint();
                    revalidate();
                }
            }
        });

        temp5.setBounds(1700,820,250,30);
        add(temp5);
        temp5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerJumps(hostPanel.playerHost.getPlayerJumps() +1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    LeaderBoardUpdateHost();
                }else{
                    characterSelectPanel.playerClient.setPlayerJumps(characterSelectPanel.playerClient.getPlayerJumps()+1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                }
            }
        });

        temp6.setBounds(1700,850,250,30);
        add(temp6);
        temp6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isHost){
                    hostPanel.playerHost.setPlayerJumps(hostPanel.playerHost.getPlayerJumps() -1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }else{
                    characterSelectPanel.playerClient.setPlayerJumps(characterSelectPanel.playerClient.getPlayerJumps()-1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                }
            }
        });

        /* TEST CASE BUTTONS*/
//---------------------------------------------------------------------------------------------------------------------------------------------------------------

        LeaderBoard = new JPanel();
        LeaderBoard.setBounds(30,475,230,300);
        add(LeaderBoard);
        JLabel leaderBoardName = new JLabel("LeaderBoard", JLabel.CENTER);
        leaderBoardName.setFont(new Font("Georgia", Font.BOLD, 20));
        leaderBoardName.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
        leaderBoardName.setOpaque(true);
        leaderBoardName.setBackground(Color.GRAY);
        leaderBoardName.setForeground(Color.WHITE);
        LeaderBoard.setLayout(new BorderLayout());
        LeaderBoard.setBackground(Color.BLACK);
        LeaderBoard.setBorder(BorderFactory.createLineBorder(Color.WHITE,2,true));
        LeaderBoard.add(leaderBoardName, BorderLayout.NORTH);
        /*if(isHost){
            LeaderBoardUpdateHost();
        } else{
            LeaderBoardUpdateClient();
        }*/


        ErrorArea = new JLabel("",JLabel.CENTER);
        ErrorArea.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
        ErrorArea.setOpaque(true);
        ErrorArea.setBackground(Color.BLACK);
        ErrorArea.setForeground(Color.RED);
        ErrorArea.setFont(new Font("Georgia", Font.BOLD, 20));
        ErrorArea.setBounds(500, 485, 230, 40);
        //ErrorArea.setEditable(false);
        add(ErrorArea);
        ErrorArea.setVisible(false);


//        ErrorArea1 = new JLabel("",JLabel.CENTER);
//        ErrorArea1.setBorder(BorderFactory.createLineBorder(Color.red,2,true));
//        ErrorArea1.setBackground(Color.PINK);
//        ErrorArea1.setForeground(Color.red);
//        ErrorArea1.setFont(new Font("Georgia", Font.BOLD, 20));
//        ErrorArea1.setBounds(1170, 485, 230, 40);
//        add(ErrorArea1);
//        ErrorArea1.setVisible(false);
        phase = new JLabel("Run Phase",JLabel.CENTER);
        phase.setBorder(BorderFactory.createLineBorder(Color.green,2,true));
        phase.setOpaque(true);

        phase.setFont(new Font("Georgia", Font.BOLD, 20));
        phase.setBounds(1170, 485, 230, 40);
        phase.setBackground(Color.black);
        phase.setForeground(Color.green);
        phase.setFont(new Font("Georgia", Font.BOLD, 20));
        add(phase);
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        cardsPanel.setBackground(Color.black);
        treasurePanel = new JPanel();
        treasurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        treasurePanel.setBackground(Color.BLACK);

        unsafeTreasures.setBounds(280, 785, 200, 30);
        unsafeTreasures.setForeground(Color.WHITE);
        unsafeTreasures.setOpaque(true);
        unsafeTreasures.setBackground(Color.black);
        unsafeTreasures.setBorder(new LineBorder(Color.white, 1));
        unsafeTreasures.setFont(new Font ("Georgia", Font.BOLD, 15));
        unsafeTreasures.setHorizontalAlignment(SwingConstants.CENTER);
        unsafeTreasures.setVerticalAlignment(SwingConstants.CENTER);
        add(unsafeTreasures);

        scrollPane1 = new JScrollPane(treasurePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(280, 830, 660, 140);

        scrollPane1.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });

        add(scrollPane1);

        safeTreasures.setBounds(1440, 785, 200, 30);
        safeTreasures.setForeground(Color.WHITE);
        safeTreasures.setOpaque(true);
        safeTreasures.setBackground(Color.black);
        safeTreasures.setBorder(new LineBorder(Color.white, 1));
        safeTreasures.setFont(new Font ("Georgia", Font.BOLD, 15));
        safeTreasures.setHorizontalAlignment(SwingConstants.CENTER);
        safeTreasures.setVerticalAlignment(SwingConstants.CENTER);
        add(safeTreasures);

        safeTreasurePanel = new JPanel();
        safeTreasurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        safeTreasurePanel.setOpaque(true);
        safeTreasurePanel.setBackground(Color.black);
        scrollPane2 = new JScrollPane(safeTreasurePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setBounds(980, 830, 660, 140);
        scrollPane2.setOpaque(true);
        scrollPane2.setBackground(Color.black);
        scrollPane2.setForeground(Color.WHITE);
        scrollPane2.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });
        add(scrollPane2);


        scrollPane = new JScrollPane(cardsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(280, 540, 1360, 230);

        scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.BLACK;
            }
        });
        add(scrollPane);
        scrollPane.getHorizontalScrollBar().setBackground(Color.GRAY);
        scrollPane1.getHorizontalScrollBar().setBackground(Color.GRAY);
        scrollPane2.getHorizontalScrollBar().setBackground(Color.GRAY);


        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));

        if(isHost){
            tempChar = serverMain.charactersInLevel_host;
        }
        else{
            tempChar = clientMain.charactersInLevel_client;
        }

        try {
            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));
        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }

        if(isHost){
            playerImage = hostPanel.playerHost.getPlayerImage();
            if(playerImage != null){
                repaint();
                revalidate();
            }
        }
        else{
            playerImage = characterSelectPanel.playerClient.getPlayerImage();
            if(playerImage != null){
                repaint();
                revalidate();
            }
        }

        if(isHost){
            current_player = 0;
        }
        else{
            current_player = clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText());
        }
        setSize(1920, 1010);
        setLayout(null);
        JButton leftArrow = new JButton("<");
        leftArrow.setBounds(700, 490, 50, 40);
        leftArrow.setForeground(Color.white);
        leftArrow.setBackground(Color.black);
        leftArrow.setFocusable(false);
        leftArrow.setFont(new Font("Georgia", Font.BOLD, 25));
        leftArrow.setBorder(null);
        leftArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_player--;
                if (current_player < 0) {
                    if(isHost) {
                        current_player = serverMain.playerArrayList_Host.size()-1;
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                    else{
                        current_player = clientMain.playerArrayList_client.size()-1;
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                }
                else{
                    String tempName = playerGameView(current_player);
                    if(tempName.equals("My")){
                        playerLabel.setText("My View");
                    }
                    else {
                        playerLabel.setText("Player " + tempName + "'s View");
                    }
                }
            }
        });
//        JButton rightArrow = new JButton("→");
        JButton rightArrow = new JButton(">");
        rightArrow.setBounds(1150, 490, 50, 40);
        rightArrow.setForeground(Color.white);
        rightArrow.setBackground(Color.black);
        rightArrow.setFocusable(false);
        rightArrow.setFont(new Font("Georgia", Font.BOLD, 25));
        rightArrow.setBorder(null);
        rightArrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current_player++;
                if(isHost) {
                    if (current_player > serverMain.playerArrayList_Host.size()-1) {
                        current_player = 0;
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                    else{
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                }
                else{
                    if (current_player > clientMain.playerArrayList_client.size()-1) {
                        current_player = 0;
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                    else{
                        String tempName = playerGameView(current_player);
                        if(tempName.equals("My")){
                            playerLabel.setText("My View");
                        }
                        else {
                            playerLabel.setText("Player " + tempName + "'s View");
                        }
                    }
                }
            }
        });

        takeOff.setFocusable(false);
        takeOff.setBackground(Color.black);
        takeOff.setForeground(Color.WHITE);
        takeOff.setFont(new Font("Georgia", Font. BOLD, 15));
        takeOff.setBorder(BorderFactory.createEmptyBorder());
        takeOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                takeOff.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                takeOff.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        takeOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isHost) {
                    if (hostPanel.playerHost.isPlayerActive) {
                        hostPanel.playerHost.setPlayerActive(false);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                        takeOff.setText("Dropped Level");
                        takeOff.setEnabled(false);
                        tempChar.remove(hostPanel.playerHost.getPlayerImage());
                        repaint();
                        revalidate();
                        serverMain.broadcastMessage(16, hostPanel.nameTextField.getText() + "-" + hostPanel.playerHost.getPlayerImage());
                    }
                    else{
                        showError("Player Not Active");
                    }
                } else {
                    if (characterSelectPanel.playerClient.isPlayerActive) {
                        characterSelectPanel.playerClient.setPlayerActive(false);
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        takeOff.setText("Dropped Level");
                        takeOff.setEnabled(false);
                        tempChar.remove(characterSelectPanel.playerClient.getPlayerImage());
                        repaint();
                        revalidate();
                        CommandFromClient.notify_LEVELDISCONNECTION(clientMain.getOut(), connectPanel.nameTextField.getText() + "-" + characterSelectPanel.playerClient.getPlayerImage());
                    }
                    else{
                        showError("Player Not Active");
                    }
                }
            }
        });
        playerLabel = new JLabel("My View", JLabel.CENTER);
        playerLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        playerLabel.setForeground(Color.white);
        playerLabel.setBounds(860, 490, 200, 50);
        JPanel backgroundP = new JPanel();
        backgroundP.setBounds(750,485, 400,40);
        backgroundP.setBorder(BorderFactory.createLineBorder(Color.white));
        backgroundP.setBackground(Color.black);
        add(backgroundP);
        backgroundP.add(leftArrow);
        backgroundP.add(playerLabel);
        backgroundP.add(rightArrow);


        int i=100;


        chatPanel1 = new ChatPanel(frame, chatButton, clientMain, serverMain, hostPanel, connectPanel, cardSelectPanel, characterSelectPanel, isHost, this);
        chatPanel1.setBounds(20,760,700,600);
        chatPanel1.setVisible(false);
        add(chatPanel1);
        toggleChatPanel();
        buildingCardsInstantiate();


        chatPanel1.setForeground(Color.YELLOW);


//        rules.setBounds(1830, 20, 70, 30);
//        rules.addActionListener(e -> {
//            toggleInGameRulesPanel();
//        });
//        add(rules);



        inGameRulesPanel1 = new InGameRulesPanel(frame, this);
        inGameRulesPanel1.setBounds(0, 0, 1920, 1010);
        inGameRulesPanel1.setVisible(false);
        add(inGameRulesPanel1);


        if(isHost){
            serverMain.setGamePanel(this);
            serverMain.setChatPanel(this.chatPanel1);
        }
        else{
            clientMain.setGamePanel(this);
            clientMain.setChatPanel(this.chatPanel1);
        }




        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\GamePanel1.jpg")));
            personalCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0002.jpg")));
            heartCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0003.jpg")));
            starCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0004.jpg")));
            sandwichStand = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0005.jpg")));
            cafe = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0006.jpg")));
            arcade = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0007.jpg")));
            bazaarOfOddities = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0008.jpg")));
            hotel = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0009.jpg")));
            templeOfZoz = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0010.jpg")));
            buildingCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0011.jpg")));
            reptileStable = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0012.jpg")));
            herbHut = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0013.jpg")));
            ostrichRanch = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0014.jpg")));
            gym = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0015.jpg")));
            hospital = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0016.jpg")));
            laboratory = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0017.jpg")));
            fishingPond = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0018.jpg")));
            bowlingAlley = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0019.jpg")));
            smithy = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0020.jpg")));
            fishVendor = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0021.jpg")));
            tollBooth = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0022.jpg")));
            soapMakers = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0023.jpg")));
            hallOfElders = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0024.jpg")));
            //skip 25
            lodge = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0026.jpg")));
            rootMarket = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0027.jpg")));
            endlessMine = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            arena = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0029.jpg")));
            backOfLevelCard = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0030.jpg")));
            levelCard31 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0031.jpg")));
            levelCard32 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0032.jpg")));
            levelCard33 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0033.jpg")));
            levelCard34 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0034.jpg")));
            levelCard35 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0035.jpg")));
            levelCard36 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0036.jpg")));
            levelCard37 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0037.jpg")));
            levelCard38 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0038.jpg")));
            levelCard39 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0039.jpg")));
            levelCard40 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0040.jpg")));


            treasureCardBackground = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0041.jpg")));
            gear = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0042.jpg")));
            cube = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0043.jpg")));
            egg = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0044.jpg")));
            carrot = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0045.jpg")));
            mineral = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0046.jpg")));
            fish = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0047.jpg")));


            coin1 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_1_Coin.png")));
            coin5 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_5_Coin.png")));
            coin10 = ImageIO.read((new File("Project\\src\\Images\\MegaLand_10_Coin.png")));
            firstPlayerToken = ImageIO.read((new File("Project\\src\\Images\\MegaLand_1stPlayerToken.png")));
            heart = ImageIO.read((new File("Project\\src\\Images\\MegaLand_HeartToken.png")));
            jump = ImageIO.read((new File("Project\\src\\Images\\MegaLand_JumpToken.png")));


            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));


            playerLevelCard = ImageIO.read((new File("Project\\src\\Images\\MegaLand_PlayerCard.png")));


        } catch (Exception e) {
            e.printStackTrace();
        }

        personalWallet = new JPanel();
//        personalWallet.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        personalWallet.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        personalWallet.setBackground(Color.black);
        personalWallet.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
        personalWallet.setBounds(1420, 485, 230, 40);
        JButton heart1 = new JButton(new ImageIcon(heart.getScaledInstance(30, 30, Image.SCALE_FAST)));
        heart1.setOpaque(true);
        heart1.setBackground(Color.black);
        heart1.setFocusable(false);
        heart1.setBorder(BorderFactory.createEmptyBorder());
        personalWallet.add(heart1);
        personalWallet.add(heartCount);
        JButton coinOne = new JButton(new ImageIcon(coin1.getScaledInstance(30, 30, Image.SCALE_FAST)));
        coinOne.setOpaque(true);
        coinOne.setBackground(Color.black);
        coinOne.setFocusable(false);
        coinOne.setBorder(BorderFactory.createEmptyBorder());
        personalWallet.add(coinOne);
        personalWallet.add(coinCount);
        JButton jump1 = new JButton(new ImageIcon(jump.getScaledInstance(30, 30, Image.SCALE_FAST)));
        jump1.setOpaque(true);
        jump1.setBackground(Color.black);
        jump1.setFocusable(false);
        jump1.setBorder(BorderFactory.createEmptyBorder());
        jump1.addActionListener(e-> {
            if(!jumpCount.equals(0)) {
                hasJumpedLevel = true;
                if(isHost){
                    hostPanel.playerHost.setPlayerJumps(hostPanel.playerHost.getPlayerJumps() -1);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                }else{
                    characterSelectPanel.playerClient.setPlayerJumps(characterSelectPanel.playerClient.getPlayerJumps()-1);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                }
            }
        });
        personalWallet.add(jump1);
        personalWallet.add(jumpCount);
        add(personalWallet);



        buildingNames.add(reptileStable);
        buildingNames.add(herbHut);
        buildingNames.add(ostrichRanch);
        buildingNames.add(gym);
        buildingNames.add(hospital);
        buildingNames.add(laboratory);
        buildingNames.add(fishingPond);
        buildingNames.add(bowlingAlley);
        buildingNames.add(smithy);
        buildingNames.add(fishVendor);
        buildingNames.add(tollBooth);
        buildingNames.add(soapMakers);
        buildingNames.add(hallOfElders);
        buildingNames.add(lodge);
        buildingNames.add(rootMarket);
        buildingNames.add(endlessMine);
        buildingNames.add(arena);
        createDeck();

        this.jFrame = frame;
        this.clientMain = clientMain;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;
        this.connectPanel = connectPanel;
        this.cardSelectPanel = cardSelectPanel;
        this.characterSelectPanel = characterSelectPanel;
        this.isHost1 = isHost;
        createImageButtons();


        System.out.println(isHost1);
        if(isHost1) {
            System.out.println("Connected Players (H): " + serverMain.gamePlayerNames);
            System.out.println("CardSelectedList (H): " + cardSelectPanel.buildingsSelect);
            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
            createLevelDeck_Host();
            System.out.println("CharactersInGame:" + serverMain.charactersInLevel_host);
            LeaderBoardUpdateHost();
        }else {
            System.out.println("Connected Players (C): " + clientMain.Final_gamePlayerNames_ClientSide);
            stringCardPanel = clientMain.cardPanel_Client_Side;
            trimmed_stringCardPanel = stringCardPanel.substring(1, stringCardPanel.length()-1);
            array_trimmed_stringCardPanel = trimmed_stringCardPanel.split(",");
            for(String element: array_trimmed_stringCardPanel){
                booleanValue = Boolean.parseBoolean(element.trim());
                cardSelectedList_g_client.add(booleanValue);
            }
            createImageButtonsClient();
            createLevelDeck_Client();
            LeaderBoardUpdateClient();
            System.out.println("CardSelectedList (C): " + cardSelectedList_g_client);
            /*if(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())==1) {
                characterSelectPanel.playerClient.setPlayerID(1000);
            }*/
            System.out.println(characterSelectPanel.playerClient.getPlayerID());
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
            System.out.println("CharactersInGame:" + clientMain.charactersInLevel_client);
        }
    }

//    public void buyBuildingsAction(BuildingCards buying) {
//        int costMatch = buyingCards.size();
//        int cost = buying.getBuildingCost();
//        int carrots = 0;//4
//        int gear = 0;//1
//        int cube = 0;//2
//        int mineral = 0;//5
//        int fish = 0;//6
//        int egg = 0;//3
//
//        for(int i = 0; i < playerTreasures.size(); i++) {
//            if(playerTreasures.get(i).getTreasureName().equals("gear")) {
//                gear++;
//            } else if(playerTreasures.get(i).getTreasureName().equals("cube")) {
//                cube++;
//            }else if(playerTreasures.get(i).getTreasureName().equals("egg")) {
//                egg++;
//            }else if(playerTreasures.get(i).getTreasureName().equals("carrots")) {
//                carrots++;
//            }else if(playerTreasures.get(i).getTreasureName().equals("mineral")) {
//                mineral++;
//            }else if(playerTreasures.get(i).getTreasureName().equals("fish")) {
//                fish++;
//            }
//        }
//
//        if(cost == costMatch) {
//            playerBuildings.add(buying);
//            hostOwnedCardsDisplay(playerBuildings);
//        }
//
//
//    }

    public void updateTempChar(String tempCharChar){
        tempChar.remove(tempCharChar);
        if(tempChar.size() == 0){
            phase.setText("Buy Phase");

            JButton heartBuy = new JButton(new ImageIcon(heartCard.getScaledInstance(310, 190, Image.SCALE_FAST)));
            heartBuy.setBounds(30, 20, 310, 190);
            heartBuy.setOpaque(true);
            heartBuy.setBackground(Color.black);
            heartBuy.setFocusable(false);
            heartBuy.setBorder(BorderFactory.createEmptyBorder());
            add(heartBuy);
        }
        System.out.println(tempChar);
        repaint();
        revalidate();
    }

    public void createLevelDeck_Host() {
        deckLevelCard.add(new LevelCard("levelCard31",1, 0, false, false, levelCard31, false));
        deckLevelCard.add(new LevelCard("levelCard32",2, 1, false, false, levelCard32, false));
        deckLevelCard.add(new LevelCard("levelCard33",3, 1, false, true, levelCard33, true));
        deckLevelCard.add(new LevelCard("levelCard34",4, 1, false, true, levelCard34, false));
        deckLevelCard.add(new LevelCard("levelCard35",5, 2, false, false, levelCard35, false));
        deckLevelCard.add(new LevelCard("levelCard36",6, 0, true, false, levelCard36, false));
        deckLevelCard.add(new LevelCard("levelCard37",7, 3, false, true, levelCard37, false));
        deckLevelCard.add(new LevelCard("levelCard38",8, 2, false, true, levelCard38, false));
        deckLevelCard.add(new LevelCard("levelCard39",9, 0, false, false, levelCard39, false));
        deckLevelCard.add(new LevelCard("levelCard40",10, 0, false, false, levelCard40, false));
        //Collections.shuffle(deckLevelCard);
        if(isHost1) {
            hostPanel.playerHost.setPlayerLevelCards(deckLevelCard);
            serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);

            BufferedImage backOfLevelCard1 = backOfLevelCard;
            JButton levelDraw = new JButton(new ImageIcon(backOfLevelCard1.getScaledInstance(140, 210, Image.SCALE_FAST)));
            levelDraw.setBounds(1450, 30, 140, 210);
            add(levelDraw);
            levelDraw.addActionListener(e -> {
                if((current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText())) && (hostPanel.playerHost.isPlayerActive) && (hostPanel.playerHost.isCanDrawLevel())) {
                    GUILevelCardsHost();
                    serverMain.broadcastMessage(13, hostPanel.nameTextField.getText());
                    //logic
                } else if (!(current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText()))) {
                    showError("Not Your View");
                } else if((!hostPanel.playerHost.isPlayerActive)){
                    showError("Not in Level");
                }else if (!(hostPanel.playerHost.isCanDrawLevel())) {
                    showError("Cannot Draw Cards");
                }
            });
        }
    }
    public void createLevelDeck_Client() {
        deckLevelCard.add(new LevelCard("levelCard31",1, 0, false, false, levelCard31, false));
        deckLevelCard.add(new LevelCard("levelCard32",2, 1, false, false, levelCard32, false));
        deckLevelCard.add(new LevelCard("levelCard33",3, 1, false, true, levelCard33, true));
        deckLevelCard.add(new LevelCard("levelCard34",4, 1, false, true, levelCard34, false));
        deckLevelCard.add(new LevelCard("levelCard35",5, 2, false, false, levelCard35, false));
        deckLevelCard.add(new LevelCard("levelCard36",6, 0, true, false, levelCard36, false));
        deckLevelCard.add(new LevelCard("levelCard37",7, 3, false, true, levelCard37, false));
        deckLevelCard.add(new LevelCard("levelCard38",8, 2, false, true, levelCard38, false));
        deckLevelCard.add(new LevelCard("levelCard39",9, 0, false, false, levelCard39, false));
        deckLevelCard.add(new LevelCard("levelCard40",10, 0, false, false, levelCard40, false));
        //Collections.shuffle(deckLevelCard);
        characterSelectPanel.playerClient.setPlayerLevelCards(deckLevelCard);
        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);

        BufferedImage backOfLevelCard1 = backOfLevelCard;
        JButton levelDraw = new JButton(new ImageIcon(backOfLevelCard1.getScaledInstance(140, 210, Image.SCALE_FAST)));
        levelDraw.setBounds(1450, 30, 140, 210);
        add(levelDraw);
        levelDraw.addActionListener(e -> {
            if((current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())) && (characterSelectPanel.playerClient.isCanDrawLevel()) && (characterSelectPanel.playerClient.isPlayerActive)){
                GUILevelCardsClient();
                CommandFromClient.notify_LEVEL_CARD_NAME(clientMain.getOut(),connectPanel.nameTextField.getText());
            }   else if(!(current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()))){
                showError("Not Your View");
            } else if(!(characterSelectPanel.playerClient.isPlayerActive)){
                showError("Not in Level");
            }
            else if(!(characterSelectPanel.playerClient.isCanDrawLevel())){
                showError("Cannot Draw Cards");
            }
        });
    }


    public void createDeck() {
        for(int i = 0; i < 6; i++) {
            unshuffledDeck.add(new TreasureCard(1, "gear", false, gear, "Project\\src\\Images\\2024-08-19-10-14-0042.jpg", false));//6
        }
        for(int i = 0; i < 20; i++) {
            unshuffledDeck.add(new TreasureCard(2, "cube", false, cube, "Project\\src\\Images\\2024-08-19-10-14-0043.jpg", false));//20
        }
        for(int i = 0; i < 16; i++) {
            unshuffledDeck.add(new TreasureCard(3, "egg", false, egg, "Project\\src\\Images\\2024-08-19-10-14-0044.jpg", false));//16
        }
        for(int i = 0; i < 30; i++) {
            unshuffledDeck.add(new TreasureCard(4, "carrot", false, carrot, "Project\\src\\Images\\2024-08-19-10-14-0045.jpg", false));//30
        }
        for(int i = 0; i < 10; i++) {
            unshuffledDeck.add(new TreasureCard(5, "mineral", false, mineral, "Project\\src\\Images\\2024-08-19-10-14-0046.jpg", false));//10
        }
        for(int i = 0; i < 14; i++) {
            unshuffledDeck.add(new TreasureCard(6, "fish", false,fish, "Project\\src\\Images\\2024-08-19-10-14-0047.jpg", false));//14
        }
        Collections.shuffle(unshuffledDeck);
        shuffledDeck.addAll(unshuffledDeck);

    }
//    public void levelDisplay() {
//        for(int i = 0; i < drawnLevelCard.size(); i++) {
//            BufferedImage image1 = drawnLevelCard.get(0).getImage();
//            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(140, 210, Image.SCALE_FAST)));
//            button1.setFocusPainted(false);
//            button1.setBorderPainted(false);
//
//            button1.setBounds(20+(80*i), 260, 140, 210);
//
//            add(button1);
//        }
//        revalidate();
//        repaint();
//    }
//    public void levelDisplay() {
//        for(int i = drawnLevelCard.size() - 1; i >= 0; i--) {
//            BufferedImage image1 = drawnLevelCard.get(i).getImage();
//            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(140, 210, Image.SCALE_FAST)));
//            button1.setFocusPainted(false);
//            button1.setBorderPainted(false);
//
//            button1.setBounds(20+(80*i), 260, 140, 210);
//
//            add(button1);
//        }
//        revalidate();
//        repaint();
//    }






    public void buildingCardsInstantiate() {
        buildingDeck.add(new BuildingCards(1, "Sandwich Stand", 1, true, false, sandwichStand, 4));
        buildingDeck.add(new BuildingCards(2, "Cafe", 2, true, false, cafe, 4));
        buildingDeck.add(new BuildingCards(3, "Arcade", 3, true, false, arcade, 4));
        buildingDeck.add(new BuildingCards(4, "Bazaar of Oddities", 4, true, false, bazaarOfOddities, 4));
        buildingDeck.add(new BuildingCards(5, "Hotel", 5, true, false, hotel, 4));
        buildingDeck.add(new BuildingCards(6, "Temple of Zoz", 6, true, false, templeOfZoz, 4));
        buildingDeck.add(new BuildingCards(7, "Reptile Stable", 1, true, false, reptileStable, 4));
        buildingDeck.add(new BuildingCards(8, "Herb Hut", 1, false, false, herbHut, 4));
        buildingDeck.add(new BuildingCards(9, "Ostrich Ranch", 2, false, false, ostrichRanch, 4));
        buildingDeck.add(new BuildingCards(10, "Gym", 2, false, false, gym, 4));
        buildingDeck.add(new BuildingCards(11, "Hospital", 2, false, false, hospital, 4));
        buildingDeck.add(new BuildingCards(12, "Laboratory", 3, false, false, laboratory, 4));
        buildingDeck.add(new BuildingCards(13, "Fishing Pond", 3, false, true, fishingPond, 4));
        buildingDeck.add(new BuildingCards(14, "Bowling Alley", 3, false, true, bowlingAlley, 4));
        buildingDeck.add(new BuildingCards(15, "Smithy", 3, false, false, smithy, 4));
        buildingDeck.add(new BuildingCards(16, "Fish Vendor", 3, false, false, fishVendor, 4));
        buildingDeck.add(new BuildingCards(17, "Toll Booth", 3, false, false, tollBooth, 4));
        buildingDeck.add(new BuildingCards(18, "Soap Makers", 3, false, false, soapMakers, 4));
        buildingDeck.add(new BuildingCards(19, "Hall of Elders", 3, false, false, hallOfElders, 4));
        buildingDeck.add(new BuildingCards(20, "Lodge", 4, false, true, lodge, 4));
        buildingDeck.add(new BuildingCards(21, "Root Market", 4, false, false, rootMarket, 4));
        buildingDeck.add(new BuildingCards(22, "Endless Mine", 5, false, true, endlessMine, 4));
        buildingDeck.add(new BuildingCards(23, "Arena", 5, false, false, arena, 4));






    }


    private void toggleChatPanel() {
        if(chatPanel1.isVisible()){
            chatPanel1.closeChat();
        }else{
            chatPanel1.openChat();
        }
    }




    private void toggleInGameRulesPanel() {
        if (inGameRulesPanel1.isVisible()) {
            inGameRulesPanel1.closeRules();
            chatPanel1.openChat();
            rules.setVisible(true);
            scrollPane.setVisible(true);
            scrollPane1.setVisible(true);




        } else {
            inGameRulesPanel1.openRules();
            chatPanel1.closeChat();
            scrollPane.setVisible(false);
            scrollPane1.setVisible(false);
            rules.setVisible(false);
        }
    }
    public void openGame(){
        setVisible(true);
        toggleChatPanel();
        rules.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane1.setVisible(true);
    }


    public void createImageButtons() {


        buildingSelected.add(sandwichStand);
        buildingSelected.add(cafe);
        buildingSelected.add(arcade);
        buildingSelected.add(bazaarOfOddities);
        buildingSelected.add(hotel);
        buildingSelected.add(templeOfZoz);
        buildingDeck1.add(new BuildingCards(1, "Sandwich Stand", 1, true, false, sandwichStand, 4));
        buildingDeck1.add(new BuildingCards(2, "Cafe", 2, true, false, cafe, 4));
        buildingDeck1.add(new BuildingCards(3, "Arcade", 3, true, false, arcade, 4));
        buildingDeck1.add(new BuildingCards(4, "Bazaar of Oddities", 4, true, false, bazaarOfOddities, 4));
        buildingDeck1.add(new BuildingCards(5, "Hotel", 5, true, false, hotel, 4));
        buildingDeck1.add(new BuildingCards(6, "Temple of Zoz", 6, true, false, templeOfZoz, 4));
        int x1 = 0;
        if(isHost1) {
            BufferedImage treasure = treasureCardBackground;
            JButton treasureDraw = new JButton(new ImageIcon(treasure.getScaledInstance(90, 120, Image.SCALE_FAST)));
            treasureDraw.setBounds(360, 75, 90, 120);
            add(treasureDraw);
            treasureDraw.addActionListener(e -> {
                if((current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText())) && (hostPanel.playerHost.isPlayerActive)) {
                    playerTreasures.add(shuffledDeck.remove(0));
                    hostPanel.playerHost.setPlayerTreasures(playerTreasures);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    System.out.println(hostPanel.playerHost.getPlayerTreasures());
                    serverMain.playerArrayList_Host.set(0,hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    hostTreasureDisplay(playerTreasures);
                    serverMain.broadcastMessage(14,hostPanel.nameTextField.getText());
                }
                else if(!(current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText()))){
                    showError("Not Your View");
                }
                else if((!hostPanel.playerHost.isPlayerActive)){
                    showError("In Buy Phase");
                }
            });
            for (int i = 0; i < cardSelectPanel.buildingsSelect.size(); i++) {
                if (cardSelectPanel.buildingsSelect.get(i) == true) {
                    buildingSelected.add(buildingNames.get(i));
                    buildingDeck1.add(buildingDeck.get(i+6));
                }
            }


            int x = 480;
            int y = 30;
            int width = 140;
            int height = 210;
            int gap = 20;
            boolean row1 = true;
            int index = 0;
            for(int j = 0; j < buildingSelected.size(); j++) {
                BufferedImage image = buildingSelected.get(j);
                JButton button = new JButton(new ImageIcon(image.getScaledInstance(140, 210, Image.SCALE_FAST)));
                button.setBounds(x, y, width, height);
                button.setContentAreaFilled(false);
                index++;
                int finalJ = j;


                button.addActionListener(e -> {
                    System.out.println("FINAL CHARACTER LIST (H): " + serverMain.charactersInLevel_host);
                    if((current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText())) && !(hostPanel.playerHost.isPlayerActive) && buildingDeck.get(finalJ).getBuildingCost() == buyingCards.size()) {
                        for(int i = 0; i < buyingCards.size(); i++) {
                            for(int k = 0; k < playerTreasures.size(); k++) {
                                if(buyingCards.get(i).getTreasureID() == playerTreasures.get(k).getTreasureID() && playerTreasures.get(k).getSelected() == true) {
                                    playerTreasures.remove(k);
                                    hostTreasureDisplay(playerTreasures);
                                    revalidate();
                                    repaint();
                                }

                            }
                        }
                        buyingCards.clear();
                        System.out.println("Button clicked before minus: " + (buildingDeck1.get(finalJ).getBuildingName()) + (buildingDeck1.get(finalJ).getNumber()));
                        buildingDeck1.get(finalJ).setNumber(buildingDeck1.get(finalJ).getNumber() - 1);
                        System.out.println("Button clicked: " + (buildingDeck1.get(finalJ).getNumber()));
                        //buyBuildingsAction(buildingDeck.get(finalJ));
                        playerBuildings.add(buildingDeck.get(finalJ));
                        hostPanel.playerHost.setPlayerBuildings(playerBuildings);
                        serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                        serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                        System.out.println("Index added: " + finalJ);
                        hostOwnedCardsDisplay(playerBuildings);
                        serverMain.broadcastMessage(14,hostPanel.nameTextField.getText());
                    }
                    else if(!(current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText()))){
                        showError("Not Your View");
                    }
                    else if((hostPanel.playerHost.isPlayerActive)){
                        showError("Still in Level");
                    }
                });
                add(button);
                imageButtons.add(button);
                x += width + gap;


                if(x > 1281 && row1) {
                    row1 = false;
                    x = 400;
                    y += height + gap;
                }
            }


        }
    }



    public ArrayList<TreasureCard> hostTreasureDisplay(ArrayList<TreasureCard> playerTreasures) {
        treasurePanel.removeAll();

        int cardWidth = 90;
        int cardHeight = 120;
        int cardSpacing = 15;

        for(int i = 0; i < playerTreasures.size(); i++) {
            TreasureCard treasureCard = playerTreasures.get(i);
            BufferedImage image1 = treasureCard.getImage();
            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(cardWidth, cardHeight, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(cardWidth, cardHeight));
            treasurePanel.add(button1);
            int finalI = i;
            button1.addActionListener(e -> {
                System.out.println("Buying Cards1: " + buyingCards.size());
                if(buyBuildings.getText().equals("Stop Buying")) {
                    playerTreasures.get(finalI).setSelected(true);
                    buyingCards.add(playerTreasures.get(finalI));
                    button1.setEnabled(false);
                    System.out.println("Buying Cards: " + buyingCards.size());

                }
                else if((buyBuildings.equals("Buy Buildings") && current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText())) && !(hostPanel.playerHost.isPlayerActive) && (hostPanel.playerHost.isCanDrawLevel())) {
                    safeTreasuresList.add(playerTreasures.get(finalI));
                    hostPanel.playerHost.setPlayerSafeTreasures(safeTreasuresList);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    playerTreasures.remove(finalI);
                    hostPanel.playerHost.setPlayerTreasures(playerTreasures);
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    hostTreasureDisplay(playerTreasures);
                    safeTreasureDisplay(safeTreasuresList);
                    serverMain.broadcastMessage(14,hostPanel.nameTextField.getText());
                    revalidate();
                    repaint();
                } else if (!(current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText()))) {
                    showError("Not Your View");
                } else if((hostPanel.playerHost.isPlayerActive)){
                    showError("Still in Level");
                }else if (!(hostPanel.playerHost.isCanDrawLevel())) {
                    showError("Cannot Draw Cards");
                }
            });
        }
        int totalWidth = (cardWidth + cardSpacing) * playerTreasures.size();
        treasurePanel.setPreferredSize(new Dimension(totalWidth, 230));
        treasurePanel.revalidate();
        treasurePanel.repaint();
        scrollPane1.revalidate();
        scrollPane1.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalBar = scrollPane1.getHorizontalScrollBar();
            horizontalBar.setValue(horizontalBar.getMaximum());
        });
        return playerTreasures;
    }
    public void hostOwnedCardsDisplay(ArrayList<BuildingCards> playerBuildings) {
        cardsPanel.removeAll();

        int cardWidth = 140;
        int cardHeight = 210;
        int cardSpacing = 15;

        for (int i = 0; i < playerBuildings.size(); i++) {
            BufferedImage image1 = buildingSelected.get(playerBuildings.get(i).getBuildingID() - 1);
            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(cardWidth, cardHeight, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(cardWidth, cardHeight));
            cardsPanel.add(button1);
        }

        int totalWidth = (cardWidth + cardSpacing) * playerBuildings.size();
        cardsPanel.setPreferredSize(new Dimension(totalWidth, 200));

        cardsPanel.revalidate();
        cardsPanel.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
            horizontalBar.setValue(horizontalBar.getMaximum());
        });
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });
        walletUpdate(playerBuildings);
    }
    public void walletUpdate(ArrayList<BuildingCards> playerBuildings) {
        if(isHost1){
            hostPanel.playerHost.setPlayerCoins(0);
            serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
            LeaderBoardUpdateHost();
        }
        else{
            //characterSelectPanel.playerClient.setPlayerCoins(0);
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
            LeaderBoardUpdateClient();
        }
        coinTemp = 0;
        for(int i = 0; i < playerBuildings.size(); i++) {
            BuildingCards temp = playerBuildings.get(i);
            if(temp == buildingDeck.get(0)) {
                addCoin(1);
            } else if (temp == buildingDeck.get(1)) {
                addCoin(2);
            } else if (temp == buildingDeck.get(2)) {
                addCoin(3);
            }
            else if (temp == buildingDeck.get(3)) {
                addCoin(4);
            }
            else if (temp == buildingDeck.get(4)) {
                addCoin(5);
            }
            else if (temp == buildingDeck.get(5)) {
                addCoin(7);
            }
        }

    }

    public void addCoin(int number) {
        if(isHost1){
            hostPanel.playerHost.setPlayerCoins(hostPanel.playerHost.getPlayerCoins() +number);
            serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
            LeaderBoardUpdateHost();
        }else{
//            int numberTemp = characterSelectPanel.playerClient.getPlayerCoins();
//            characterSelectPanel.playerClient.setPlayerCoins(0);
//            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
//            LeaderBoardUpdateClient();
            coinTemp += number;
            characterSelectPanel.playerClient.setPlayerCoins(coinTemp);
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
            LeaderBoardUpdateClient();
        }
    }




    public void createImageButtonsClient() {
        BufferedImage treasure = treasureCardBackground;
        JButton treasureDraw = new JButton(new ImageIcon(treasure.getScaledInstance(90, 120, Image.SCALE_FAST)));
        treasureDraw.setBounds(360, 75, 90, 120);
        add(treasureDraw);
        treasureDraw.addActionListener(e -> {
            if((current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())) && (characterSelectPanel.playerClient.isPlayerActive)) {
                playerTreasures.add(shuffledDeck.remove(0));
                characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                if (!isHost1) {
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                }
                clientTreasureDisplay(playerTreasures);
                CommandFromClient.notify_INTERCLICK(clientMain.getOut(),connectPanel.nameTextField.getText());
            } else if(!(current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()))){
                showError("Not Your View");
            }
            else if((!characterSelectPanel.playerClient.isPlayerActive)){
                showError("In Buy Phase");
            }
        });
        System.out.println(cardSelectedList_g_client.size());
        System.out.println(cardSelectedList_g_client);
        System.out.println(buildingSelected);
        System.out.println(buildingNames);
        for (int i = 0; i < cardSelectedList_g_client.size(); i++) {
            if (cardSelectedList_g_client.get(i) == true) {
                buildingSelected.add(buildingNames.get(i));
                buildingDeck1.add(buildingDeck.get(i+6));
            }
        }
        System.out.println(buildingSelected);
        int x = 480;
        int y = 30;
        int width = 140;
        int height = 210;
        int gap = 20;
        boolean row1 = true;
        for(int j = 0; j < buildingSelected.size(); j++) {
            BufferedImage image = buildingSelected.get(j);
            JButton button = new JButton(new ImageIcon(image.getScaledInstance(140, 210, Image.SCALE_FAST)));
            button.setBounds(x, y, width, height);
            button.setContentAreaFilled(false);
            int finalJ = j;
            int x1 = 0;
            button.addActionListener(e -> {
                System.out.println("FINAL CHARACTER LIST (C): " + clientMain.charactersInLevel_client);
                if((current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())) && !(characterSelectPanel.playerClient.isPlayerActive)&& buildingDeck.get(finalJ).getBuildingCost() == buyingCards.size()) {
                    for(int i = 0; i < buyingCards.size(); i++) {
                        for(int k = 0; k < playerTreasures.size(); k++) {
                            if(buyingCards.get(i).getTreasureID() == playerTreasures.get(k).getTreasureID() && playerTreasures.get(k).getSelected() == true) {
                                playerTreasures.remove(k);
                                clientTreasureDisplay(playerTreasures);
                                revalidate();
                                repaint();
                            }

                        }
                    }
                    buyingCards.clear();
                    System.out.println("Button clicked before minus: " + (buildingDeck1.get(finalJ).getBuildingName()) + (buildingDeck1.get(finalJ).getNumber()));
                    buildingDeck1.get(finalJ).setNumber(buildingDeck1.get(finalJ).getNumber() - 1);
                    System.out.println("Button clicked: " + (buildingDeck1.get(finalJ).getNumber()));
                    playerBuildings.add(buildingDeck.get(finalJ));
                    characterSelectPanel.playerClient.setPlayerBuildings(playerBuildings);
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    clientOwnedCardsDisplay(playerBuildings);
                    CommandFromClient.notify_INTERCLICK(clientMain.getOut(),connectPanel.nameTextField.getText());
                }else if(!(current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()))){
                    showError("Not Your View");
                }
                else if((characterSelectPanel.playerClient.isPlayerActive)){
                    showError("Still in Level");
                }
            });
            add(button);
            imageButtons.add(button);
            x += width + gap;


            if(x > 1281 && row1) {
                row1 = false;
                x = 400;
                y += height + gap;
            }
        }


    }


    public ArrayList<TreasureCard> clientTreasureDisplay(ArrayList<TreasureCard> playerTreasures) {
        treasurePanel.removeAll();

        int cardWidth = 90;
        int cardHeight = 120;
        int cardSpacing = 20;

        for(int i = 0; i < playerTreasures.size(); i++) {
            TreasureCard treasureCard = playerTreasures.get(i);
            BufferedImage image1 = treasureCard.getImage();
            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(cardWidth, cardHeight, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(cardWidth, cardHeight));
            treasurePanel.add(button1);
            int finalI = i;
            button1.addActionListener(e -> {
                System.out.println("Buying Cards1: " + buyingCards.size());
                if(buyBuildings.getText().equals("Stop Buying")) {
                    playerTreasures.get(finalI).setSelected(true);
                    buyingCards.add(playerTreasures.get(finalI));
                    button1.setEnabled(false);
                    System.out.println("Buying Cards: " + buyingCards.size());

                }
                else if((buyBuildings.equals("Buy Buildings") && current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())) && (characterSelectPanel.playerClient.isCanDrawLevel()) && !(characterSelectPanel.playerClient.isPlayerActive)){
                    /*safeTreasuresList.add(playerTreasures.get(finalI));
                    characterSelectPanel.playerClient.setPlayerSafeTreasures(safeTreasuresList);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    playerTreasures.remove(finalI);
                    characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);

                    clientMain.playerArrayList_client.get(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())).setPlayerTreasures(playerTreasures);
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())).getPlayerTreasures());
                    CommandFromClient.sendPlayerObject(clientMain.getOut(), clientMain.playerArrayList_client.get(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())));

                    safeTreasureDisplay(safeTreasuresList);
                    CommandFromClient.notify_INTERCLICK(clientMain.getOut(),connectPanel.nameTextField.getText());
                    revalidate();
                    repaint();*/
                }   else if(!(current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()))){
                    showError("Not Your View");
                } else if((characterSelectPanel.playerClient.isPlayerActive)){
                    showError("Still in Level");
                }
                else if(!(characterSelectPanel.playerClient.isCanDrawLevel())){
                    showError("Cannot Draw Cards");
                }
            });
        }
        int totalWidth = (cardWidth + cardSpacing) * playerTreasures.size();
        treasurePanel.setPreferredSize(new Dimension(totalWidth, 230));
        treasurePanel.revalidate();
        treasurePanel.repaint();
        scrollPane1.revalidate();
        scrollPane1.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalBar = scrollPane1.getHorizontalScrollBar();
            horizontalBar.setValue(horizontalBar.getMaximum());
        });
//        for(int i = 0; i < playerTreasures.size(); i++) {
//            BufferedImage image1 = playerTreasures.get(i).getImage();
//            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(90, 120, Image.SCALE_FAST)));
//            button1.setBounds(1600+(110*i), 600, 90, 120);
//
//
//            add(button1);
//        }
//        revalidate();
//        repaint();
        return playerTreasures;
    }

    public void safeTreasureDisplay(ArrayList<TreasureCard> safeTreasuresList1) {
        safeTreasurePanel.removeAll();

        int cardWidth = 90;
        int cardHeight = 120;
        int cardSpacing = 20;
        for(int i = 0; i < safeTreasuresList1.size(); i++) {
            TreasureCard treasureCard = safeTreasuresList1.get(i);
            BufferedImage image1 = treasureCard.getImage();
            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(cardWidth, cardHeight, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(cardWidth, cardHeight));
            safeTreasurePanel.add(button1);
        }
        int totalWidth = (cardWidth + cardSpacing) * safeTreasuresList1.size();
        safeTreasurePanel.setPreferredSize(new Dimension(totalWidth, 230));
        safeTreasurePanel.revalidate();
        safeTreasurePanel.repaint();
        scrollPane2.revalidate();
        scrollPane2.repaint();
        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalBar = scrollPane2.getHorizontalScrollBar();
            horizontalBar.setValue(horizontalBar.getMaximum());
        });
    }



    public void clientOwnedCardsDisplay(ArrayList<BuildingCards> playerBuildings) {
        cardsPanel.removeAll();

        int cardWidth = 140;
        int cardHeight = 210;
        int cardSpacing = 20;

        for (int i = 0; i < playerBuildings.size(); i++) {
            BufferedImage image1 = buildingSelected.get(playerBuildings.get(i).getBuildingID() - 1);
            JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(cardWidth, cardHeight, Image.SCALE_FAST)));
            button1.setPreferredSize(new Dimension(cardWidth, cardHeight));
            cardsPanel.add(button1);
        }

        int totalWidth = (cardWidth + cardSpacing) * playerBuildings.size();
        cardsPanel.setPreferredSize(new Dimension(totalWidth, 230));

        cardsPanel.revalidate();
        cardsPanel.repaint();
        scrollPane.revalidate();
        scrollPane.repaint();

        SwingUtilities.invokeLater(() -> {
            JScrollBar horizontalBar = scrollPane.getHorizontalScrollBar();
            horizontalBar.setValue(horizontalBar.getMaximum());
        });
        walletUpdate(playerBuildings);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1050, null);

//        Graphics2D g2d = (Graphics2D) g;
        if (isHost1) {
            if(phase.getText() == "Run Phase") {
                g.drawImage(playerLevelCard, 30, 20, 320, 200, null);
            } else if(phase.getText() == "Buy Phase") {
                g.drawImage(heartCard, 30, 20, 300, 180, null);
            }
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);

            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);
//                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
//                g2d.setColor(new Color(128, 128, 128, 127));
//                g2d.fillRect(1610, 30, 140, 210);
//                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));


            }
        } else {
            if(phase.getText() == "Run Phase") {
                g.drawImage(playerLevelCard, 30, 20, 320, 200, null);
            } else if(phase.getText() == "Buy Phase") {
                g.drawImage(heartCard, 30, 20, 300, 180, null);
            }
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);
            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);


            }
        }

        if (isHost1) {
            if(phase.getText() == "Run Phase") {
                g.drawImage(playerLevelCard, 30, 20, 320, 200, null);
            } else if(phase.getText() == "Buy Phase") {
                g.drawImage(heartCard, 30, 20, 300, 180, null);
            }
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);
            g.drawImage(personalCard, 30, 260, 320, 200, null);
            int hearts = hostPanel.playerHost.getPlayerHearts();
            for (int j = 0; j < hearts; j++) {
                if (j < 2) {
                    g.drawImage(heart, 50 + (45 * j), 300, 40, 40, null);

                } else {
                    int k = j - 2;
                    g.drawImage(heart, 50 + (45 * k), 345, 40, 40, null);

                }
            }
            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);

            }
        } else {
            if(phase.getText() == "Run Phase") {
                g.drawImage(playerLevelCard, 30, 20, 320, 200, null);
            } else if(phase.getText() == "Buy Phase") {
                g.drawImage(heartCard, 30, 20, 300, 180, null);
            }
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);
            g.drawImage(personalCard, 30, 260, 320, 200, null);
            int hearts = characterSelectPanel.playerClient.getPlayerHearts();
            for (int j = 0; j < hearts; j++) {
                if (j < 2) {
                    g.drawImage(heart, 50 + (45 * j), 300, 40, 40, null);

                } else {
                    int k = j - 2;
                    g.drawImage(heart, 50 + (45 * k), 345, 40, 40, null);

                }
            }
            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);


            }


        }
        /*if(isHost1) {
            if(hostPanel.playerHost.getPlayerImage().equals("cat")) {
                g.drawImage(cat, 60, 60, 70, 105, null);
            } else if(hostPanel.playerHost.getPlayerImage().equals("indianWoman")) {
                g.drawImage(indianWoman, 110, 60, 70, 105, null);
            } else if(hostPanel.playerHost.getPlayerImage().equals("white")) {
                g.drawImage(white, 160, 60, 70, 105, null);
            } else if(hostPanel.playerHost.getPlayerImage().equals("frog")) {
                g.drawImage(frog, 210, 60, 70, 105, null);
            } else if(hostPanel.playerHost.getPlayerImage().equals("gandalf")) {
                g.drawImage(gandalf, 260, 60, 70, 105, null);
            }
            setVisible(true);
        }*/

        if (isHost1) {
            System.out.println(tempChar);
            if(tempChar.size() != 0) {
                for (int i = 0; i < tempChar.size(); i++) {
                    if ((tempChar.get(i)).equals("catB")) {
                        g.drawImage(cat, 60, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("indianWomanB"))) {
                        g.drawImage(indianWoman, 110, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("whiteB"))) {
                        g.drawImage(white, 160, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("frogB"))) {
                        g.drawImage(frog, 210, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("gandalfB"))) {
                        g.drawImage(gandalf, 260, 60, 70, 105, null);
                    }
                    setVisible(true);
                }
            }
        } else {
            if (tempChar.size() == 0) {
                phase.setText("Buy Phase");
            }
            System.out.println(tempChar);
            if(tempChar.size() != 0) {
                for (int i = 0; i < tempChar.size(); i++) {
                    if ((tempChar.get(i)).equals("catB")) {
                        g.drawImage(cat, 60, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("indianWomanB"))) {
                        g.drawImage(indianWoman, 110, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("whiteB"))) {
                        g.drawImage(white, 160, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("frogB"))) {
                        g.drawImage(frog, 210, 60, 70, 105, null);
                    } else if ((tempChar.get(i).equals("gandalfB"))) {
                        g.drawImage(gandalf, 260, 60, 70, 105, null);
                    }
                    setVisible(true);
                }
            }
        }
    }
    public String playerGameView(int x){
        cardsPanel.setEnabled(false);
        System.out.println(x);
        System.out.println("Night Debug Test");
        if(isHost1) {
            for (int i = 0; i <serverMain.playerArrayList_Host.size();i++){
                System.out.println(serverMain.playerArrayList_Host.get(i).getPlayerName());
                System.out.println(serverMain.playerArrayList_Host.get(i).getPlayerID());
            }
        }
        else{
            for (int i = 0; i <clientMain.playerArrayList_client.size();i++){
                System.out.println(clientMain.playerArrayList_client.get(i).getPlayerName());
                System.out.println(clientMain.playerArrayList_client.get(i).getPlayerID());
            }
        }

        if(isHost1){
            switch (x){
                case 0:
                    System.out.println("Player 1");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                    serverMain.playerArrayList_Host.get(x).setPlayerTreasures(hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerSafeTreasures());
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
                case 1:
                    System.out.println("Player 2");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                    serverMain.playerArrayList_Host.get(x).setPlayerTreasures(hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerSafeTreasures());
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
                case 2:
                    System.out.println("Player 3");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                    serverMain.playerArrayList_Host.get(x).setPlayerTreasures(hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerSafeTreasures());
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
                case 3:
                    System.out.println("Player 4");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                    serverMain.playerArrayList_Host.get(x).setPlayerTreasures(hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerSafeTreasures());
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
                case 4:
                    System.out.println("Player 5");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                    serverMain.playerArrayList_Host.get(x).setPlayerTreasures(hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerSafeTreasures());
                    serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
            }
        }
        else{
            switch (x){
                case 0:
                    System.out.println("Player 1");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientMain.playerArrayList_client.get(x).setPlayerTreasures(clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerSafeTreasures());
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        clientTreasureDisplay(playerTreasures);
                        characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                        CommandFromClient.sendPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                //break;
                case 1:
                    System.out.println("Player 2");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientMain.playerArrayList_client.get(x).setPlayerTreasures(clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerSafeTreasures());
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        clientTreasureDisplay(playerTreasures);
                        characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                        CommandFromClient.sendPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                //break;
                case 2:
                    System.out.println("Player 3");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientMain.playerArrayList_client.get(x).setPlayerTreasures(clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerSafeTreasures());
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        clientTreasureDisplay(playerTreasures);
                        characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                        CommandFromClient.sendPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                //break;
                case 3:
                    System.out.println("Player 4");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientMain.playerArrayList_client.get(x).setPlayerTreasures(clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerSafeTreasures());
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        clientTreasureDisplay(playerTreasures);
                        characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                        CommandFromClient.sendPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                //break;
                case 4:
                    System.out.println("Player 5");
                    cardsPanel.removeAll();
                    treasurePanel.removeAll();
                    safeTreasurePanel.removeAll();

                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientMain.playerArrayList_client.get(x).setPlayerTreasures(clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures()));
                    safeTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerSafeTreasures());
                    if (!isHost1) {
                        CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    }
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        clientTreasureDisplay(playerTreasures);
                        characterSelectPanel.playerClient.setPlayerTreasures(playerTreasures);
                        CommandFromClient.sendPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        safeTreasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                //break;
            }
        }
        return null;
    }

    public void GUILevelCardsHost(){
        if(hostPanel.playerHost.getPlayerLevelCards().size() != 0) {

            LevelCard temp = hostPanel.playerHost.getPlayerLevelCards().remove(0);
            int skulls = temp.getNumberSkulls();
            levelCardActions(skulls);
            hostPanel.playerHost.setPlayerLevelCards(hostPanel.playerHost.getPlayerLevelCards());
            serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);

            drawnLevelCard.add(temp);
            //levelDisplay();
            revalidate();
            repaint();
            if(hostPanel.playerHost.getPlayerLevelCards().size()==0){
                createLevelDeck_Host();
            }
        }
    }
    public void GUILevelCardsClient(){
        if(characterSelectPanel.playerClient.getPlayerLevelCards().size() != 0) {
            LevelCard temp = characterSelectPanel.playerClient.getPlayerLevelCards().remove(0);
            int skulls = temp.getNumberSkulls();
            levelCardActions(skulls);
            characterSelectPanel.playerClient.setPlayerLevelCards(characterSelectPanel.playerClient.getPlayerLevelCards());
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);

            drawnLevelCard.add(temp);
            //levelDisplay();
            revalidate();
            repaint();
            if(characterSelectPanel.playerClient.getPlayerLevelCards().size()!=0){
                createLevelDeck_Client();
            }
        }
    }

    public void levelCardActions(int skulls){
        if(isHost1){
            if (hostPanel.playerHost.isPlayerActive) {
                if(hasJumpedLevel) {
                    System.out.println("Nothing happened you are safe");
                    hasJumpedLevel = false;
                } else {
                    System.out.println("Hearts to be taken off:" + skulls);
                    hostPanel.playerHost.setPlayerHearts(hostPanel.playerHost.getPlayerHearts() - skulls);
                    if (hostPanel.playerHost.getPlayerHearts() <= 0) {
                        hostPanel.playerHost.setPlayerHearts(0);
                        if (hostPanel.playerHost.isPlayerActive) {
                            hostPanel.playerHost.setPlayerActive(false);
                            serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                            serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                            takeOff.setText("Dropped Level");
                            treasurePanel.removeAll();
                            takeOff.setEnabled(false);
                            hostPanel.playerHost.getPlayerTreasures().clear();
                            tempChar.remove(hostPanel.playerHost.getPlayerImage());
                            repaint();
                            revalidate();
                            serverMain.broadcastMessage(16, hostPanel.nameTextField.getText() + "-" + hostPanel.playerHost.getPlayerImage());
                        }
                        else{
                            showError("Player Not Active");
                        }
                    }
                }


                serverMain.playerArrayList_Host.set(0, hostPanel.playerHost);
                serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                LeaderBoardUpdateHost();
                personalWallet.repaint();
                personalWallet.revalidate();
                repaint();
                revalidate();
            }

        }else{
            if (characterSelectPanel.playerClient.isPlayerActive) {
                if(hasJumpedLevel) {
                    hasJumpedLevel = false;
                    System.out.println("Nothing happened you are safe");
                } else {
                    System.out.println("Hearts to be taken off:" + skulls);
                    characterSelectPanel.playerClient.setPlayerHearts(characterSelectPanel.playerClient.getPlayerHearts()-skulls);
                    if(characterSelectPanel.playerClient.getPlayerHearts() <= 0) {
                        characterSelectPanel.playerClient.setPlayerHearts(0);
                        if (characterSelectPanel.playerClient.isPlayerActive) {
                            characterSelectPanel.playerClient.setPlayerActive(false);
                            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                            takeOff.setText("Dropped Level");
                            takeOff.setEnabled(false);
                            treasurePanel.removeAll();
                            characterSelectPanel.playerClient.getPlayerTreasures().clear();
                            tempChar.remove(characterSelectPanel.playerClient.getPlayerImage());
                            repaint();
                            revalidate();
                            CommandFromClient.notify_LEVELDISCONNECTION(clientMain.getOut(), connectPanel.nameTextField.getText() + "-" + characterSelectPanel.playerClient.getPlayerImage());
                        }
                        else{
                            showError("Player Not Active");
                        }
                    }
                    CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
                    LeaderBoardUpdateClient();
                    repaint();
                    revalidate();
                }
            }

        }
    }

    private static void showError(String message) {
        ErrorArea.setVisible(true);
        ErrorArea.setText(message);
        Timer timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ErrorArea.setText("");
                ErrorArea.setVisible(false);
                ((Timer)e.getSource()).stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void wallet(String playerName, int hearts, int coins, int jumps) {
        heartCount.setText(String.valueOf(hearts));
        coinCount.setText(String.valueOf(coins));
        jumpCount.setText(String.valueOf(jumps));
        revalidate();
        repaint();
        personalWallet.revalidate();
        personalWallet.repaint();
    }

    public void LeaderBoardUpdateHost() {
        if (this.isHost1) {
            LeaderBoard.removeAll();

            JLabel leaderBoardName = new JLabel("LeaderBoard (Host)", JLabel.CENTER);
            leaderBoardName.setFont(new Font("Georgia", Font.BOLD, 18));
            leaderBoardName.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
            leaderBoardName.setOpaque(true);
            leaderBoardName.setBackground(Color.GRAY);
            leaderBoardName.setForeground(Color.WHITE);

            LeaderBoard.setLayout(new BorderLayout());
            LeaderBoard.setBackground(Color.BLACK);
            LeaderBoard.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            LeaderBoard.add(leaderBoardName, BorderLayout.NORTH);

            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new GridLayout(0, 4)); // Dynamic rows, 4 columns
            playerPanel.setBackground(Color.BLACK);
            playerPanel.setForeground(Color.WHITE);

            playerPanel.add(new JLabel("Name", JLabel.CENTER));
            playerPanel.add(new JLabel("Hearts", JLabel.CENTER));
            playerPanel.add(new JLabel("Coins", JLabel.CENTER));
            playerPanel.add(new JLabel("Jumps", JLabel.CENTER));

            for (Component comp : playerPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setFont(new Font("Georgia", Font.PLAIN, 14));
                    ((JLabel) comp).setForeground(Color.GREEN);
                    ((JLabel) comp).setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }
            }

            for (int i = 0; i < serverMain.playerArrayList_Host.size(); i++) {
                String playerName = serverMain.playerArrayList_Host.get(i).getPlayerName();
                int HeartsTemp = serverMain.playerArrayList_Host.get(i).getPlayerHearts();
                int CoinsTemp = serverMain.playerArrayList_Host.get(i).getPlayerCoins();
                int JumpTemp = serverMain.playerArrayList_Host.get(i).getPlayerJumps();



                JLabel nameLabel = new JLabel(playerName, JLabel.CENTER);
                nameLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                nameLabel.setForeground(Color.WHITE);

                JLabel heartsLabel = new JLabel(String.valueOf(HeartsTemp), JLabel.CENTER);
                heartsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                heartsLabel.setForeground(Color.WHITE);

                JLabel coinsLabel = new JLabel(String.valueOf(CoinsTemp), JLabel.CENTER);
                coinsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                coinsLabel.setForeground(Color.WHITE);

                JLabel jumpsLabel = new JLabel(String.valueOf(JumpTemp), JLabel.CENTER);
                jumpsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                jumpsLabel.setForeground(Color.WHITE);

                playerPanel.add(nameLabel);
                playerPanel.add(heartsLabel);
                playerPanel.add(coinsLabel);
                playerPanel.add(jumpsLabel);

            }

            LeaderBoard.add(playerPanel, BorderLayout.CENTER);
            LeaderBoard.revalidate();
            LeaderBoard.repaint();
        }
    }

    public void LeaderBoardUpdateClient() {
        if (!this.isHost1) {
            LeaderBoard.removeAll();

            JLabel leaderBoardName = new JLabel("LeaderBoard (Client)", JLabel.CENTER);
            leaderBoardName.setFont(new Font("Georgia", Font.BOLD, 18));
            leaderBoardName.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
            leaderBoardName.setOpaque(true);
            leaderBoardName.setBackground(Color.GRAY);
            leaderBoardName.setForeground(Color.WHITE);

            LeaderBoard.setLayout(new BorderLayout());
            LeaderBoard.setBackground(Color.BLACK);
            LeaderBoard.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
            LeaderBoard.add(leaderBoardName, BorderLayout.NORTH);

            JPanel playerPanel = new JPanel();
            playerPanel.setLayout(new GridLayout(0, 4)); // Dynamic rows, 4 columns
            playerPanel.setBackground(Color.BLACK);
            playerPanel.setForeground(Color.WHITE);

            playerPanel.add(new JLabel("Name", JLabel.CENTER));
            playerPanel.add(new JLabel("Hearts", JLabel.CENTER));
            playerPanel.add(new JLabel("Coins", JLabel.CENTER));
            playerPanel.add(new JLabel("Jumps", JLabel.CENTER));

            for (Component comp : playerPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    ((JLabel) comp).setFont(new Font("Georgia", Font.PLAIN, 14));
                    ((JLabel) comp).setForeground(Color.GREEN);
                    ((JLabel) comp).setBorder(BorderFactory.createLineBorder(Color.WHITE));
                }
            }
            JLabel coinsLabel;
            for (int i = 0; i < clientMain.playerArrayList_client.size(); i++) {
                String playerName = clientMain.playerArrayList_client.get(i).getPlayerName();
                int HeartsTemp = clientMain.playerArrayList_client.get(i).getPlayerHearts();
                int CoinsTemp = clientMain.playerArrayList_client.get(i).getPlayerCoins();
                int JumpTemp = clientMain.playerArrayList_client.get(i).getPlayerJumps();

                JLabel nameLabel = new JLabel(playerName, JLabel.CENTER);
                nameLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                nameLabel.setForeground(Color.WHITE);

                JLabel heartsLabel = new JLabel(String.valueOf(HeartsTemp), JLabel.CENTER);
                heartsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                heartsLabel.setForeground(Color.WHITE);

                if(i == (clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText()))) {
                    coinsLabel = new JLabel(String.valueOf(clientMain.playerArrayList_client.get(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())).getPlayerCoins()), JLabel.CENTER);
                    coinsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                    coinsLabel.setForeground(Color.WHITE);
                }
                else{
                    coinsLabel = new JLabel(String.valueOf(CoinsTemp), JLabel.CENTER);
                    coinsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                    coinsLabel.setForeground(Color.WHITE);
                }

                JLabel jumpsLabel = new JLabel(String.valueOf(JumpTemp), JLabel.CENTER);
                jumpsLabel.setFont(new Font("Georgia", Font.PLAIN, 14));
                jumpsLabel.setForeground(Color.WHITE);

                playerPanel.add(nameLabel);
                playerPanel.add(heartsLabel);
                playerPanel.add(coinsLabel);
                playerPanel.add(jumpsLabel);

            }

            LeaderBoard.add(playerPanel, BorderLayout.CENTER);
            LeaderBoard.revalidate();
            LeaderBoard.repaint();
        }
    }
    public ChatPanel getChatPanel() {
        return chatPanel1;
    }
    public InGameRulesPanel getinGameRulesPanel() {
        return inGameRulesPanel1;
    }
}