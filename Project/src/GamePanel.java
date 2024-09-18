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
    public ArrayList<BufferedImage> buildingNames = new ArrayList<>();



    //missing one\
    //skip 25 its a repeat
    private BufferedImage personalCard, heartCard, starCardBackground,
            sandwichStand, cafe, arcade, bazaarOfOddities, hotel, templeOfZoz,
            buildingCardBackground, reptileStable, herbHut, ostrichRanch, gym, hospital, laboratory, fishingPond, bowlingAlley, smithy, fishVendor, tollBooth,
            soapMakers, hallOfElders, lodge, rootMarket, endlessMine, arena,
            backOfLevelCard, levelCard31, levelCard32, levelCard33, levelCard34, levelCard35, levelCard36, levelCard37, levelCard38, levelCard39, levelCard40,
            treasureCardBackground, gear, cube, egg, carrot, mineral, fish,
            coin1, coin5, coin10, firstPlayerToken, heart, jump, indianWoman, gandalf, cat, frog, white, playerLevelCard;


    public GamePanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost) {
        setSize(1920, 1010);
        setLayout(null);
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

        WelcomeLabel.setBounds(400,400,1000,400);
        add(WelcomeLabel);

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
            System.out.println("CardSelectedList (C): " + cardSelectedList_g_client);
        }

//        System.out.println("List of Selected Buildings: " + cardSelectPanel.buildingsSelect);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isHost1) {
            int j = 0;
            for(int i = 0; i < cardSelectPanel.buildingsSelect.size(); i++) {
                boolean selected = false;
                if(cardSelectPanel.buildingsSelect.get(i) == true) {
                    selected = true;
                    g.drawImage(buildingNames.get(i), 40+ (j*140), 40, 120, 200, null);

                }
                if(selected) {
                    j++;
                }
            }
        } else {
            int x = 0;
            for(int i = 0; i < cardSelectedList_g_client.size(); i++) {
                boolean selected = false;
                if(cardSelectedList_g_client.get(i) == true) {
                    selected = true;
                    g.drawImage(buildingNames.get(i), 40+ (x*140), 40, 120, 180, null);
                }
                if(selected) {
                    x++;
                }
            }


        }

    }


}
