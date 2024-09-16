package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class HostPanel extends JPanel{

    private JLabel hostSettingsLabel = new JLabel("Hosting Settings:");
    private JLabel nameLabel = new JLabel("Name: ");
    public JTextField nameTextField = new JTextField(20);
    private JButton confirmSettings = new JButton("Confirm Settings");
    private JLabel numPlayersLabel = new JLabel("Number of Players: ");
    private ButtonGroup playerGroup = new ButtonGroup();
    private JRadioButton[] playerButtons = new JRadioButton[4];
    private JLabel ipLabel = new JLabel("Hosting IP Address: ");
    private JLabel ipPlaceHolderLabel = new JLabel("Waiting for game settings...");
    private JButton homeButton = new JButton("Home");
    private JButton startHostingButton = new JButton("Start Hosting");
    private JButton startButton = new JButton("Start Game");
    private boolean settingsConfirmed = false;
    private BufferedImage loading;
    private String ipAddress;
    public String hostName;
    private final int[] selectedNumberOfPlayers = {2};
    private JLabel peopleInGameLabel = new JLabel("People in Game Server: ");
    private JTextArea peopleListArea = new JTextArea();
    private JScrollPane peopleScrollPane = new JScrollPane(peopleListArea);
    private JPanel peoplePanel = new JPanel();
    ArrayList<Player> playerInfo = new ArrayList<>(); //Ayan Code Implement
    private ServerSocket serverSocket;
    //private final List<ClientHandler> clientHandler = new ArrayList<ClientHandler>();
    public ArrayList<String> playerList_serverSide = new ArrayList<>();
    private ServerListener serverListener;
    private ServerMain serverMain;
    private CommandFromServer commandFromServer = new CommandFromServer();
    private CharacterSelectPanel characterSelectPanel;
    private CardSelectPanel cardSelectPanel;
    private JFrame jFrame;

    public HostPanel(JFrame frame) {
        this.jFrame = frame;

        hostName="";
        setSize(1920, 1040);
        setLayout(null);
        this.playerList_serverSide = new ArrayList<>();


        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            ipAddress = "Unable to Determine IP of Device";
        }

        try {
            loading = ImageIO.read(new File("Project/src/Images/megaland_banner_1.png"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Loading Images: " + e.getMessage());
        }

        hostSettingsLabel.setBounds(250, 380, 400, 75);
        hostSettingsLabel.setFont(new Font("Oswald", Font.BOLD, 40));
        add(hostSettingsLabel);

        nameLabel.setBounds(250, 450, 100, 30);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(nameLabel);

        nameTextField.setBounds(350, 450, 300, 30);
        nameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateConfirmSettingsButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateConfirmSettingsButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateConfirmSettingsButtonState();
            }
        });
        add(nameTextField);

        confirmSettings.setBounds(750, 450, 250, 30);
        confirmSettings.setFont(new Font("Georgia", Font.BOLD, 15));
        confirmSettings.setEnabled(false);
        confirmSettings.addActionListener(e -> {
            if (settingsConfirmed) {
                nameTextField.setEnabled(true);
                startButton.setVisible(true);
                startHostingButton.setEnabled(false);
                confirmSettings.setText("Confirm Settings");
                ipLabel.setVisible(false);
                ipPlaceHolderLabel.setVisible(false);
                setPlayerButtonsEnabled(true);
                peoplePanel.setVisible(true);
                resetStartHostingButton();
                clearPeopleList();
                clearPeopleList();
            } else {
                hostName = nameTextField.getText();
                nameTextField.setEnabled(false);
                confirmSettings.setText("Change Settings");
                ipLabel.setVisible(true);
                ipPlaceHolderLabel.setVisible(true);
                ipPlaceHolderLabel.setText(ipAddress);
                setPlayerButtonsEnabled(false);
                peoplePanel.setVisible(true);
                startButton.setVisible(true);
                updateStartButtonState();
                startHostingButton.setEnabled(true);
            }
            settingsConfirmed = !settingsConfirmed;
            revalidate();
            repaint();
        });
        add(confirmSettings);

        numPlayersLabel.setBounds(250, 500, 200, 30);
        numPlayersLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(numPlayersLabel);

        String[] numberPlayersOption = {"2", "3", "4", "5"};
        for (int i = 0; i < numberPlayersOption.length; i++) {
            final int index = i;
            playerButtons[i] = new JRadioButton(numberPlayersOption[i]);
            playerButtons[i].setBounds(450 + i * 55, 500, 50, 30);
            playerButtons[i].setFont(new Font("Georgia", Font.PLAIN, 20));
            playerButtons[i].addActionListener(e -> {
                selectedNumberOfPlayers[0] = Integer.parseInt(numberPlayersOption[index]);
                updateConfirmSettingsButtonState();
                updateStartButtonState();
            });
            playerGroup.add(playerButtons[i]);
            add(playerButtons[i]);
        }

        ipLabel.setBounds(250, 550, 250, 30);
        ipLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        ipLabel.setVisible(true);
        add(ipLabel);

        ipPlaceHolderLabel.setBounds(450, 550, 400, 30);
        ipPlaceHolderLabel.setFont(new Font("Georgia", Font.ITALIC, 20));
        ipPlaceHolderLabel.setVisible(true);
        add(ipPlaceHolderLabel);

        peopleInGameLabel.setBounds(250, 600, 350, 30);
        peopleInGameLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        add(peopleInGameLabel);

        peopleListArea.setFont(new Font("Georgia", Font.PLAIN, 20));
        peopleListArea.setEditable(false);
        peopleScrollPane.setBounds(0, 0, 600, 200);
        add(peopleScrollPane);

        peoplePanel.setBounds(250, 650, 400, 250);
        peoplePanel.setLayout(null);
        peoplePanel.add(peopleScrollPane);
        add(peoplePanel);

        startHostingButton.setBounds(250,950,200,30);
        startHostingButton.setFont(new Font("Georgia", Font.BOLD,15));
        startHostingButton.setEnabled(false);
        startHostingButton.addActionListener(e -> {
            startHostingServer();
            startHostingButton.setEnabled(false);
            startHostingButton.setBackground(Color.LIGHT_GRAY);
            startHostingButton.setForeground(Color.WHITE);
            startHostingButton.setText("Hosting in Progress");
            confirmSettings.setEnabled(false);
            updatePeopleList();
        });
        add(startHostingButton);


        startButton.setBounds(500,950,200,30);
        startButton.setFont(new Font("Georgia",Font.BOLD,15));
        startButton.setEnabled(false);//mayank fix this
        startButton.addActionListener(e -> {
            serverMain.broadcastMessage(1, nameTextField.getText());
            //commandFromServer.notify_START_GAME(serverMain.getOut(), nameTextField.getText());
            System.out.println("Game Has Started!!!");
            frame.setContentPane(cardSelectPanel);
            frame.revalidate();
        });
        add(startButton);

        homeButton.setBounds(10, 10, 100, 30);
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));
        homeButton.addActionListener(e -> {
            serverMain.stopServer();
            frame.setContentPane(new LoadingPanel(frame));
            frame.revalidate();
        });
        add(homeButton);

        setVisible(true);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1010, null);
    }

    private void setPlayerButtonsEnabled(boolean enabled) {
        for (JRadioButton button : playerButtons) {
            button.setEnabled(enabled);
        }
    }


    public void updateConfirmSettingsButtonState(){
        boolean isNameValid = !nameTextField.getText().trim().isEmpty();
        boolean isPlayerSelected = playerGroup.getSelection() != null;
        confirmSettings.setEnabled(isNameValid && isPlayerSelected);
    }

    public void updateStartButtonState(){
        int numberOfPeople = getNumberOfPeopleInGame();
        startButton.setEnabled(numberOfPeople == selectedNumberOfPlayers[0]);
        System.out.print(selectedNumberOfPlayers[0]  + "Selected Number of People");
        System.out.print(numberOfPeople + "Number of People");
    }

    public int getNumberOfPeopleInGame(){
        return playerList_serverSide.size();
    }

    public synchronized void updatePeopleList() {
        peopleListArea.setText("");
        StringBuilder sb = new StringBuilder("Players In Game:\n");
        for(String playerName : playerList_serverSide){
            sb.append(playerName).append("\n");
        }
        peopleListArea.setText(sb.toString());
        updateStartButtonState();
    }

    public void clearPeopleList(){
        peopleListArea.setText("");
    }

    public void startHostingServer() {
        try {
            cardSelectPanel = new CardSelectPanel(jFrame, null, this);
            this.serverMain = new ServerMain(12345, nameTextField.getText(), this, cardSelectPanel.getCharacterSelectPanel());
            cardSelectPanel = new CardSelectPanel(jFrame, serverMain, this);
            characterSelectPanel = cardSelectPanel.getCharacterSelectPanel();
            serverMain.setCharacterSelectPanel(characterSelectPanel);
            new Thread(() -> serverMain.startServer()).start();
            System.out.println("Hosting Started");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Starting the Server");
        }
    }
    private synchronized void printPlayerList(){
        System.out.println("People Currently in the Game:");
        System.out.println(hostName);
        for(String playerName : playerList_serverSide){
            System.out.println(playerName);
        }
    }
    private void resetStartHostingButton(){
        startHostingButton.setEnabled(false);
        startHostingButton.setForeground(new JButton().getForeground());
        startHostingButton.setBackground(new JButton().getBackground());
        startHostingButton.setText("Start Hosting!");
    }
    public ArrayList<String> getPlayerList(){
        return playerList_serverSide;
    }
    public void setCharacterSelectPanel(CharacterSelectPanel panel) {
        this.characterSelectPanel = panel;
    }
}
