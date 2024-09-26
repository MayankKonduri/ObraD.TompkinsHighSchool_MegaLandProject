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


public class LoadingPanel extends JPanel {
    private JButton hostButton = new JButton("Host Game");
    private JButton connectButton = new JButton("Connect to Game");
    private JButton rulesButton = new JButton("View Rules");
    private BufferedImage loading;
    private BufferedImage buffer;
    private JLabel welcome = new JLabel("Welcome to Megaland!");


    public LoadingPanel(JFrame frame) {
        setSize(1920, 1040);
        setLayout(null);

        buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB_PRE);
        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\Loading_v7.jpg")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        hostButton.setBounds(400, 550, 500, 75);
        connectButton.setBounds(400, 650, 500, 75);
        rulesButton.setBounds(400, 750, 500, 75);
        welcome.setBounds(300, 390, 750, 90);
        welcome.setFont(new Font("Georgia", Font.BOLD, 60));
        welcome.setForeground(Color.BLACK);


        //225
//
//        hostButton.setOpaque(false);
//        connectButton.setOpaque(false);
//        rulesButton.setOpaque(false);

        hostButton.setFocusPainted(false);
        connectButton.setFocusPainted(false);
        rulesButton.setFocusPainted(false);

        hostButton.setBackground(Color.black);
        hostButton.setForeground(Color.white);
        connectButton.setBackground(Color.black);
        connectButton.setForeground(Color.WHITE);
        rulesButton.setBackground(Color.black);
        rulesButton.setForeground(Color.WHITE);
        hostButton.setBorder(BorderFactory.createEmptyBorder());
        connectButton.setBorder(BorderFactory.createEmptyBorder());
        rulesButton.setBorder(BorderFactory.createEmptyBorder());
        hostButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hostButton.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                hostButton.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                connectButton.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                connectButton.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        rulesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                rulesButton.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rulesButton.setBorder(BorderFactory.createEmptyBorder());
            }
        });

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
        add(welcome);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1050, null);
//        g.drawImage(buffer, 0, 0, null);
    }




}