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

    public GamePanel(JFrame frame) {
        setSize(1920, 1010);
        setLayout(null);

        WelcomeLabel.setBounds(400,400,1000,400);
        add(WelcomeLabel);
    }
}
