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

public class HostPanel extends JPanel {

    private JLabel hostSettingsLabel = new JLabel("Hosting Settings:");
    private JLabel nameLabel = new JLabel("Name: ");
    private JTextField nameTextField = new JTextField(20);
    private JButton confirmSettings = new JButton("Confirm Settings");
    private JLabel numPlayersLabel = new JLabel("Number of Players: ");
    private ButtonGroup playerGroup = new ButtonGroup();
    private JRadioButton[] playerButtons = new JRadioButton[4];
    private JLabel ipLabel = new JLabel("Hosting IP Address: ");
    private JLabel ipPlaceHolderLabel = new JLabel();
    private JButton homeButton = new JButton("Home");
    private JButton changeSettings = new JButton("Change Settings: ");
    private boolean settingsConfirmed = false;
    private BufferedImage loading;
    private String ipAddress;
    private String hostName;

    private JLabel peopleInGameLabel = new JLabel("People in Game Server: ");
    private JTextArea peopleListArea = new JTextArea();
    private JScrollPane peopleScrollPane = new JScrollPane(peopleListArea);
    private JPanel peoplePanel = new JPanel();

    public HostPanel(JFrame frame) {
        hostName="";
        setSize(1920, 1040);
        setLayout(null);

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

        hostSettingsLabel.setBounds(400, 50, 400, 75);
        hostSettingsLabel.setFont(new Font("Oswald", Font.BOLD, 40));
        add(hostSettingsLabel);

        nameLabel.setBounds(400, 150, 100, 30);
        nameLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(nameLabel);

        nameTextField.setBounds(470, 150, 300, 30);
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

        confirmSettings.setBounds(780, 150, 250, 30);
        confirmSettings.setFont(new Font("Georgia", Font.BOLD, 15));
        confirmSettings.setEnabled(false);
        confirmSettings.addActionListener(e -> {
            if (settingsConfirmed) {
                nameTextField.setEnabled(true);
                confirmSettings.setText("Confirm Settings");
                ipLabel.setVisible(false);
                ipPlaceHolderLabel.setVisible(false);
                changeSettings.setVisible(false);
                setPlayerButtonsEnabled(true);
                if(!hostName.isEmpty()){
                    updatePeopleList(new String[]{hostName});
                }
                clearPeopleList();
            } else {
                hostName = nameTextField.getText();
                nameTextField.setEnabled(false);
                confirmSettings.setText("Change Settings");
                ipLabel.setVisible(true);
                ipPlaceHolderLabel.setVisible(true);
                ipPlaceHolderLabel.setText(ipAddress);
                changeSettings.setVisible(true);
                setPlayerButtonsEnabled(false);
                peoplePanel.setVisible(true);
                updatePeopleList(new String[]{hostName});

            }
            settingsConfirmed = !settingsConfirmed;
            revalidate();
            repaint();
        });
        add(confirmSettings);

        numPlayersLabel.setBounds(400, 200, 200, 30);
        numPlayersLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        add(numPlayersLabel);

        String[] numberPlayersOption = {"2", "3", "4", "5"};
        for (int i = 0; i < numberPlayersOption.length; i++) {
            playerButtons[i] = new JRadioButton(numberPlayersOption[i]);
            playerButtons[i].setBounds(600 + i * 75, 200, 70, 30);
            playerButtons[i].setFont(new Font("Georgia", Font.PLAIN, 20));
            playerButtons[i].addActionListener(e -> updateConfirmSettingsButtonState());
            playerGroup.add(playerButtons[i]);
            add(playerButtons[i]);
        }

        ipLabel.setBounds(400, 250, 250, 30);
        ipLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
        ipLabel.setVisible(false);
        add(ipLabel);

        ipPlaceHolderLabel.setBounds(600, 250, 200, 30);
        ipPlaceHolderLabel.setFont(new Font("Georgia", Font.ITALIC, 25));
        ipPlaceHolderLabel.setVisible(false);
        add(ipPlaceHolderLabel);

        peopleInGameLabel.setBounds(400, 300, 350, 30);
        peopleInGameLabel.setFont(new Font("Georgia", Font.BOLD, 15));
        add(peopleInGameLabel);

        peopleListArea.setFont(new Font("Georgia", Font.PLAIN, 20));
        peopleListArea.setEditable(false);
        peopleScrollPane.setBounds(0, 0, 600, 200);
        add(peopleScrollPane);

        peoplePanel.setBounds(400, 340, 600, 250);
        peoplePanel.setLayout(null);
        peoplePanel.add(peopleScrollPane);
        add(peoplePanel);

        homeButton.setBounds(10, 10, 100, 30);
        homeButton.setFont(new Font("Georgia", Font.PLAIN, 20));
        homeButton.addActionListener(e -> {
            frame.setContentPane(new LoadingPanel(frame));
            frame.revalidate();
        });
        add(homeButton);

        setVisible(true);
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

    public void updatePeopleList(String[] people) {
        StringBuilder sb = new StringBuilder();
        for (String person : people) {
            if(person.equals(hostName)){
                sb.append(person).append(" (Host)").append("\n");
            } else{
                sb.append(person).append("\n");
            }
        }
        peopleListArea.setText(sb.toString());
    }

    public void clearPeopleList(){
        peopleListArea.setText("");
    }
}
