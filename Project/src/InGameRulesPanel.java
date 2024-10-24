package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class InGameRulesPanel extends JPanel{
    private BufferedImage rule1, rule2, rule3, rule4, rule5, rule6, rule7, rule8, rule9, rule10, rule11, rule12, ruleEnd;
    private JButton nextPage = new JButton(">");
    private JButton previousPage = new JButton("<");
    private JButton homeButton = new JButton("Game");
    public int pageCount1 = 0;
    public int pageCount = 0;
    private GamePanel gamePanel;
    private boolean isRulesVisible;
    public JLabel ruleInstructions = new JLabel("Press the \">\" to flip.");





    public InGameRulesPanel(JFrame frame, GamePanel gamePanel) {
        setSize(1920, 1010);
        setLayout(null);
        this.gamePanel = gamePanel;

        try {
            rule1 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-01.png")));
            rule2 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-02.png")));
            rule3 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-03.png")));
            rule4 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-04.png")));
            rule5 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-05.png")));
            rule6 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-06.png")));
            rule7 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-07.png")));
            rule8 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-08.png")));
            rule9 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-09.png")));
            rule10 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-10.png")));
            rule11 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-11.png")));
            rule12 = ImageIO.read((new File("Project\\src\\Images\\megaland+rulebook_ver+03-12.png")));
            ruleEnd = ImageIO.read((new File("Project\\src\\Images\\EndRules.png")));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        nextPage.setBounds(1850, 480, 70, 80);
        nextPage.setFont(new Font("Georgia", Font.BOLD, 40));
        nextPage.setOpaque(false);
        nextPage.setBackground(Color.black);
        nextPage.setFocusPainted(false);
        nextPage.setBorderPainted(false);
        nextPage.setContentAreaFilled(false);

        previousPage.setBounds(10, 480, 70, 80);
        previousPage.setOpaque(false);
        previousPage.setBackground(Color.black);
        previousPage.setFocusPainted(false);
        previousPage.setBorderPainted(false);
        previousPage.setContentAreaFilled(false);
        previousPage.setFont(new Font("Georgia", Font.BOLD, 40));

        homeButton.setBounds(10, 10, 100, 30);

        nextPage.addActionListener(e -> {
            pageCount++;
            System.out.println(pageCount);
            repaint();

        });
        previousPage.addActionListener(e -> {
            pageCount--;
            repaint();
        });

        homeButton.addActionListener(e -> {
            toggleGame();
        });

        homeButton.setBounds(10, 10, 100, 40);
        homeButton.setFont(new Font("Georgia", Font.BOLD, 20));


        homeButton.setFocusPainted(false);
        homeButton.setBackground(Color.black);
        homeButton.setForeground(Color.white);
        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                homeButton.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                homeButton.setBorder(null);
            }
        });

        add(nextPage);
        add(previousPage);
        add(homeButton);
        setVisible(true);
    }
    void openRules() {
        isRulesVisible = true;
        setVisible(true);
    }
    void closeRules() {
        isRulesVisible = false;
        setVisible(false);
    }
    public void toggleGame() {
        setVisible(false);
        gamePanel.openGame();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(pageCount == 0) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 960, 1010);
            ruleInstructions.setBounds(150, 465, 960, 80);
            ruleInstructions.setFont(new Font("Georgia", Font.BOLD, 60));
            ruleInstructions.setForeground(Color.WHITE);
            add(ruleInstructions);
            g.drawImage(rule1, 960, 0, 960, 1010, null);
            previousPage.setVisible(false);
            homeButton.setBounds(970, 10, 100, 40);

        } else if(pageCount == 1) {
            ruleInstructions.setVisible(false);
            g.drawImage(rule2, 0, 0, 960, 1010, null);
            g.drawImage(rule3, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
            homeButton.setBounds(10, 10, 100, 40);

        }else if(pageCount == 2){
            g.drawImage(rule4, 0, 0, 960, 1010, null);
            g.drawImage(rule5, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        }else if(pageCount == 3){
            g.drawImage(rule6, 0, 0, 960, 1010, null);
            g.drawImage(rule7, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        }else if(pageCount == 4){
            g.drawImage(rule8, 0, 0, 960, 1010, null);
            g.drawImage(rule9, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        }else if(pageCount == 5){
            g.drawImage(rule10, 0, 0, 960, 1010, null);
            g.drawImage(rule11, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
            ruleInstructions.setVisible(false);
        }else if(pageCount == 6){
            ruleInstructions.setVisible(true);
            g.drawImage(rule12, 0, 0, 960, 1010, null);
            g.setColor(Color.BLACK);
            g.fillRect(960, 0, 960, 1010);
            previousPage.setVisible(true);
            nextPage.setVisible(false);
            ruleInstructions.setBounds(970+ 150, 465, 960, 80);
            ruleInstructions.setFont(new Font("Georgia", Font.BOLD, 60));
            ruleInstructions.setText("End of the Rulebook.");
        }
    }
}
