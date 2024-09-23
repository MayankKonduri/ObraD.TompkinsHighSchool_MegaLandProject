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
import java.util.Arrays;

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
        setSize(1920, 1010);
        setLayout(null);
        int i=100;

//        chatButton = new JButton("Chat");
//        chatButton.setBounds(10,950,100,30);
//        chatButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                toggleChatPanel();
//            }
//        });
//        add(chatButton);
        chatPanel1 = new ChatPanel(frame, chatButton, clientMain, serverMain, hostPanel, connectPanel, cardSelectPanel, characterSelectPanel, isHost, this);
        chatPanel1.setBounds(20,760,700,600);
        chatPanel1.setVisible(false);
        add(chatPanel1);
        toggleChatPanel();
        buildingCardsInstantiate();

        chatPanel1.setForeground(Color.YELLOW);

        rules.setBounds(1830, 20, 70, 30);
        rules.addActionListener(e -> {
            toggleInGameRulesPanel();
        });
        add(rules);

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
            templeOfZoz = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0010.png")));
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

        }else {
            System.out.println("Connected Players (C): " + clientMain.gamePlayerNames_ClientSide);
            stringCardPanel = clientMain.cardPanel_Client_Side;
            trimmed_stringCardPanel = stringCardPanel.substring(1, stringCardPanel.length()-1);
            array_trimmed_stringCardPanel = trimmed_stringCardPanel.split(",");
            for(String element: array_trimmed_stringCardPanel){
                booleanValue = Boolean.parseBoolean(element.trim());
                cardSelectedList_g_client.add(booleanValue);
            }
            createImageButtonsClient();
            System.out.println("CardSelectedList (C): " + cardSelectedList_g_client);
        }

//        System.out.println("List of Selected Buildings: " + cardSelectPanel.buildingsSelect);

    }

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

//        buildingDeck1.add(new BuildingCards(1, "Sandwich Stand", 1, true, false, sandwichStand, 4));
//        buildingDeck1.add(new BuildingCards(2, "Cafe", 2, true, false, cafe, 4));
//        buildingDeck1.add(new BuildingCards(3, "Arcade", 3, true, false, arcade, 4));
//        buildingDeck1.add(new BuildingCards(4, "Bazaar of Oddities", 4, true, false, bazaarOfOddities, 4));
//        buildingDeck1.add(new BuildingCards(5, "Hotel", 5, true, false, hotel, 4));
//        buildingDeck1.add(new BuildingCards(6, "Temple of Zoz", 6, true, false, templeOfZoz, 4));
//        if(isHost1) {
//            for (int i = 0; i < cardSelectPanel.buildingsSelect.size(); i++) {
//                if (cardSelectPanel.buildingsSelect.get(i) == true) {
//                    buildingDeck1.add(buildingDeck.get(i+5));
//                }
//            }
//        } else {
//            for (int i = 0; i < cardSelectedList_g_client.size(); i++) {
//                if (cardSelectedList_g_client.get(i) == true) {
//                    buildingDeck1.add(buildingDeck.get(i+5));
//                }
//            }
//        }

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


        } else {
            inGameRulesPanel1.openRules();
            chatPanel1.closeChat();
            rules.setVisible(false);
        }
    }
    public void openGame(){
        setVisible(true);
        toggleChatPanel();
        rules.setVisible(true);
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
                int finalx1 = x1;

                button.addActionListener(e -> {
                    System.out.println("Button clicked before minus: " + (buildingDeck1.get(finalJ).getBuildingName()) + (buildingDeck1.get(finalJ).getNumber()));
                    buildingDeck1.get(finalJ).setNumber(buildingDeck1.get(finalJ).getNumber()-1);
                    System.out.println("Button clicked: " + (buildingDeck1.get(finalJ).getNumber()));
                    BufferedImage image1 = buildingSelected.get(finalJ);
                    JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(140, 210, Image.SCALE_FAST)));
                    button1.setBounds(900 + finalx1, 600, 140, 210);
                    add(button1);

                    revalidate();
                    repaint();

                });
                add(button);
                imageButtons.add(button);
                x += width + gap;

                if(x > 1281 && row1) {
                    row1 = false;
                    x = 400;
                    y += height + gap;
                }
                x1 = x1+160;
            }
        }
    }

    public void createImageButtonsClient() {

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
//        for (BufferedImage image : buildingSelected) {
        for(int j = 0; j < buildingSelected.size(); j++) {
            BufferedImage image = buildingSelected.get(j);
            JButton button = new JButton(new ImageIcon(image.getScaledInstance(140, 210, Image.SCALE_FAST)));
            button.setBounds(x, y, width, height);
            button.setContentAreaFilled(false);
            int finalJ = j;
            int x1 = 0;
            button.addActionListener(e -> {
                System.out.println("Button clicked before minus: " + (buildingDeck1.get(finalJ).getBuildingName()) + (buildingDeck1.get(finalJ).getNumber()));
                buildingDeck1.get(finalJ).setNumber(buildingDeck1.get(finalJ).getNumber()-1);
                System.out.println("Button clicked: " + (buildingDeck1.get(finalJ).getNumber()));
                BufferedImage image1 = buildingSelected.get(finalJ);
                JButton button1 = new JButton(new ImageIcon(image1.getScaledInstance(140, 210, Image.SCALE_FAST)));
                button1.setBounds(900, 600, 140, 210);
                add(button1);
                revalidate();
                repaint();


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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if(isHost1) {
//            int j = 0;
//            g.drawImage(sandwichStand, 480, 30, 140, 210, null);
//            g.drawImage(cafe, 640, 30, 140, 210, null);
//            g.drawImage(arcade, 800, 30, 140, 210, null);
//            g.drawImage(bazaarOfOddities, 960, 30, 140, 210, null);
//            g.drawImage(hotel, 1120, 30, 140, 210, null);
//            g.drawImage(templeOfZoz, 1280, 30, 140, 210, null);
//            for(int i = 0; i < cardSelectPanel.buildingsSelect.size(); i++) {
//                boolean selected = false;
//                if(cardSelectPanel.buildingsSelect.get(i) == true) {
//                    selected = true;
//                    g.drawImage(buildingNames.get(i), 400+ (j*160), 260, 140, 210, null);
//
//                }
//                if(selected) {
//                    j++;
//                }
//            }
//        } else {
//            int x = 0;
//            g.drawImage(sandwichStand, 540, 40, 140, 210, null);
//            g.drawImage(cafe, 680, 40, 140, 210, null);
//            g.drawImage(arcade, 820, 40, 140, 210, null);
//            g.drawImage(bazaarOfOddities, 960, 140, 120, 210, null);
//            g.drawImage(hotel, 1100, 40, 140, 210, null);
//            g.drawImage(templeOfZoz, 1240, 40, 140, 210, null);
//            for(int i = 0; i < cardSelectedList_g_client.size(); i++) {
//                boolean selected = false;
//                if(cardSelectedList_g_client.get(i) == true) {
//                    selected = true;
//                    g.drawImage(buildingNames.get(i), 400+ (x*160), 260, 140, 210, null);
//                }
//                if(selected) {
//                    x++;
//                }
//            }
//
//
//        }

    }



    public ChatPanel getChatPanel() {
        return chatPanel1;
    }
    public InGameRulesPanel getinGameRulesPanel() {
        return inGameRulesPanel1;
    }
}
