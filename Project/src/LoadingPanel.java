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
    private JButton rulesButton = new JButton("Rules");
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
        rulesButton.setBounds(400, 750, 225, 75);

        hostButton.setOpaque(false);
        connectButton.setOpaque(false);
        rulesButton.setOpaque(false);

        hostButton.setFont(new Font("Georgia", Font.BOLD, 30));
        connectButton.setFont(new Font("Georgia", Font.BOLD, 30));
        rulesButton.setFont(new Font("Georgia", Font.BOLD, 30));


        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new HostPanel(frame));
                frame.revalidate();
            }
        });
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new ConnectPanel(frame));
                frame.revalidate();
            }
        });
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new RulesPanel(frame));
                frame.revalidate();
            }
        });

        add(hostButton);
        add(connectButton);
        add(rulesButton);
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
        g.drawImage(loading, 0, 0, 1920, 1050, null);
//        g.drawImage(buffer, 0, 0, null);
    }




}