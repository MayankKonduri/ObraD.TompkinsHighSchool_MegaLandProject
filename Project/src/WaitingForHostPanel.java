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
    public ArrayList<String> waitingTips = new ArrayList<>();
    public WaitingForHostPanel(JFrame frame, ClientMain clientMain, ConnectPanel connectPanel){
        setLayout(null);

        this.clientMain = clientMain;
        this.jframe1 = frame;
        this.connectPanel = connectPanel;
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

    public void switchToCharacterSelectPanel() {

        characterSelectPanel = new CharacterSelectPanel(jframe1, clientMain, null, null, connectPanel,false, null);
        connectPanel.setCharacterSelectPanel(characterSelectPanel);
        jframe1.setContentPane(characterSelectPanel);
        jframe1.revalidate();
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
}
