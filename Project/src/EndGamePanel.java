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
    int coins, buildings, hearts;
    boolean didWin;

    JLabel message;

    public EndGamePanel(JFrame frame, int coins, int buildings, int hearts){

        this.coins = coins;
        this.buildings = buildings;
        this.hearts = hearts;

        if(coins>=20){
            didWin = true;
            message = new JLabel("You Won!!!");
        }
        else{
            didWin = false;
            message = new JLabel("You Lost...");
        }



        setSize(1920, 1040);
        setLayout(null);
        this.jFrame = frame;


        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\Loading_v7.jpg")));

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

            message.setFont(Font.getFont(Font.SANS_SERIF));
            message.setBounds(100,100,300,100);
    }




}