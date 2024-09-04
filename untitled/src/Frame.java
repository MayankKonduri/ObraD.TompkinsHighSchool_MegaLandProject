package untitled.src;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;

public class Frame extends JFrame {

    ObjectOutputStream os;
    LoadingPanel l = null;
    public Frame() {
        super("MegaLand");


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setAlwaysOnTop(true);
        setVisible(true);
        setSize(1920, 1040);
        l = new LoadingPanel();
        add(l);

    }



}
