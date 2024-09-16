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
    //missing one\
    //skip 25 its a repeat
    private BufferedImage playerCard, heartCard, starCardBackground,
            sandwichStand, cafe, arcade, bazaarOfOddities, hotel,
            buildingCardBackground, reptileStable, herbHut, ostrichRanch, gym, hospital, laboratory, fishingPond, bowlingAlley, smithy, fishVendor, tollBooth,
            soapMakers, hallOfElders, lodge, rootMarket, endlessMine, arena,
            backOfLevelCard, levelCard31, levelCard32, levelCard33, levelCard34, levelCard35, levelCard36, levelCard37, levelCard38, levelCard39, levelCard40,
            treasureCardBackground, gear, cube, egg, carrot, mineral, fish,
            coin1, coin5, coin10, firstPlayerToken, heart, jump, indianWoman, gandalf, cat, frog, white, playerLevelCard;


    public GamePanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost) {
        setSize(1920, 1010);
        setLayout(null);
        try {
            playerLevelCard = ImageIO.read((new File("Project\\src\\Images\\MegaLand_PlayerCard.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        }else {
            System.out.println("Connected Players (C): " + clientMain.gamePlayerNames_ClientSide);
        }

//        System.out.println("List of Selected Buildings: " + cardSelectPanel.buildingsSelect);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
//        if(cardSelectPanel.buildingsSelect.get(0) == true) {
//            g.drawImage(playerLevelCard, 0, 0, 1920, 1010, null);
//
//        }
//        g.drawImage(buffer, 0, 0, null);
    }


}
