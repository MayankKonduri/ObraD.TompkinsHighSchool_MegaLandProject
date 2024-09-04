package untitled.src;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;


public class LoadingPanel extends JPanel {
    JLabel megalandName = new JLabel("Megaland");

    public LoadingPanel() {
        setSize(1920, 1040);
        setLayout(null);

        megalandName.setBounds(100, 100, 100, 100);

        add(megalandName);
        setVisible(true);
    }
}