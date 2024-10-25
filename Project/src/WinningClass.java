package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;


import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class WinningClass extends JPanel {

    public JFrame jFrame1;
    private Boolean win;

    public WinningClass(JFrame jFrame, Boolean win){
        jFrame1 = jFrame;
        this.win = win;

        if(win){
            JLabel winLabel = new JLabel("You Win!");

            winLabel.setFont(new Font("Georgia",Font.BOLD,60));
            winLabel.setHorizontalAlignment(SwingConstants.CENTER);

            setLayout(new BorderLayout());
            add(winLabel, BorderLayout.CENTER);
        }
        else{
            JLabel winLabel = new JLabel("You Lose!");

            winLabel.setFont(new Font("Georgia",Font.BOLD,60));
            winLabel.setHorizontalAlignment(SwingConstants.CENTER);

            setLayout(new BorderLayout());
            add(winLabel, BorderLayout.CENTER);
        }
    }

}
