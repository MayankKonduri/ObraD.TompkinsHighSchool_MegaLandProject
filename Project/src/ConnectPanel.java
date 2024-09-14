package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class ConnectPanel extends JPanel{
  /*  private BufferedImage loading;
    private JLabel hostLabel = new JLabel("Connect");
    private JLabel ipLabel = new JLabel("IP Address: ");
    private JTextArea playersJoined = new JTextArea("Players Joined:\n");

    public ConnectPanel(JFrame frame) {
        setSize(1920, 1040);
        setLayout(null);

        try {
            loading = ImageIO.read((new File("Project\\src\\Images\\megaland_banner_1.png")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }

        hostLabel.setBounds(400, 450, 400, 75);
        hostLabel.setFont(new Font("Oswald", Font.BOLD, 30));
        add(hostLabel);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1040, null);
//        g.drawImage(buffer, 0, 0, null);
    }

*/

    private JLabel titleLabel = new JLabel("Connecting to Game Server");
    private JLabel nameLabel = new JLabel("Name: ");
    private JTextField nameTextField = new JTextField(20);
    private JLabel ipLabel = new JLabel("IP Address: ");
    private JTextField ipTextField = new JTextField(15);
    private JButton confirmButton = new JButton("Connect to Live Game!");
    private JButton homeButton = new JButton("Home");
    private JButton verifyNameButton = new JButton("Verify Name");
    private JTextArea playersJoined = new JTextArea();
    private JScrollPane playersScrollPane = new JScrollPane(playersJoined);
    private HostPanel hostPanel;
    private ClientMain clientMain;
    private Socket socket;
    private ClientListener clientListener;
    ArrayList<String> playerListClientSide;
    private ClientHandler clientHandler;
    private JFrame jFrame1;

    public ConnectPanel(JFrame frame){
        setSize(1920, 1040);
        setLayout(null);
        clientMain = new ClientMain();
        clientMain.setConnectPanel(this);
        titleLabel.setBounds(400,50,500,75);
        titleLabel.setFont(new Font("Oswald", Font.BOLD,30));
        add(titleLabel);

        nameLabel.setBounds(400,150,100,30);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        add(nameLabel);

        nameTextField.setBounds(470,150,330,30);
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

        ipLabel.setBounds(400,200,150,30);
        ipLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        ipLabel.setVisible(true);
        add(ipLabel);

        ipTextField.setBounds(520,200,280,30);
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

        confirmButton.setBounds(570,250,230,30);
        confirmButton.setFont(new Font("Georgia",Font.BOLD,15));
        confirmButton.setEnabled(true);
        confirmButton.addActionListener(e -> {
            String clientName = nameTextField.getText();
            String ipAddress = ipTextField.getText();
            connectToServer(ipAddress,clientName);
            System.out.println("Connecting To Server At: " + ipAddress + ", With Name: " + clientName);
        });
        add(confirmButton);

        verifyNameButton.setBounds(400, 250, 160, 30);
        verifyNameButton.setFont(new Font("Georgia",Font.BOLD,15));
        verifyNameButton.setEnabled(false);
        verifyNameButton.addActionListener(e -> {
            if(playerListClientSide == null || playerListClientSide.isEmpty()){
                JOptionPane.showMessageDialog(this,"Player List Not Available");
                return;
            }
            if(clientMain.playerNameExists(nameTextField.getText())){
                System.out.print("Name Already Taken");
            }else{
                System.out.print("Name is Available");
            }
        });
        add(verifyNameButton);

        homeButton.setBounds(10, 10, 100, 30);
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));
        homeButton.addActionListener(e -> {
            sendDisconnectMessage();
            frame.setContentPane(new LoadingPanel(frame));
            frame.revalidate();
        });
        add(homeButton);

        playersJoined.setFont(new Font("Georgia", Font.PLAIN, 20));
        playersJoined.setEditable(false);
        playersScrollPane.setBounds(400, 300, 400, 300);
        add(playersScrollPane);

        setVisible(true);
    }
    private boolean playerNameExists(String name){
        for(String playerName: playerListClientSide){
            if(playerName.equals(name)){
                return true;
            }
        } return false;
    }
    private void updateConfirmButtonState(){
        boolean isNameValid = !nameTextField.getText().trim().isEmpty();
        boolean isIPValid = !ipTextField.getText().trim().isEmpty();
        verifyNameButton.setEnabled(isNameValid && isIPValid);
    }

    private void connectToServer(String ipAddress, String clientName){
        System.out.println("Attempting to connect to server...");
        if (socket != null && !socket.isClosed()) {
            System.out.println("Already connected.");
            return;
        }
        try{
            socket = new Socket(ipAddress, 12345);
            System.out.println("Connected to server at " + ipAddress + " on port 12345");
            clientListener = new ClientListener(socket, clientMain);
            new Thread(clientListener).start();
            clientListener.sendMessage(clientName);
            System.out.println("Sent client name: " + clientName);
            confirmButton.setEnabled(false);
            confirmButton.setBackground(Color.LIGHT_GRAY);
            confirmButton.setForeground(Color.WHITE);
            confirmButton.setText("Connected");
        } catch (IOException e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to Connect to Server.", "Connection Error",JOptionPane.ERROR_MESSAGE);
            confirmButton.setEnabled(true);
            confirmButton.setBackground(null);
            confirmButton.setForeground(null);
        }
    }
    private void sendDisconnectMessage(){
        if(clientListener!=null){
            clientListener.sendMessage("DISCONNECT: " + nameTextField.getText());
            clientListener.close();
        }
    }

    public void onHostDisconnected(){
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(jFrame1, "Host Disconnected", "Connection Lost", JOptionPane.ERROR_MESSAGE);
            jFrame1.setContentPane(new LoadingPanel(jFrame1));
            jFrame1.revalidate();
        });
    }

    public void updatePlayerList(String playerNames){
        playerListClientSide.add(playerNames);

        StringBuilder sb = new StringBuilder("Players Joined:\n");
        for(String playerName : playerListClientSide){
            sb.append(playerName).append("\n");
        }
        playersJoined.setText(sb.toString());
    }
    public void handlePlayerLeft(String playerName){
        String text = playersJoined.getText();
        String updatedText = text.replace(playerName + "\n","");
        playersJoined.setText(updatedText);
    }
}