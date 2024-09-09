package Project.src;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;


public class CardSelectPanel extends JPanel {
    private BufferedImage image12, image13, image14, image15, image16, image17, image18, image19, image20, image21, image22, image23, image24, image25, image26, image27, image28, image29;
    private JLabel title = new JLabel ("Please select 7 cards.");
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

        add(title);
        add(done);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image12, 50, 80, 180, 270, null);
        g.drawImage(image13, 300, 80, 200, 300, null);
        g.drawImage(image14, 550, 80, 200, 300, null);
        g.drawImage(image15, 800, 80, 180, 270, null);
        g.drawImage(image16, 1050, 80, 200, 300, null);
        g.drawImage(image17, 1300, 80, 200, 300, null);
        g.drawImage(image18, 1550, 80, 200, 300, null);
        g.drawImage(image19, 50, 430, 180, 270, null);
        g.drawImage(image20, 50, 780, 180, 270, null);






    }
}
