package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class CharacterSelectPanel extends JPanel {
    private BufferedImage indianWoman, gandalf, cat, frog, white;
    private JLabel title = new JLabel ("Choose Your Characters Wisely!");
    private JButton gandalfB = new JButton ("Select");
    private JButton whiteB = new JButton ("Select");
    private JButton indianWomanB = new JButton ("Select");
    private JButton catB = new JButton ("Select");
    private JButton frogB = new JButton ("Select");
    public CharacterSelectPanel(JFrame frame) {
        setSize(1920, 1010);
        setLayout(null);


        try {
            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(610, 50, 700,75);
        title.setFont(new Font("Times New Roman", Font.BOLD, 50));


        catB.setBounds(245, 600, 180, 30);
        indianWomanB.setBounds(605, 600, 180, 30);
        whiteB.setBounds(965, 600, 180, 30);
        frogB.setBounds(1325, 600, 180, 30);
        gandalfB.setBounds(1685, 600, 180, 30);




    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(cat, 65, 250, 360, 540, null);
        g.drawImage(indianWoman, 425, 250, 360, 540, null);
        g.drawImage(white, 785, 250, 360, 540, null);
        g.drawImage(frog, 1325, 250, 360, 540, null);
        g.drawImage(gandalf, 1505, 250, 360, 540, null);


        add(title);
        add(gandalfB);
        add(indianWomanB);
        add(frogB);
        add(whiteB);
        add(catB);
        setVisible(true);
    }



}
