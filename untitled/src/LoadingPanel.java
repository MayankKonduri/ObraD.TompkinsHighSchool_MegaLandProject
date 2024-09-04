package untitled.src;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.Collections;


public class LoadingPanel extends JPanel {
    JLabel megalandName = new JLabel("Megaland");
    private BufferedImage loading;
    private BufferedImage buffer;



    public LoadingPanel() {
        setSize(1920, 1040);
        setLayout(null);

        megalandName.setBounds(100, 100, 100, 100);
        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        try {
            loading = ImageIO.read((new File("src\\images\\megaland_banner.jpg")));

        }catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }

        add(megalandName);
        setVisible(true);
    }
}