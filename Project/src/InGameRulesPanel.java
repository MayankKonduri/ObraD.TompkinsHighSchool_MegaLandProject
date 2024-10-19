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
        g.setColor(new Color(230, 230, 230));
        g.fillRect(0, 0, getWidth(), getHeight());

        if (pageCount == 0) {
            g.drawImage(rule1, 0, 0, 1920, 1010, null);
            previousPage.setVisible(false);
        } else if (pageCount == 1) {
            g.drawImage(rule2, 0, 0, 960, 1010, null);
            g.drawImage(rule3, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        } else if (pageCount == 2) {
            g.drawImage(rule4, 0, 0, 960, 1010, null);
            g.drawImage(rule5, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        } else if (pageCount == 3) {
            g.drawImage(rule6, 0, 0, 960, 1010, null);
            g.drawImage(rule7, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        } else if (pageCount == 4) {
            g.drawImage(rule8, 0, 0, 960, 1010, null);
            g.drawImage(rule9, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        } else if (pageCount == 5) {
            g.drawImage(rule10, 0, 0, 960, 1010, null);
            g.drawImage(rule11, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(true);
        } else if (pageCount == 6) {
            g.drawImage(rule12, 0, 0, 960, 1010, null);
            g.drawImage(ruleEnd, 960, 0, 960, 1010, null);
            previousPage.setVisible(true);
            nextPage.setVisible(false);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Page " + (pageCount + 1) + " of 7", 900, 30);

        int progressBarWidth = 800;
        int progressBarHeight = 20;
        int progressX = (getWidth() - progressBarWidth) / 2;
        int progressY = getHeight() - 50;
        int progress = (int) ((pageCount / 6.0) * progressBarWidth);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(progressX, progressY, progressBarWidth, progressBarHeight);

        g.setColor(new Color(76, 175, 80));
        g.fillRect(progressX, progressY, progress, progressBarHeight);

        g.setColor(Color.BLACK);
        g.drawRect(progressX, progressY, progressBarWidth, progressBarHeight);

        if (nextPage.getBounds().contains(getMousePosition())) {
            drawTooltip(g, "Next Page", nextPage.getX() - 10, nextPage.getY() - 10);
        }
        if (previousPage.getBounds().contains(getMousePosition())) {
            drawTooltip(g, "Previous Page", previousPage.getX() - 10, previousPage.getY() - 10);
        }
        if (homeButton.getBounds().contains(getMousePosition())) {
            drawTooltip(g, "Return to Game", homeButton.getX() - 10, homeButton.getY() - 10);
        }

        g.setColor(Color.DARK_GRAY);
        g.drawRect(0, 0, 960, 1010);
        g.drawRect(960, 0, 960, 1010);

        addFadingEffect(g, pageCount);

        private void drawTooltip(Graphics g, String text, int x, int y) {
            FontMetrics metrics = g.getFontMetrics();
            int width = metrics.stringWidth(text) + 10;
            int height = metrics.getHeight() + 4;

            g.setColor(new Color(50, 50, 50, 180));
            g.fillRect(x, y - height, width, height);

            g.setColor(Color.WHITE);
            g.drawString(text, x + 5, y - 4);
        }

        private void addFadingEffect(Graphics g, int pageCount) {
            Graphics2D g2d = (Graphics2D) g;
            float alpha = 0.1f + (pageCount / 6.0f) * 0.9f;
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);

            g2d.setColor(new Color(200, 200, 200, 100));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        }
    }
}
