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
    private JLabel title = new JLabel ("Please select 7 cards.");
    public GamePanel(JFrame frame) {
        setSize(1920, 1010);
        setLayout(null);


        title.setBounds(700, 1, 1920, 1010);
        title.setText("Welcome to the Game!");
        title.setFont(new Font("Georgia", Font.BOLD, 40));

        add(title);
        setVisible(true);
    }
}
