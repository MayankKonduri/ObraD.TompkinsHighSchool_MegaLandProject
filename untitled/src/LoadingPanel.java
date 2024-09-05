package untitled.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;


public class LoadingPanel extends JPanel {
    JLabel name = new JLabel("Enter your name: ");
    JTextField nameField = new JTextField();
    private BufferedImage loading;
    private BufferedImage buffer;


    public LoadingPanel() {
        setSize(1920, 1040);
        setLayout(null);

        name.setBounds(610, 490, 200, 30);
        nameField.setBounds(810, 490, 500, 30);
//        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
//        try {
//            loading = ImageIO.read((new File("src\\images\\megaland_banner.jpg")));
//
//        } catch (Exception ah) {
//            ah.printStackTrace();
//            System.out.println("Error Loading Images: " + ah.getMessage());
//        }

        add(name);
        add(nameField);
        setVisible(true);
    }

//    public void paint(Graphics g) {
//        Graphics bg = buffer.getGraphics();
//        bg.setColor(Color.orange);
//        bg.drawImage(loading, 200, 200, 200, 200, null);
//        g.drawImage(buffer, 0, 0, null);
//    }


}