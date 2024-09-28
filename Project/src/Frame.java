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

        LoadingPanel loadingPanel = new LoadingPanel(this);
        loadingPanel.setPreferredSize(new Dimension(1920, 1040));

        JScrollPane scrollPane = new JScrollPane(loadingPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}
