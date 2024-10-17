package Project.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;


public class EndGamePanel extends JPanel {

    private BufferedImage loading;
    private JFrame jFrame;


    public EndGamePanel(JFrame frame) {

        setSize(1920, 1040);
        setLayout(null);
        this.jFrame = frame;


        try {
            loading = ImageIO.read(new File("Project" + File.separator + "src" + File.separator + "Images" + File.separator + "Loading_v7.jpg"));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }

        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1050, null);
//        g.drawImage(buffer, 0, 0, null);
    }




}