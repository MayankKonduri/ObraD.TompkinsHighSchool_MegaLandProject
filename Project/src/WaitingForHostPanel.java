package Project.src;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.Cleaner;

public class WaitingForHostPanel extends JPanel {

    private JFrame jframe1;
    private ClientMain clientMain;
    public WaitingForHostPanel(JFrame frame, ClientMain clientMain){
        setLayout(null);
        this.clientMain = clientMain;
        this.jframe1 = frame;
        clientMain.setWaitingForHostPanel(this);
        JLabel waitingLabel = new JLabel("Waiting for Host to Finish Card Selection");
        waitingLabel.setFont(new Font("Georgia", Font.BOLD,20));
        add(waitingLabel);
    }

    public void switchToCharacterSelectPanel() {

        jframe1.setContentPane(new CharacterSelectPanel(jframe1, clientMain, false));
        jframe1.revalidate();
    }
}
