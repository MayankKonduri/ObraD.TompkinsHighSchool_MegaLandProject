package Project.src;

import javax.swing.*;
import java.awt.*;

public class WaitingForHostPanel extends JPanel {
    public WaitingForHostPanel(JFrame frame){
        setLayout(null);

        JLabel waitingLabel = new JLabel("Waiting for Host to Finish Card Selection");
        waitingLabel.setFont(new Font("Georgia", Font.BOLD,20));
        add(waitingLabel);
    }
}
