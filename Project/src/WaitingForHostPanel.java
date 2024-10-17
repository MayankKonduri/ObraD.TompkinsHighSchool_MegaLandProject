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
    private Player playerObject;

    public WaitingForHostPanel(JFrame frame, ClientMain clientMain, ConnectPanel connectPanel, Player player){
        setLayout(null);

        this.clientMain = clientMain;
        this.jframe1 = frame;
        this.connectPanel = connectPanel;
        this.playerObject = player;

        try {
            String basePath = "Project" + File.separator + "src" + File.separator + "Images" + File.separator;
            loading = ImageIO.read(new File(basePath + "Waiting_v2.jpg"));

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
}
