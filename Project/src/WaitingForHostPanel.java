package Project.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.ref.Cleaner;
import java.util.ArrayList;

public class WaitingForHostPanel extends JPanel {

    private JFrame jframe1;
    private ClientMain clientMain;
    private ConnectPanel connectPanel;
    private CharacterSelectPanel characterSelectPanel;
    public WaitingForHostPanel(JFrame frame, ClientMain clientMain, ConnectPanel connectPanel){
        setLayout(null);

        this.clientMain = clientMain;
        this.jframe1 = frame;
        this.connectPanel = connectPanel;

        clientMain.setWaitingForHostPanel(this);
        JLabel waitingLabel = new JLabel("Waiting for Host to Finish Card Selection");
        waitingLabel.setBounds(460,480,1000,50);
        waitingLabel.setFont(new Font("Georgia", Font.BOLD,40));
        add(waitingLabel);

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
}
