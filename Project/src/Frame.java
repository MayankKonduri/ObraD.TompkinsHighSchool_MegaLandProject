package Project.src;
import javax.swing.*;
import java.awt.*;
import java.io.ObjectOutputStream;

public class Frame extends JFrame {

    ObjectOutputStream os;
    public Frame() {
        super("MegaLand");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        pack();

        LoadingPanel l = new LoadingPanel(this, os);
        setPreferredSize(new Dimension(l.getWidth(), l.getHeight()));

        setLayout(null);

        add(l);
        pack();

        setVisible(true);
    }




}
