package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class ConnectPanel extends JPanel{
    private BufferedImage loading;
    private JLabel hostLabel = new JLabel("Connect");
    private JLabel ipLabel = new JLabel("IP Address: ");
    private JTextArea playersJoined = new JTextArea("Players Joined:\n");

    public ConnectPanel(JFrame frame) {
        setSize(1920, 1040);
        setLayout(null);

        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\megaland_banner_1.png")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }

        hostLabel.setBounds(400, 450, 400, 75);
        hostLabel.setFont(new Font("Oswald", Font.BOLD, 30));
        add(hostLabel);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1040, null);
//        g.drawImage(buffer, 0, 0, null);
    }


}
