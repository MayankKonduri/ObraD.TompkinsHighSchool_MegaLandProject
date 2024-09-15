package Project.src;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.Cleaner;

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
        waitingLabel.setFont(new Font("Georgia", Font.BOLD,20));
        add(waitingLabel);
    }

    public void switchToCharacterSelectPanel() {

        characterSelectPanel = new CharacterSelectPanel(jframe1, clientMain, null, null, connectPanel,false)
        jframe1.setContentPane(characterSelectPanel);
        jframe1.revalidate();
    }

    public CharacterSelectPanel getCharacterSelectPanel(){
        return characterSelectPanel;
    }
}
