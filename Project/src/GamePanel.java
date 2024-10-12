package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public ArrayList<TreasureCard> usedTreasureCard = new ArrayList();
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
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane;
    private static JLabel ErrorArea;

    //missing one\
    //skip 25 its a repeat
    private BufferedImage personalCard, heartCard, starCardBackground,
            sandwichStand, cafe, arcade, bazaarOfOddities, hotel, templeOfZoz,
            buildingCardBackground, reptileStable, herbHut, ostrichRanch, gym, hospital, laboratory, fishingPond, bowlingAlley, smithy, fishVendor, tollBooth,
            soapMakers, hallOfElders, lodge, rootMarket, endlessMine, arena,
            backOfLevelCard, levelCard31, levelCard32, levelCard33, levelCard34, levelCard35, levelCard36, levelCard37, levelCard38, levelCard39, levelCard40,
            treasureCardBackground, gear, cube, egg, carrot, mineral, fish,
            coin1, coin5, coin10, firstPlayerToken, heart, jump, indianWoman, gandalf, cat, frog, white, playerLevelCard;




    public GamePanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost, ChatPanel chatPanel, InGameRulesPanel inGameRulesPanel) {
        this.chatPanel1 = chatPanel;
        this.inGameRulesPanel1 = inGameRulesPanel;
        //this.cardSelectPanel = cardSelectPanel;

        ErrorArea = new JLabel("",JLabel.CENTER);
        ErrorArea.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
        ErrorArea.setBackground(Color.PINK);
        ErrorArea.setForeground(Color.RED);
        ErrorArea.setFont(new Font("Roman", Font.BOLD, 20));
        ErrorArea.setBounds(30, 740, 230, 40);
        //ErrorArea.setEditable(false);
        add(ErrorArea);
        ErrorArea.setVisible(false);
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        treasurePanel = new JPanel();
        treasurePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        scrollPane1 = new JScrollPane(treasurePanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setBounds(280, 790, 160 * 9 + 40, 140);
        add(scrollPane1);

        scrollPane = new JScrollPane(cardsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(280, 540, 160 * 9 + 40, 230);
        add(scrollPane);

        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));


        if(isHost){
            current_player = 0;
        }
        else{
            current_player = clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText());
        }
        setSize(1920, 1010);
        setLayout(null);

        JButton leftArrow = new JButton("←");
        leftArrow.setBounds(760, 485, 50, 40);
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
        JButton rightArrow = new JButton("→");
        rightArrow.setBounds(1090, 485, 50, 40);
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
                            }
                            else{
                                showError("Player Not Active");
                            }
                        }
                    }
                });

        playerLabel = new JLabel("My View", JLabel.CENTER);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerLabel.setBounds(860, 485, 200, 50);
        JPanel backgroundP = new JPanel();
        backgroundP.setBounds(750,480, 400,40);
        backgroundP.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        backgroundP.setBackground(Color.orange);
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
            serverMain.setChatPanel(this.chatPanel1);
        }
        else{
            clientMain.setChatPanel(this.chatPanel1);
        }




        try {
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
            System.out.println("CardSelectedList (C): " + cardSelectedList_g_client);
            /*if(clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())==1) {
                characterSelectPanel.playerClient.setPlayerID(1000);
            }*/
            System.out.println(characterSelectPanel.playerClient.getPlayerID());
            CommandFromClient.notifyPlayerObject(clientMain.getOut(), characterSelectPanel.playerClient);
        }





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
                    } else if(!(hostPanel.playerHost.isPlayerActive)){
                        showError("Player Not Active");
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
                    showError("Player Not Active");
                }
                else if(!(characterSelectPanel.playerClient.isCanDrawLevel())){
                    showError("Cannot Draw Cards");
                }
        });
    }


    public void createDeck() {
        for(int i = 0; i < 6; i++) {
            unshuffledDeck.add(new TreasureCard(1, "gear", false, gear, "Project\\src\\Images\\2024-08-19-10-14-0042.jpg"));//6
        }
        for(int i = 0; i < 20; i++) {
            unshuffledDeck.add(new TreasureCard(2, "cube", false, cube, "Project\\src\\Images\\2024-08-19-10-14-0043.jpg"));//20
        }
        for(int i = 0; i < 16; i++) {
            unshuffledDeck.add(new TreasureCard(3, "egg", false, egg, "Project\\src\\Images\\2024-08-19-10-14-0044.jpg"));//16
        }
        for(int i = 0; i < 30; i++) {
            unshuffledDeck.add(new TreasureCard(4, "carrot", false, carrot, "Project\\src\\Images\\2024-08-19-10-14-0045.jpg"));//30
        }
        for(int i = 0; i < 10; i++) {
            unshuffledDeck.add(new TreasureCard(5, "mineral", false, mineral, "Project\\src\\Images\\2024-08-19-10-14-0046.jpg"));//10
        }
        for(int i = 0; i < 14; i++) {
            unshuffledDeck.add(new TreasureCard(6, "fish", false,fish, "Project\\src\\Images\\2024-08-19-10-14-0047.jpg"));//14
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
                    System.out.println(hostPanel.playerHost.getPlayerTreasures());
                    serverMain.playerArrayList_Host.set(0,hostPanel.playerHost);
                    serverMain.broadcastMessagePlayers(serverMain.playerArrayList_Host);
                    hostTreasureDisplay(playerTreasures);
                    serverMain.broadcastMessage(14,hostPanel.nameTextField.getText());
                }
                else if(!(current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText()))){
                    showError("Not Your View");
                }
                else if(!(hostPanel.playerHost.isPlayerActive)){
                    showError("Player Not Active");
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
                    if((current_player == serverMain.gamePlayerNames.indexOf(hostPanel.nameTextField.getText())) && (hostPanel.playerHost.isPlayerActive)) {
                        System.out.println("Button clicked before minus: " + (buildingDeck1.get(finalJ).getBuildingName()) + (buildingDeck1.get(finalJ).getNumber()));
                        buildingDeck1.get(finalJ).setNumber(buildingDeck1.get(finalJ).getNumber() - 1);
                        System.out.println("Button clicked: " + (buildingDeck1.get(finalJ).getNumber()));
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
                        else if(!(hostPanel.playerHost.isPlayerActive)){
                            showError("Player Not Active");
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
    public void hostTreasureDisplay(ArrayList<TreasureCard> playerTreasures) {
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
            else if(!(characterSelectPanel.playerClient.isPlayerActive)){
                showError("Player Not Active");
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
                if((current_player == clientMain.Final_gamePlayerNames_ClientSide.indexOf(connectPanel.nameTextField.getText())) && (characterSelectPanel.playerClient.isPlayerActive)){
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
                else if(!(characterSelectPanel.playerClient.isPlayerActive)){
                    showError("Player Not Active");
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


    public void clientTreasureDisplay(ArrayList<TreasureCard> playerTreasures) {
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
    }
    public GamePanel() {
        playerBuildings = new ArrayList<>();
        players = new ArrayList<>();
        initializePlayers();
    }

    public void initializePlayers() {
        players.add("Player 1");
        players.add("Player 2");
        players.add("Player 3");
    }

    public void initializeGameTimer() {
        int countdown = 60;
        while (countdown > 0) {
            countdown--;
        }
    }

    public void checkPlayerStatistics() {
        for (int i = 0; i < playerBuildings.size(); i++) {
            boolean active = playerBuildings.get(i).isActive();
            if (active) {
                System.out.println("Player building " + i + " is active.");
            }
        }
    }

    public void generateRandomEvents() {
        int event = (int) (Math.random() * 5);
        switch (event) {
            case 0:
                System.out.println("Random event: Nothing happens.");
                break;
            case 1:
                System.out.println("Random event: A storm approaches!");
                break;
            case 2:
                System.out.println("Random event: A treasure is found!");
                break;
            case 3:
                System.out.println("Random event: A building is under attack!");
                break;
            case 4:
                System.out.println("Random event: Resources have been depleted!");
                break;
        }
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
        if(isHost1) {
            g.drawImage(playerLevelCard, 30, 20,320, 200, null);
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);
            g.drawImage(personalCard, 30, 260, 320, 200, null);
            int hearts = hostPanel.playerHost.getPlayerHearts();
            for(int j = 0; j < 4; j++) {
                if(j <2) {
                    g.drawImage(heart, 50 + (45*j), 300, 40, 40, null);

                } else {
                    int k = j-2;
                    g.drawImage(heart, 50 + (45*k), 345, 40, 40, null);

                }
            }
            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);

            }
        } else {
            g.drawImage(playerLevelCard, 30, 20,320, 200, null);
            takeOff.setBounds(40, 220, 300, 30);
            add(takeOff);
            g.drawImage(personalCard, 30, 260, 320, 200, null);
            int hearts = characterSelectPanel.playerClient.getPlayerHearts();
            for(int j = 0; j < 4; j++) {
                if(j <2) {
                    g.drawImage(heart, 50 + (45*j), 300, 40, 40, null);

                } else {
                    int k = j-2;
                    g.drawImage(heart, 50 + (45*k), 345, 40, 40, null);

                }
            }
            for (int i = 0; i < drawnLevelCard.size(); i++) {
                BufferedImage image1 = drawnLevelCard.get(i).getImage();
                Image scaledImage = image1.getScaledInstance(140, 210, Image.SCALE_FAST);
                g.drawImage(scaledImage, 1610, 30, this);


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
                hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures());
                if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    return "My";
                }
                else{
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                }
                return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
            case 1:
                System.out.println("Player 2");
                cardsPanel.removeAll();
                hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures());
                if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    return "My";
                }
                else{
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                }
                return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
            case 2:
                System.out.println("Player 3");
                cardsPanel.removeAll();
                hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures());
                if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    return "My";
                }
                else{
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                }
                return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
            case 3:
                System.out.println("Player 4");
                cardsPanel.removeAll();
                hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures());
                if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    return "My";
                }
                else{
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                }
                return serverMain.playerArrayList_Host.get(x).getPlayerName();
                //break;
            case 4:
                System.out.println("Player 5");
                cardsPanel.removeAll();
                hostOwnedCardsDisplay(serverMain.playerArrayList_Host.get(x).getPlayerBuildings());
                hostTreasureDisplay(serverMain.playerArrayList_Host.get(x).getPlayerTreasures());
                if(serverMain.gamePlayerNames.get(x).equals(hostPanel.nameTextField.getText())){
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                    return "My";
                }
                else{
                    cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
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
                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures());
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                    //break;
                case 1:
                    System.out.println("Player 2");
                    cardsPanel.removeAll();
                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures());
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                    //break;
                case 2:
                    System.out.println("Player 3");
                    cardsPanel.removeAll();
                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures());
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                    //break;
                case 3:
                    System.out.println("Player 4");
                    cardsPanel.removeAll();
                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures());
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                    }
                    return clientMain.playerArrayList_client.get(x).getPlayerName();
                    //break;
                case 4:
                    System.out.println("Player 5");
                    cardsPanel.removeAll();
                    clientOwnedCardsDisplay(clientMain.playerArrayList_client.get(x).getPlayerBuildings());
                    clientTreasureDisplay(clientMain.playerArrayList_client.get(x).getPlayerTreasures());
                    if(clientMain.Final_gamePlayerNames_ClientSide.get(x).equals(connectPanel.nameTextField.getText())){
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2,true));
                        return "My";
                    }
                    else{
                        cardsPanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
                        treasurePanel.setBorder(BorderFactory.createLineBorder(Color.RED,2,true));
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
    public void initializeComponents() {
        setLayout(new BorderLayout());
        WelcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        add(WelcomeLabel, BorderLayout.NORTH);
    }

    public void addPlayerLabel(String playerName) {
        playerLabel = new JLabel("Welcome " + playerName);
        add(playerLabel, BorderLayout.SOUTH);
    }

    public void setupChatButton() {
        chatButton = new JButton("Chat");
        chatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        add(chatButton, BorderLayout.EAST);
    }


    public void GUILevelCardsClient(){
        if(characterSelectPanel.playerClient.getPlayerLevelCards().size() != 0) {
            LevelCard temp = characterSelectPanel.playerClient.getPlayerLevelCards().remove(0);
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

    public ChatPanel getChatPanel() {
        return chatPanel1;
    }
    public InGameRulesPanel getinGameRulesPanel() {
        return inGameRulesPanel1;
    }
    private void initializeGameSettings() {
        boolean gameSettingsLoaded = loadGameSettings();
        if (gameSettingsLoaded) {
            System.out.println("Game settings loaded successfully.");
        } else {
            System.out.println("Failed to load game settings.");
        }
    }

    private boolean loadGameSettings() {
        // Simulate loading settings
        try {
            Thread.sleep(100);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    private void updatePlayerStats() {
        for (int i = 0; i < 10; i++) {
            int randomStat = (int) (Math.random() * 100);
            System.out.println("Updating player stat: " + randomStat);

            if (randomStat > 50) {
                System.out.println("Stat is above average.");
            } else {
                System.out.println("Stat is below average.");
            }
        }
    }

    private void simulateGameRound() {
        for (int round = 1; round <= 5; round++) {
            System.out.println("Simulating round " + round);
            boolean roundOutcome = Math.random() > 0.5;
            if (roundOutcome) {
                System.out.println("Round " + round + " was successful.");
            } else {
                System.out.println("Round " + round + " failed.");
            }
        }
    }

    private void displayGameStats() {
        int totalGamesPlayed = 10;
        int gamesWon = 7;
        System.out.println("Total Games Played: " + totalGamesPlayed);
        System.out.println("Games Won: " + gamesWon);
        System.out.println("Winning Percentage: " + (gamesWon * 100 / totalGamesPlayed) + "%");
    }

    private void resetGame() {
        System.out.println("Resetting game...");
        boolean resetSuccessful = performReset();
        if (resetSuccessful) {
            System.out.println("Game reset successfully.");
        } else {
            System.out.println("Game reset failed.");
        }
    }
    private boolean performReset() {
        return true;
    }

    private void saveGameProgress() {
        System.out.println("Saving game progress...");
        boolean saveSuccessful = saveProgressToFile();
        if (saveSuccessful) {
            System.out.println("Game progress saved successfully.");
        } else {
            System.out.println("Failed to save game progress.");
        }
    }

    private boolean saveProgressToFile() {
        return true;
    }

    private boolean checkPlayerReadiness() {
        return Math.random() > 0.5;
    }

    public void loadBuildingImages() {
        try {
            buildingNames.add(ImageIO.read(new File("path/to/image1.png")));
            buildingNames.add(ImageIO.read(new File("path/to/image2.png")));
            // Load more images as needed
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    private void generateRandomEvents() {
        String[] events = {"Picks a Building Card", "Picks a Treasure Card", "Drops out of Level", "Gains or Wins the Game"};
        int randomIndex = (int) (Math.random() * events.length);
        System.out.println("Event Occurred: " + events[randomIndex]);
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

    public void setupLevelCardDeck() {
        deckLevelCard.add(new LevelCard("Level 1", 1));
        deckLevelCard.add(new LevelCard("Level 2", 2));
        // Add more level cards as needed
    }
}