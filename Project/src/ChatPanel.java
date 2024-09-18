package Project.src;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ChatPanel extends JPanel{

    private JFrame jFrame1;
    private ClientMain clientMain1;
    private ServerMain serverMain1;
    private HostPanel hostPanel1;
    private ConnectPanel connectPanel1;
    private CardSelectPanel cardSelectPanel1;
    private CharacterSelectPanel characterSelectPanel1;
    private Boolean isHost1;
    private GamePanel gamePanel1;
    private JButton chatButton1;

    private JTextArea chatArea;
    private JTextField messageField;
    private Timer fadeTimer;
    private Timer inactivityTimer;
    private boolean isChatVisible = false;
    private boolean hasNewMessage = false;;

    public ChatPanel(JFrame frame, JButton chatButton1, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CardSelectPanel cardSelectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost, GamePanel gamePanel){
        this.jFrame1 = frame;
        this.clientMain1 = clientMain;
        this.serverMain1 = serverMain;
        this.hostPanel1 = hostPanel;
        this.connectPanel1 = connectPanel;
        this.cardSelectPanel1 = cardSelectPanel;
        this.characterSelectPanel1 = characterSelectPanel;
        this.isHost1 = isHost;
        this.gamePanel1 = gamePanel;
        this.chatButton1 = chatButton1;

        setLayout(null);
        setOpaque(false);

        JPanel headerPanel = new JPanel(null);
        headerPanel.setBounds(0,0,250,30);
        headerPanel.setBackground(new Color(150,150,150));
        headerPanel.setBorder(new EmptyBorder(5,10,5,10));

        JLabel headerLabel = new JLabel("Game Chat with:" /* Player Names */);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(10, 5, 200, 20);
        headerPanel.add(headerLabel);
        add(headerPanel);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBounds(10, 40, 230, 130); // Position and size
        add(scrollPane);

        messageField = new JTextField();
        messageField.setBounds(10,180,150,30);
        add(messageField);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(170, 180, 70, 30);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText().trim();
                if(!message.isEmpty()){
                    addMessage("PlayerName", message);
                    messageField.setText("");
                    sendMessageToPlayers(message);
                }
            }
        });
        add(sendButton);
        createFadeTimer();
        createInactivityTimer();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetInactivityTimer();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                resetInactivityTimer();
            }
        });
        messageField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                resetInactivityTimer();
            }
        });
    }

    private void addMessage(String playerName, String message) {
        chatArea.append("<b>" + playerName + "<b>:" + message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());

        if(!isChatVisible){
            hasNewMessage = true;
            chatButton1.setBorder(new LineBorder(Color.BLUE,3));
        }
        resetInactivityTimer();
    }
    public void openChat(){
        isChatVisible = true;
        setVisible(true);
        chatButton1.setEnabled(false);
        chatButton1.setBorder(null);
        hasNewMessage = true;
        resetInactivityTimer();
    }
    public void closeChat(){
        isChatVisible = false;
        fadeTimer.start();
    }
    private void createFadeTimer(){
        fadeTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float opacity = getBackground().getAlpha() / 255.0f;
                opacity -= 0.05f;
                if(opacity <=0){
                    fadeTimer.stop();
                    setVisible(false);
                    chatButton1.setEnabled(true);
                }
                setBackground(new Color(0.8f, 0.8f, 0.8f, opacity));
                repaint();
            }
        });
    }
    private void createInactivityTimer(){
        inactivityTimer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isChatVisible){
                    fadeTimer.start();
                }
            }
        });
        inactivityTimer.setRepeats(false);
    }
    private void resetInactivityTimer(){
        inactivityTimer.restart();
        if(fadeTimer.isRunning()){
            fadeTimer.stop();
        }
        setBackground(new Color(0.8f, 0.8f, 0.8f, 1.0f));
        repaint();
    }
    private void sendMessageToPlayers(String message){

    }
    public void handleIncomingMessage(String playerName, String message){
        addMessage(playerName,message);
        if(!isChatVisible){
            hasNewMessage = true;
            chatButton1.setBorder(new LineBorder(Color.BLUE,3));
        }
    }
}
