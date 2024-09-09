package Project.src;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CardSelectPanel extends JPanel {
    private BufferedImage image12, image13, image14, image15, image16, image17, image18, image19, image20, image21, image22, image23, image24, image25, image26, image27, image28, image29;
    private JLabel title = new JLabel ("Please select 7 cards.");
    private JButton select12 = new JButton("Select");
    private JButton select13 = new JButton("Select");
    private JButton select14 = new JButton("Select");
    private JButton select15 = new JButton("Select");
    private JButton select16 = new JButton("Select");
    private JButton select17 = new JButton("Select");
    private JButton select18 = new JButton("Select");
    private JButton select19 = new JButton("Select");
    private JButton select20 = new JButton("Select");
    private JButton select21 = new JButton("Select");
    private JButton select22 = new JButton("Select");
    private JButton select23 = new JButton("Select");
    private JButton select24 = new JButton("Select");
    private JButton select25 = new JButton("Select");
    private JButton select26 = new JButton("Select");
    private JButton select27 = new JButton("Select");
    private JButton select28 = new JButton("Select");
    private JButton select29 = new JButton("Select");
    private int selectionCount = 0;

    private JButton done = new JButton ("Done");

    public CardSelectPanel(JFrame frame) {
        setSize(1920, 1010);
        setLayout(null);

        try {
            image12 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0012.jpg")));
            image13 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0013.jpg")));
            image14 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0014.jpg")));
            image15 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0015.jpg")));
            image16 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0016.jpg")));
            image17 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0017.jpg")));
            image18 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0018.jpg")));
            image19 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0019.jpg")));
            image20 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0020.jpg")));
            image21 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0021.jpg")));
            image22 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0022.jpg")));
            image23 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0023.jpg")));
            image24 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0024.jpg")));
            image25 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0025.jpg")));
            image26 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0026.jpg")));
            image27 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0027.jpg")));
            image28 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            image29 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0029.jpg")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(710, 0, 500,75);
        title.setFont(new Font("Georgia", Font.BOLD, 40));
        done.setBounds(1800, 900, 100, 100);
        done.addActionListener(e -> {
            frame.setContentPane(new CharacterSelectPanel(frame));
            frame.revalidate();
        });

        select12.setBounds(60, 380, 180, 30);
        select12.addActionListener(e -> {
            int press12 = 0;
            if(select12.getText().equals("Select")) {
                select12.setText("Selected");
                selectionCount++;
                System.out.println("Selected: " + selectionCount);
            } else {
                select12.setText("Select");
                selectionCount--;
                System.out.println("Unselected: " + selectionCount);
            }

//            select12.setText("Selected");
        });
        select13.setBounds(260, 380, 180, 30);
        select14.setBounds(460, 380, 180, 30);
        select15.setBounds(660, 380, 180, 30);
        select16.setBounds(860, 380, 180, 30);
        select17.setBounds(1060, 380, 180, 30);
        select18.setBounds(1260, 380, 180, 30);
        select19.setBounds(1460, 380, 180, 30);
        select20.setBounds(1660, 380, 180, 30);

        select21.setBounds(160, 720, 180, 30);
        select22.setBounds(360, 720, 180, 30);
        select23.setBounds(560, 720, 180, 30);
        select24.setBounds(760, 720, 180, 30);

        select26.setBounds(960, 720, 180, 30);
        select27.setBounds(1160, 720, 180, 30);
        select28.setBounds(1360, 720, 180, 30);
        select29.setBounds(1560, 720, 180, 30);



        add(select12);
        add(select13);
        add(select14);
        add(select15);
        add(select16);
        add(select17);
        add(select18);
        add(select19);
        add(select20);
        add(select21);
        add(select22);
        add(select23);
        add(select24);
        add(select25);
        add(select26);
        add(select27);
        add(select28);
        add(select29);

        add(title);
        add(done);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image12, 60, 90, 180, 270, null);
        g.drawImage(image13, 260, 90, 180, 270, null);
        g.drawImage(image14, 460, 90, 180, 270, null);
        g.drawImage(image15, 660, 90, 180, 270, null);
        g.drawImage(image16, 860, 90, 180, 270, null);
        g.drawImage(image17, 1060, 90, 180, 270, null);
        g.drawImage(image18, 1260, 90, 180, 270, null);
        g.drawImage(image19, 1460, 90, 180, 270, null);
        g.drawImage(image20, 1660, 90, 180, 270, null);
        //new line
        g.drawImage(image21, 160, 430, 180, 270, null);
        g.drawImage(image22, 360, 430, 180, 270, null);
        g.drawImage(image23, 560, 430, 180, 270, null);
        g.drawImage(image24, 760, 430, 180, 270, null);

        g.drawImage(image26, 960, 430, 180, 270, null);
        g.drawImage(image27, 1160, 430, 180, 270, null);
        g.drawImage(image28, 1360, 430, 180, 270, null);
        g.drawImage(image29, 1560, 430, 180, 270, null);







    }
}
