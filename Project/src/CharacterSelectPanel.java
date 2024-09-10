package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.*;
import java.util.ArrayList;

public class CharacterSelectPanel extends JPanel {
    private BufferedImage indianWoman, gandalf, cat, frog, white;
    private JLabel title = new JLabel ("Choose Your Characters Wisely!");
    private JLabel selected = new JLabel ("");
    private JButton gandalfB = new JButton ("Available");
    private JButton whiteB = new JButton ("Available");
    private JButton indianWomanB = new JButton ("Available");
    private JButton catB = new JButton ("Available");
    private JButton frogB = new JButton ("Available");
    private ArrayList<Boolean> available= new ArrayList<Boolean>();
    public CharacterSelectPanel(JFrame frame) {
        setSize(1920, 1010);
        setLayout(null);

        for(int i=0;i<=4;i++){
            available.add(false);
        }


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
        title.setFont(new Font("Georgia", Font.BOLD, 50));



        catB.setBounds(145, 800, 180, 30);
        indianWomanB.setBounds(510, 800, 180, 30);
        whiteB.setBounds(875, 800, 180, 30);
        frogB.setBounds(1235, 800, 180, 30);
        gandalfB.setBounds(1600, 800, 180, 30);
        selected.setBounds(680, 900, 700, 70);
        selected.setFont(new Font("Georgia", Font.BOLD, 40));


        catB.addActionListener(e -> {
            if(catB.getText().equals("Available")) {
                reset(catB);
                catB.setText("Selected");
                catB.setBackground(Color.BLACK);
                catB.setForeground(Color.WHITE);
                available.set(0,true);

                selected.setText("Your Character is Cat");
                System.out.println("Selected: Cat");
            } else {
                catB.setText("Available");
                System.out.println("Unselected: catB");
                catB.setBackground(null);
                catB.setForeground(null);
                available.set(0,false);
            }
            System.out.println(available.toString());
        });
        indianWomanB.addActionListener(e -> {
            if(indianWomanB.getText().equals("Available")) {
                reset(indianWomanB);
                indianWomanB.setText("Selected");
                indianWomanB.setBackground(Color.BLACK);
                indianWomanB.setForeground(Color.WHITE);
                available.set(1,true);
                selected.setText("Your Character is Woman");

                System.out.println("Selected: frogB");
            } else {
                indianWomanB.setText("Available");
                System.out.println("Unselected: indianWomanB");
                indianWomanB.setBackground(null);
                indianWomanB.setForeground(null);
                available.set(1,false);
            }
            System.out.println(available.toString());
        });
        whiteB.addActionListener(e -> {
            if(whiteB.getText().equals("Available")) {
                reset(whiteB);
                whiteB.setText("Selected");
                whiteB.setBackground(Color.BLACK);
                whiteB.setForeground(Color.WHITE);
                available.set(2,true);
                selected.setText("Your Character is White Boy");


                System.out.println("Selected: whiteB");
            } else {
                whiteB.setText("Available");
                System.out.println("Unselected: whiteB");
                whiteB.setBackground(null);
                whiteB.setForeground(null);
                available.set(2,false);
            }
            System.out.println(available.toString());
        });
        frogB.addActionListener(e -> {
            if(frogB.getText().equals("Available")) {
                reset(frogB);
                frogB.setText("Selected");
                frogB.setBackground(Color.BLACK);
                frogB.setForeground(Color.WHITE);
                available.set(3,true);
                selected.setText("Your Character is Froggy");

                System.out.println("Selected: frogB");
            } else {
                frogB.setText("Available");
                System.out.println("Unselected: frogB");
                frogB.setBackground(null);
                frogB.setForeground(null);
                available.set(3,false);
            }
            System.out.println(available.toString());
        });
        gandalfB.addActionListener(e -> {
            if(gandalfB.getText().equals("Available")) {
                reset(gandalfB);
                gandalfB.setText("Selected");
                gandalfB.setBackground(Color.BLACK);
                gandalfB.setForeground(Color.WHITE);
                available.set(3,true);
                selected.setText("Your Character is Gandalf");

                System.out.println("Selected: gandalfB");
            } else {
                gandalfB.setText("Available");
                System.out.println("Unselected: gandalfB");
                gandalfB.setBackground(null);
                gandalfB.setForeground(null);
                available.set(4,false);
            }
            System.out.println(available.toString());
        });


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(cat, 65, 250, 360, 540, null);
        g.drawImage(indianWoman, 425, 250, 360, 540, null);
        g.drawImage(white, 785, 250, 360, 540, null);
        g.drawImage(frog, 1145, 250, 360, 540, null);
        g.drawImage(gandalf, 1505, 250, 360, 540, null);


        add(title);
        add(selected);
        add(gandalfB);
        add(indianWomanB);
        add(frogB);
        add(whiteB);
        add(catB);
        setVisible(true);
    }

    public void reset(JButton button) {
        available.clear();
        for(int i = 0; i <5; i++) {
            available.add(false);
        }
        gandalfB.setText("Available");
        indianWomanB.setText("Available");
        frogB.setText("Available");
        whiteB.setText("Available");
        catB.setText("Available");
        catB.setBackground(null);
        gandalfB.setForeground(null);
        gandalfB.setBackground(null);
        indianWomanB.setForeground(null);
        indianWomanB.setBackground(null);
        whiteB.setForeground(null);
        whiteB.setBackground(null);
        frogB.setForeground(null);
        frogB.setBackground(null);
        catB.setForeground(null);


    }



}
