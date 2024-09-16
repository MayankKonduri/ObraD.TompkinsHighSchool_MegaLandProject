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
    private Boolean isHost;

    public GamePanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost) {
        setSize(1920, 1010);
        setLayout(null);

        this.jFrame = frame;
        this.clientMain = clientMain;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;
        this.connectPanel = connectPanel;
        this.cardSelectPanel = cardSelectPanel;
        this.characterSelectPanel = characterSelectPanel;
        this.isHost = isHost;


        WelcomeLabel.setBounds(400,400,1000,400);
        add(WelcomeLabel);

        if(isHost) {
            System.out.println("Connected Players: " + serverMain.gamePlayerNames);
        }else {
            System.out.println("Connected Players: " + clientMain.gamePlayerNames_ClientSide);
        }
    }
}
