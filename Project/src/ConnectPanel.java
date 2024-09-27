package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class ConnectPanel extends JPanel{
    private BufferedImage loading;
//    private JLabel hostLabel = new JLabel("Connect");
//    private JLabel ipLabel = new JLabel("IP Address: ");
//    private JTextArea playersJoined = new JTextArea("Players Joined:\n");
//
//    public ConnectPanel(JFrame frame) {
//        setSize(1920, 1040);
//        setLayout(null);
//
//        try {
//            loading = ImageIO.read((new File("Project\\src\\Images\\megaland_banner_1.png")));
//
//        } catch (Exception ah) {
//            ah.printStackTrace();
//            System.out.println("Error Loading Images: " + ah.getMessage());
//        }
//
//        hostLabel.setBounds(400, 450, 400, 75);
//        hostLabel.setFont(new Font("Oswald", Font.BOLD, 30));
//        add(hostLabel);
//        setVisible(true);
//    }





    private JLabel titleLabel = new JLabel("Connecting to Game Server");
    private JLabel nameLabel = new JLabel("Name: ");
    public JTextField nameTextField = new JTextField(20);
    private JLabel ipLabel = new JLabel("IP Address: ");
    private JTextField ipTextField = new JTextField(15);
    public JButton confirmButton = new JButton("Connect to Live Game!");
    private JButton homeButton = new JButton("Home");
    private JButton verifyNameButton = new JButton("Verify Name");
    private JTextArea playersJoined = new JTextArea();
    private JScrollPane playersScrollPane = new JScrollPane(playersJoined);
    private ClientMain clientMain;
    private Socket socket;
    private ClientListener clientListener;
    public ArrayList<String> playerListClientSide;
    private JFrame jFrame1;
    private WaitingForHostPanel waitingForHostPanel;
    private CharacterSelectPanel characterSelectPanel;
    private ConnectPanel connectPanel1;

    public ConnectPanel(JFrame frame){
        setSize(1920, 1040);
        setLayout(null);
        this.jFrame1 = frame;

        verifyNameButton.setVisible(false);

        try{
            loading = ImageIO.read(new File("Project\\src\\Images\\megaland_banner_1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        clientMain = new ClientMain(nameTextField.getText(), characterSelectPanel, null);
        waitingForHostPanel = new WaitingForHostPanel(jFrame1, clientMain, this);
        characterSelectPanel = waitingForHostPanel.getCharacterSelectPanel();


        clientMain.setConnectPanel(this);
        clientMain.setWaitingForHostPanel(waitingForHostPanel);
        this.playerListClientSide = new ArrayList<>();

        titleLabel.setBounds(250,380,600,75);
        titleLabel.setFont(new Font("Oswald", Font.BOLD,40));
        add(titleLabel);

        nameLabel.setBounds(250,450,100,30);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        add(nameLabel);

        nameTextField.setBounds(350,450,300,30);
        nameTextField.setEnabled(false);
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

        confirmButton.setBounds(250,550,400,30);
        confirmButton.setFont(new Font("Georgia",Font.BOLD,15));
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(e -> {
            String clientName = nameTextField.getText();
            String ipAddress = ipTextField.getText();
            boolean temp = clientMain.connectToServer(ipAddress,clientName);
            if(temp) {
                ipTextField.setEnabled(false);
                System.out.println("Connecting To Server At: " + ipAddress + ", With Name: " + clientName);
                confirmButton.setEnabled(false);
                confirmButton.setVisible(false);
                verifyNameButton.setVisible(true);
                verifyNameButton.setBounds(250,550,400,30);
                //confirmButton.setBackground(Color.lightGray);
                //confirmButton.setForeground(Color.WHITE);
                confirmButton.setText("Connected");
                nameTextField.setEnabled(true);

                nameTextField.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        updateVerifyButtonState();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        updateVerifyButtonState();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        updateVerifyButtonState();
                    }

                    private void updateVerifyButtonState(){
                        String text = nameTextField.getText();
                        verifyNameButton.setEnabled(text != null && !text.trim().isEmpty());
                    }
                });
            }
        });
        add(confirmButton);

        //verifyNameButton.setBounds(200, 550, 160, 30);
        verifyNameButton.setFont(new Font("Georgia",Font.BOLD,15));
        verifyNameButton.setEnabled(false);
        verifyNameButton.addActionListener(e -> {
            if(clientMain.Final_gamePlayerNames_ClientSide  == null || playerListClientSide.isEmpty()){
                JOptionPane.showMessageDialog(this,"Player List Not Available");
                return;
            }
            if(playerNameExists(nameTextField.getText())){
                JOptionPane.showMessageDialog(this,"Name Already Taken");
            }else{
                System.out.print("Name is Available");
                clientMain.setName(nameTextField.getText());
                nameTextField.setEnabled(false);
                confirmButton.setEnabled(false);
                verifyNameButton.setEnabled(false);
                clientMain.gamePlayerNames_ClientSide = clientMain.clearPlayerNames(clientMain.gamePlayerNames_ClientSide);
            }
        });
        add(verifyNameButton);

        homeButton.setBounds(10, 10, 100, 30);
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));
        homeButton.addActionListener(e -> {
            if(clientMain.getOut() != null){
                sendDisconnectMessage();
                frame.setContentPane(new LoadingPanel(frame));
                frame.revalidate();
            }
            else{
                frame.setContentPane(new LoadingPanel(frame));
                frame.revalidate();
            }
        });
        add(homeButton);

        playersJoined.setFont(new Font("Georgia", Font.PLAIN, 20));
        playersJoined.setEditable(false);
        playersJoined.setBounds(250, 600, 400, 300);
        add(playersJoined);
        //add(playersScrollPane);

        setVisible(true);


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(clientMain != null && clientMain.getOut()!=null) {
                sendDisconnectMessage();
            }
        }));

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(clientMain != null && clientMain.getOut()!=null) {
                    sendDisconnectMessage();
                    frame.setContentPane(new LoadingPanel(frame));
                    frame.revalidate();
                }
                frame.setContentPane(new LoadingPanel(frame));
                frame.revalidate();
            }
        });


    }
    private boolean playerNameExists(String name){
        return clientMain.Final_gamePlayerNames_ClientSide.contains(name);
    }
    private void updateConfirmButtonState(){
        //boolean isNameValid = !nameTextField.getText().trim().isEmpty();
        boolean isIPValid = !ipTextField.getText().trim().isEmpty();
        confirmButton.setEnabled(/*isNameValid &&*/ isIPValid);
    }
    private void sendDisconnectMessage(){
        try{
            CommandFromClient.notify_CLIENT_DISCONNECTED(clientMain.getOut(), nameTextField.getText());
            clientMain.getOut().close();
            clientMain.getSocket().close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            clientMain.setSocket(null);
            clientMain.setOut(null);
        }
    }
    public void switchToLoadingPanel() {
        JOptionPane.showMessageDialog(jFrame1, "Host Disconnected", "Connection Lost", JOptionPane.ERROR_MESSAGE);
        jFrame1.setContentPane(new LoadingPanel(jFrame1));
        jFrame1.revalidate();
    }
    public void updatePlayerList(){
        playersJoined.setText("");
        StringBuilder sb = new StringBuilder("Players In Game:\n");
        for(String playerName : clientMain.Final_gamePlayerNames_ClientSide){
            sb.append(playerName).append("\n");
        }
        playersJoined.setText(sb.toString());
    }
    public void handlePlayerLeft(String playerName){
        String text = playersJoined.getText();
        String updatedText = text.replace(playerName + "\n","");
        playersJoined.setText(updatedText);
    }
    public void switchToWaitingForHostPanel() {
        jFrame1.setContentPane(waitingForHostPanel);
        jFrame1.revalidate();
    }
    public void setCharacterSelectPanel(CharacterSelectPanel panel) {
        this.characterSelectPanel = panel;
    }

    public void verifyName() {
        for(int i=0;i<playerListClientSide.size()-1;i++){
            System.out.println("Entered Search!");
            if(nameTextField.getText().equals(playerListClientSide.get(i))){
                JOptionPane.showMessageDialog(this, "Found Match!");
                connectPanel1 = new ConnectPanel(new JFrame());
                connectPanel1.ipTextField.setText(this.ipTextField.getText());
                System.out.println("Please Join Game Again with Valid Name");
                sendDisconnectMessage();
                jFrame1.setContentPane(connectPanel1);
                jFrame1.revalidate();
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics bg = buffer.getGraphics();
        g.drawImage(loading, 0, 0, 1920, 1010, null);
//        g.drawImage(buffer, 0, 0, null);
    }

}