package Project.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class LoadingPanel extends JPanel {
    private JButton hostButton = new JButton("Host");
    private JButton connectButton = new JButton("Connect");
    private BufferedImage loading;
    private BufferedImage buffer;


    public LoadingPanel(JFrame frame) {
        setSize(1920, 1040);
        setLayout(null);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\megaland_banner_1.png")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        hostButton.setBounds(400, 550, 225, 75);
        connectButton.setBounds(400, 650, 225, 75);

        hostButton.setOpaque(true);
        connectButton.setOpaque(false);

        hostButton.setFont(new Font("Georgia", Font.BOLD, 30));
        connectButton.setFont(new Font("Georgia", Font.BOLD, 30));

        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new HostPanel(frame));
                frame.revalidate();
            }
        });

        add(hostButton);
        add(connectButton);
        setVisible(true);
    }

//    public void paint(Graphics g) {
//        Graphics bg = buffer.getGraphics();
//        bg.setColor(Color.red);
//        bg.drawImage(loading, 0, 0, 1920, 1040, null);
//        g.drawImage(buffer, 0, 0, null);
//    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1040, null);
//        g.drawImage(buffer, 0, 0, null);
    }




}