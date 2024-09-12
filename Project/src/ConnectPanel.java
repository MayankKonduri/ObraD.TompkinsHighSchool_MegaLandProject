package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.net.*;
import java.nio.Buffer;

public class ConnectPanel extends JPanel{
    private BufferedImage loading;
    private JLabel titleLabel = new JLabel("Connect to Server:");
    private JLabel nameLabel = new JLabel("Name: ");
    private JTextField nameTextField = new JTextField(20);
    private JLabel ipLabel = new JLabel("IP Address: ");
    private JTextField ipTextField = new JTextField(20);
    private JButton confirmButton = new JButton("Connect to Live Game!");
    private JButton homeButton = new JButton("Home");
    private boolean isConnecting = false;
    ObjectOutputStream os;


    public ConnectPanel(JFrame frame, ObjectOutputStream os){
        this.os = os;
        setSize(1920, 1040);
        setLayout(null);
        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\megaland_banner_1.png")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        titleLabel.setBounds(250,380,500,75);
        titleLabel.setFont(new Font("Oswald", Font.BOLD,40));
        add(titleLabel);

        nameLabel.setBounds(250,450,100,30);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        add(nameLabel);

        nameTextField.setBounds(350,450,300,30);
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }
        });
        add(nameTextField);

        ipLabel.setBounds(250,500,150,30);
        ipLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        ipLabel.setVisible(true);
        add(ipLabel);

        ipTextField.setBounds(400,500,250,30);
        ipTextField.setVisible(true);
        ipTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateConfirmButtonState();
            }
        });
        add(ipTextField);

        confirmButton.setBounds(350,550,250,30);
        confirmButton.setFont(new Font("Georgia",Font.BOLD,15));
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(e -> {
//            if(isConnecting){
//                String clientName = nameTextField.getText();
//                String ipAddress = ipTextField.getText();
//                System.out.println("Connecting To Server At: " + ipAddress + ", With Name: " + clientName);
//                try {
//                    os.writeObject(new CommandFromClient(CommandFromClient.JOIN, " " + clientName));
//                } catch (Exception ez) {
//                    ez.printStackTrace();
//                }
//            } else{
//                ipTextField.setVisible(true);
//                confirmButton.setText("Connect to Live Game!");
//                isConnecting = true;
//            }
            try {
                os.writeObject(new CommandFromClient(CommandFromClient.JOIN, " " + nameTextField.getText()));
            } catch (Exception ez) {
                ez.printStackTrace();
            }
            updateConfirmButtonState();
        });
        add(confirmButton);

        homeButton.setBounds(10, 10, 100, 30);
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));
        homeButton.addActionListener(e -> {
            frame.setContentPane(new LoadingPanel(frame, os));
            frame.revalidate();
        });
        add(homeButton);

        setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1010, null);
//        g.drawImage(buffer, 0, 0, null);
    }

    private void updateConfirmButtonState(){
        boolean isNameValid = !nameTextField.getText().trim().isEmpty();
        boolean isIPValid = !ipTextField.getText().isEmpty();
        confirmButton.setEnabled(isNameValid && isIPValid);
    }

    public void setPlayersJoined(String name) {
        ipLabel.setText(name);
    }
}
