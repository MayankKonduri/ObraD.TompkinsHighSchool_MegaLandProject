package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.ArrayList;

public class ConnectPanel extends JPanel{
    private BufferedImage loading;


    private JLabel titleLabel = new JLabel("Establish Connection");
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
    private JLabel peopleInGameLabel = new JLabel("People in Game Server: ");


    public ConnectPanel(JFrame frame){
        setSize(1920, 1040);
        setLayout(null);
        this.jFrame1 = frame;

        try{
            loading = ImageIO.read(new File("Project\\src\\Images\\Loading_v7.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        clientMain = new ClientMain(nameTextField.getText(), characterSelectPanel, null);
        waitingForHostPanel = new WaitingForHostPanel(jFrame1, clientMain, this, null);
        characterSelectPanel = waitingForHostPanel.getCharacterSelectPanel();


        clientMain.setConnectPanel(this);
        clientMain.setWaitingForHostPanel(waitingForHostPanel);
        this.playerListClientSide = new ArrayList<>();

        titleLabel.setBounds(280, 340, 750, 90);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 60));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel);

        nameLabel.setBounds(394-(394-280),510,140,40);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN,25));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.black);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        nameLabel.setBorder(new LineBorder(Color.white, 1));
        add(nameLabel);

        nameTextField.setBounds(532-(394-280),510,292,40);
        nameTextField.setEnabled(false);
        nameTextField.setFocusable(true);
        nameTextField.setBackground(Color.BLACK);
        nameTextField.setForeground(Color.WHITE);
        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        nameTextField.setFont(new Font("Georgia", Font.PLAIN,25));
        nameTextField.setCaretColor(Color.white);
        nameTextField.setBorder(new LineBorder(Color.white, 1));


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

        ipLabel.setBounds(394-(394-280),450,140,40);
        ipLabel.setFont(new Font("Georgia", Font.PLAIN,20));
        ipLabel.setVisible(true);
        ipLabel.setOpaque(true);
        ipLabel.setBackground(Color.black);
        ipLabel.setForeground(Color.WHITE);
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ipLabel.setVerticalAlignment(SwingConstants.CENTER);
        ipLabel.setBorder(new LineBorder(Color.white, 1));
        add(ipLabel);

        ipTextField.setBounds(532-(394-280),450,292,40);
        ipTextField.setVisible(true);
        ipTextField.setFocusable(true);
        ipTextField.setBackground(Color.BLACK);
        ipTextField.setForeground(Color.WHITE);
        ipTextField.setHorizontalAlignment(SwingConstants.CENTER);
        ipTextField.setFont(new Font("Georgia", Font.PLAIN,25));
        ipTextField.setCaretColor(Color.white);
        ipTextField.setBorder(new LineBorder(Color.white, 1));
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

        confirmButton.setBounds(394-(394-280),570,430,40);
        confirmButton.setFont(new Font("Georgia",Font.BOLD,18));
        confirmButton.setEnabled(false);
        confirmButton.setBackground(Color.black);
        confirmButton.setForeground(Color.white);
        confirmButton.setBorder(BorderFactory.createEmptyBorder());
        confirmButton.setFocusPainted(false);
        confirmButton.addActionListener(e -> {
            String clientName = nameTextField.getText();
            String ipAddress = ipTextField.getText();
            boolean temp = clientMain.connectToServer(ipAddress,clientName);
            if(temp) {
                ipTextField.setEnabled(false);
                System.out.println("Connecting To Server At: " + ipAddress + ", With Name: " + clientName);
                confirmButton.setEnabled(false);
//                confirmButton.setBackground(Color.black);
//                confirmButton.setForeground(Color.WHITE);
//                confirmButton.setText("Connected");
                confirmButton.setVisible(false);
                verifyNameButton.setVisible(true);
                verifyNameButton.setBounds(394-(394-280), 570, 430, 40);
                verifyNameButton.setEnabled(false);


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
                    private void updateVerifyButtonState() {
                        String text = nameTextField.getText();
                        verifyNameButton.setEnabled(text != null && !text.trim().isEmpty());
                        if(verifyNameButton.isEnabled()) {
                            verifyNameButton.addMouseListener(new MouseAdapter() {

                                @Override
                                public void mouseEntered(MouseEvent e) {
                                    verifyNameButton.setBorder(new LineBorder(Color.white, 1));
                                }
                                @Override
                                public void mouseExited(MouseEvent e) {
                                    verifyNameButton.setBorder(BorderFactory.createEmptyBorder());
                                }
                            });
                        } else if(verifyNameButton.isEnabled() == false){
                            verifyNameButton.setBorder(BorderFactory.createEmptyBorder());
                        }
                    }
                });
            }
        });
        add(confirmButton);




//        verifyNameButton.setBounds(394, 570, 160, 40);
        verifyNameButton.setFont(new Font("Georgia",Font.BOLD,18));
        verifyNameButton.setEnabled(false);
        verifyNameButton.setBackground(Color.black);
        verifyNameButton.setForeground(Color.white);
        verifyNameButton.setBorder(BorderFactory.createEmptyBorder());
        verifyNameButton.setFocusPainted(false);

        verifyNameButton.addActionListener(e -> {
            if(clientMain.Final_gamePlayerNames_ClientSide  == null || playerListClientSide.isEmpty()){
                JOptionPane.showMessageDialog(this,"Player List Not Available");
                return;
            }
            if(playerNameExists(nameTextField.getText())){
                JOptionPane.showMessageDialog(this,"Name Already Taken");
            }
            else{
                System.out.print("Name is Available");
                clientMain.setName(nameTextField.getText());
                nameTextField.setEnabled(false);
                confirmButton.setEnabled(false);
                verifyNameButton.setText("Verified and Connected");
                verifyNameButton.setEnabled(false);
                clientMain.Final_gamePlayerNames_ClientSide = clientMain.clearPlayerNames(clientMain.Final_gamePlayerNames_ClientSide);
                System.out.println("ID Before Creating Object: " + clientMain.Final_gamePlayerNames_ClientSide);
                Player playerClient = new Player(clientMain.gamePlayerNames_ClientSide.indexOf(nameTextField.getText()), nameTextField.getText(), false, 0, 0,0,false,false, false);
                waitingForHostPanel.setPlayerObject(playerClient);
                CommandFromClient.notifyPlayerObject(clientMain.getOut(), playerClient);
                System.out.println("Verified Name");
            }

        });
        add(verifyNameButton);



        homeButton.setBounds(10, 10, 100, 40);
        homeButton.setFont(new Font("Georgia", Font.BOLD, 20));
        homeButton.setFocusPainted(false);
        homeButton.setBackground(Color.black);
        homeButton.setForeground(Color.white);
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

        homeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                homeButton.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                homeButton.setBorder(null);
            }
        });
        add(homeButton);

        peopleInGameLabel.setBounds(394-(394-280), 630, 300, 40);
        peopleInGameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        peopleInGameLabel.setVisible(true);
        peopleInGameLabel.setOpaque(true);
        peopleInGameLabel.setBackground(Color.black);
        peopleInGameLabel.setForeground(Color.WHITE);
        peopleInGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        peopleInGameLabel.setVerticalAlignment(SwingConstants.CENTER);
        peopleInGameLabel.setBorder(new LineBorder(Color.white, 1));
        add(peopleInGameLabel);

        playersJoined.setFont(new Font("Georgia", Font.PLAIN, 20));
        playersJoined.setEditable(false);
        playersJoined.setBounds(394-(394-280), 690-15, 430, 240);
        playersJoined.setBackground(Color.BLACK);
        playersJoined.setForeground(Color.WHITE);
        playersJoined.setBorder(new LineBorder(Color.white, 1));

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
        if(isIPValid) {
            confirmButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    confirmButton.setBorder(new LineBorder(Color.white, 1));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    confirmButton.setBorder(BorderFactory.createEmptyBorder());
                }
            });
        }
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
        StringBuilder sb = new StringBuilder("");
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
        g.drawImage(loading, 0, 0, 1920, 1050, null);
//        g.drawImage(buffer, 0, 0, null);
    }

}