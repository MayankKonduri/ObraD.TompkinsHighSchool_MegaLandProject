package Project.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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




    public InGameRulesPanel(JFrame frame, GamePanel gamePanel) {
        setSize(1920, 1010);
        setLayout(null);
        this.gamePanel = gamePanel;

        try {
            String basePath = "Project" + File.separator + "src" + File.separator + "Images" + File.separator;

            rule1 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-01.png"));
            rule2 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-02.png"));
            rule3 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-03.png"));
            rule4 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-04.png"));
            rule5 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-05.png"));
            rule6 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-06.png"));
            rule7 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-07.png"));
            rule8 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-08.png"));
            rule9 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-09.png"));
            rule10 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-10.png"));
            rule11 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-11.png"));
            rule12 = ImageIO.read(new File(basePath + "megaland+rulebook_ver+03-12.png"));
            ruleEnd = ImageIO.read(new File(basePath + "EndRules.png"));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        nextPage.setBounds(1845, 480, 50, 80);
        nextPage.setOpaque(true);

        previousPage.setBounds(10, 480, 50, 80);
        previousPage.setOpaque(true);

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
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));

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
            g.drawImage(rule1, 0, 0, 1920, 1010, null);
            previousPage.setVisible(false);
        } else if(pageCount == 1) {
            g.drawImage(rule2, 0, 0, 960, 1010, null);
            g.drawImage(rule3, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
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
        }else if(pageCount == 6){
            g.drawImage(rule12, 0, 0, 960, 1010, null);
            g.drawImage(ruleEnd, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(false);
        }
    }
}
