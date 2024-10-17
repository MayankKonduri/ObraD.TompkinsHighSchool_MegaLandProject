package Project.src;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class CardSelectPanel extends JPanel {
    private BufferedImage image12, image13, image14, image15, image16, image17, image18, image19, image20, image21, image22, image23, image24, image25, image26, image27, image28, image29;
    private JLabel title = new JLabel ("Please select 7 cards.");
    private JButton select12 = new JButton("Select");
    private JButton select13 = new JButton("Select");
    private JButton select14 = new JButton("Select");
    private JButton select15 = new JButton("Select");
    private JButton select16 = new JButton("Select");
    private JButton select17 = new JButton("Select");
    private JButton select18 = new JButton("Select");
    private JButton select19 = new JButton("Select");
    private JButton select20 = new JButton("Select");
    private JButton select21 = new JButton("Select");
    private JButton select22 = new JButton("Select");
    private JButton select23 = new JButton("Select");
    private JButton select24 = new JButton("Select");
    private JButton select25 = new JButton("Select");
    private JButton select26 = new JButton("Select");
    private JButton select27 = new JButton("Select");
    private JButton select28 = new JButton("Select");
    private int selectionCount = 0;
    public ArrayList<Boolean> buildingsSelect = new ArrayList<>(17);
    private JLabel buildingLabel= new JLabel("You have selected 0/7 buildings");
    private JLabel randomLabel = new JLabel("Press to Select Random Buildings");
    private JButton random = new JButton("Random");

    private JButton done = new JButton ("Done");
    private JLabel error = new JLabel("");
    private ServerMain serverMain;
    private HostPanel hostPanel;
    private ClientMain clientMain;
    private JFrame jFrame;
    private CharacterSelectPanel characterSelectPanel;
    private BufferedImage loading;
    public ArrayList<JButton> allButtons = new ArrayList<>();

    public CardSelectPanel(JFrame frame, ServerMain serverMain, HostPanel hostPanel) {
        this.jFrame = frame;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;


        characterSelectPanel = new CharacterSelectPanel(frame, null, serverMain, hostPanel, null, true, this);
        hostPanel.setCharacterSelectPanel(characterSelectPanel);

        setSize(1920, 1010);
        setLayout(null);

        for(int i = 0; i <17; i++) {
            buildingsSelect.add(false);
        }
        try {
            String basePath = "Project" + File.separator + "src" + File.separator + "Images" + File.separator;

            loading = ImageIO.read(new File(basePath + "Character_v.jpg"));
            image12 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0012.jpg"));
            image13 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0013.jpg"));
            image14 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0014.jpg"));
            image15 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0015.jpg"));
            image16 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0016.jpg"));
            image17 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0017.jpg"));
            image18 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0018.jpg"));
            image19 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0019.jpg"));
            image20 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0020.jpg"));
            image21 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0021.jpg"));
            image22 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0022.jpg"));
            image23 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0023.jpg"));
            image24 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0024.jpg"));
            image25 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0026.jpg"));
            image26 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0027.jpg"));
            image27 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0028.jpg"));
            image28 = ImageIO.read(new File(basePath + "2024-08-19-10-14-0029.jpg"));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(710, 0, 500,75);
        title.setFont(new Font("Georgia", Font.BOLD, 40));
        done.setBounds(1770, 900, 100, 60);
        done.setFont(new Font("Georgia", Font.BOLD, 20));
        done.setForeground(Color.white);
        done.setBackground(Color.black);
        done.setBorder(BorderFactory.createEmptyBorder());
        done.setFocusable(false);
        done.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                done.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                done.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        done.addActionListener(e -> {
            if(serverMain!= null) {
                if (selectionCount == 7) {
                    serverMain.broadcastMessage(2, hostPanel.nameTextField.getText());

                    characterSelectPanel.setPreferredSize(new Dimension(1920,1040));

                    JScrollPane scrollPane1 = new JScrollPane(characterSelectPanel);
                    scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                    add(scrollPane1);
                    frame.setContentPane(scrollPane1);

                    frame.pack();
                    frame.revalidate();
                    frame.repaint();
                    frame.setVisible(true);

                    //CommandFromServer.notify_DONE_WITH_CARD_SELECTION(serverMain.getOut(), hostPanel.nameTextField.getText());
                    //CommandFromServer.notify_CARDPANEL_TOSTRING(serverMain.getOut(), buildingsSelect.toString());
                    serverMain.broadcastMessage(10, buildingsSelect.toString());
                } else if (selectionCount < 7) {
                    error.setVisible(true);
                    int needed = 7 - selectionCount;
                    error.setText("You need to select " + needed + " more.");
                } else {
                    error.setVisible(true);
                    int neededG = selectionCount - 7;
                    error.setText("You need to deselect " + neededG + ".");
                }
            }
            else{
                error.setText("ServerMain is Not Initialized");
                error.setVisible(true);
            }
        });

        select12.setBounds(60, 380, 180, 30);
        select12.addActionListener(e -> {
            buildingsSelected(select12);
        });
        select13.setBounds(260, 380, 180, 30);
        select13.addActionListener(e -> {
            buildingsSelected(select13);
        });
        select14.setBounds(460, 380, 180, 30);
        select14.addActionListener(e -> {
            buildingsSelected(select14);
        });
        select15.setBounds(660, 380, 180, 30);
        select15.addActionListener(e -> {
            buildingsSelected(select15);
        });
        select16.setBounds(860, 380, 180, 30);
        select16.addActionListener(e -> {
            buildingsSelected(select16);
        });
        select17.setBounds(1060, 380, 180, 30);
        select17.addActionListener(e -> {
            buildingsSelected(select17);
        });
        select18.setBounds(1260, 380, 180, 30);
        select18.addActionListener(e -> {
            buildingsSelected(select18);
        });
        select19.setBounds(1460, 380, 180, 30);
        select19.addActionListener(e -> {
            buildingsSelected(select19);
        });
        select20.setBounds(1660, 380, 180, 30);
        select20.addActionListener(e -> {
            buildingsSelected(select20);
        });

        select21.setBounds(160, 720, 180, 30);
        select21.addActionListener(e -> {
            buildingsSelected(select21);
        });
        select22.setBounds(360, 720, 180, 30);
        select22.addActionListener(e -> {
            buildingsSelected(select22);
        });
        select23.setBounds(560, 720, 180, 30);
        select23.addActionListener(e -> {
            buildingsSelected(select23);
        });
        select24.setBounds(760, 720, 180, 30);
        select24.addActionListener(e -> {
            buildingsSelected(select24);
        });
        select25.setBounds(960, 720, 180, 30);
        select25.addActionListener(e -> {
            buildingsSelected(select25);
        });
        select26.setBounds(1160, 720, 180, 30);
        select26.addActionListener(e -> {
            buildingsSelected(select26);
        });
        select27.setBounds(1360, 720, 180, 30);
        select27.addActionListener(e -> {
            buildingsSelected(select27);
        });
        select28.setBounds(1560, 720, 180, 30);
        select28.addActionListener(e -> {
            buildingsSelected(select28);
        });
        allButtons.add(select12);
        allButtons.add(select13);
        allButtons.add(select14);
        allButtons.add(select15);
        allButtons.add(select16);
        allButtons.add(select17);
        allButtons.add(select18);
        allButtons.add(select19);
        allButtons.add(select20);
        allButtons.add(select21);
        allButtons.add(select22);
        allButtons.add(select23);
        allButtons.add(select24);
        allButtons.add(select25);
        allButtons.add(select26);
        allButtons.add(select27);
        allButtons.add(select28);

        for(int i = 0; i < allButtons.size(); i++) {
            JButton button = allButtons.get(i);
            button.setFocusable(false);
            button.setForeground(Color.white);
            button.setBackground(Color.BLACK);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setFont(new Font("Georgia", Font.BOLD, 15));
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(new LineBorder(Color.white, 1));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(BorderFactory.createEmptyBorder());
                }
            });

        }

        buildingLabel.setBounds(0, 920, 1920, 80);
        buildingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        buildingLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        buildingLabel.setForeground(Color.white);
        error.setBounds(0, 890, 1920, 40);
        error.setHorizontalAlignment(SwingConstants.CENTER);
        error.setFont(new Font("Georgia", Font.BOLD, 35));
        error.setForeground(Color.WHITE);
        randomLabel.setBounds(0, 770, 1920, 60);
        randomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        randomLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        randomLabel.setForeground(Color.white);
        random.setBounds(890,830 , 140, 50);
        random.setFont(new Font("Georgia", Font.BOLD, 20));
        random.setForeground(Color.white);
        random.setBackground(Color.black);
        random.setBorder(BorderFactory.createEmptyBorder());
        random.setFocusable(false);

        random.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                random.setBorder(new LineBorder(Color.white, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                random.setBorder(BorderFactory.createEmptyBorder());
            }
        });
        random.addActionListener(e -> {
            reset();
            error.setVisible(false);
            //buttonsEnabled();
            buildingsSelect.clear();
            for(int i = 0; i <17; i++) {
                buildingsSelect.add(false);
            }

            ArrayList<Integer> numbers = new ArrayList<>();
            for(int i =0; i <17; i++) {
                numbers.add(i);
            }
            Collections.shuffle(numbers);
            ArrayList<Integer> randomNumbers = new ArrayList<>(numbers.subList(0,7));
            for(int j = 0; j <7; j++) {
                buildingsSelect.set(randomNumbers.get(j), true);
                System.out.println("Finale Random: " + buildingsSelect.toString());
                int buttonRandom = randomNumbers.get(j) + 12;
                switch(buttonRandom) {
                    case 12:
                        select12.setText("Selected");
                        randomSelectedColor(select12);
                        break;
                    case 13:
                        select13.setText("Selected");
                        randomSelectedColor(select13);
                        break;
                    case 14:
                        select14.setText("Selected");
                        randomSelectedColor(select14);
                        break;
                    case 15:
                        select15.setText("Selected");
                        randomSelectedColor(select15);
                        break;
                    case 16:
                        select16.setText("Selected");
                        randomSelectedColor(select16);
                        break;
                    case 17:
                        select17.setText("Selected");
                        randomSelectedColor(select17);
                        break;
                    case 18:
                        select18.setText("Selected");
                        randomSelectedColor(select18);
                        break;
                    case 19:
                        select19.setText("Selected");
                        randomSelectedColor(select19);
                        break;
                    case 20:
                        select20.setText("Selected");
                        randomSelectedColor(select20);
                        break;
                    case 21:
                        select21.setText("Selected");
                        randomSelectedColor(select21);
                        break;
                    case 22:
                        select22.setText("Selected");
                        randomSelectedColor(select22);
                        break;
                    case 23:
                        select23.setText("Selected");
                        randomSelectedColor(select23);
                        break;
                    case 24:
                        select24.setText("Selected");
                        randomSelectedColor(select24);
                        break;
                    case 25:
                        select25.setText("Selected");
                        randomSelectedColor(select25);
                        break;
                    case 26:
                        select26.setText("Selected");
                        randomSelectedColor(select26);
                        break;
                    case 27:
                        select27.setText("Selected");
                        randomSelectedColor(select27);
                        break;
                    case 28:
                        select28.setText("Selected");
                        randomSelectedColor(select28);
                        break;
                    default:
                        break;
                }
                selectionCount = 7;
                buildingLabel.setText("You have selected 7/7 buildings");
            }
        });



        add(select12);
        add(select13);
        add(select14);
        add(select15);
        add(select16);
        add(select17);
        add(select18);
        add(select19);
        add(select20);
        add(select21);
        add(select22);
        add(select23);
        add(select24);
        add(select25);
        add(select26);
        add(select27);
        add(select28);
        add(title);
        add(done);
        add(buildingLabel);
        add(random);
        add(randomLabel);
        add(error);
        setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(serverMain != null) {
                serverMain.broadcastMessage(3, hostPanel.nameTextField.getText());
                serverMain.stopServer();
            }
        }));

        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if(serverMain != null) {
                    serverMain.broadcastMessage(3, hostPanel.nameTextField.getText());
                    serverMain.stopServer();
                }
            }
        });
    }

    public ArrayList<Boolean> getBuildingsSelect() {
        return buildingsSelect;
    }

    public void randomSelectedColor(JButton button1) {
        button1.setForeground(Color.black);
        button1.setBackground(Color.white);
        button1.setBorder(BorderFactory.createEmptyBorder());
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setBorder(new LineBorder(Color.black, 1));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button1.setBorder(BorderFactory.createEmptyBorder());
            }
        });
    }

    public void buildingsSelected(JButton button){
        boolean pressed = false;
        if(button.getText().equals("Select")) {
            button.setText("Selected");
            selectionCount++;
            pressed = true;
            String buttonSelected = identifyButton(button);
            int buttonNumber = Integer.parseInt(buttonSelected.substring(buttonSelected.length() - 2));
            System.out.println(buttonNumber + "hi");
            buildingsSelect.set(buttonNumber-12,true);
            System.out.println("Selected: " + selectionCount + pressed);
            buildingLabel.setText("You have selected " + selectionCount + "/7 buildings");
            button.setBackground(Color.white);
            button.setForeground(Color.black);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(new LineBorder(Color.black, 1));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(BorderFactory.createEmptyBorder());
                }
            });
        } else {
            button.setText("Select");
            selectionCount--;
            pressed = false;
            String buttonSelected = identifyButton(button);
            int buttonNumber = Integer.parseInt(buttonSelected.substring(buttonSelected.length() - 2));
            buildingsSelect.set(buttonNumber-12,false);
            System.out.println("Unselected: " + selectionCount + pressed);
            buildingLabel.setText("You have selected " + selectionCount + "/7 buildings");
            button.setBackground(Color.black);
            button.setForeground(Color.white);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(new LineBorder(Color.white, 1));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(BorderFactory.createEmptyBorder());
                }
            });
        }
        System.out.println("Final: " + buildingsSelect.toString());
    }
    public String identifyButton(JButton button) {
        if(button == select12) {
            return "Button12";
        } else if(button == select13){
            return "Button13";
        }else if(button == select14){
            return "Button14";
        }else if(button == select15){
            return "Button15";
        }else if(button == select15){
            return "Button15";
        }else if(button == select16){
            return "Button16";
        }else if(button == select17){
            return "Button17";
        }else if(button == select18){
            return "Button18";
        }else if(button == select19){
            return "Button19";
        }else if(button == select20){
            return "Button20";
        }else if(button == select21){
            return "Button21";
        }else if(button == select22){
            return "Button22";
        }else if(button == select23){
            return "Button23";
        }else if(button == select24){
            return "Button24";
        }else if(button == select25){
            return "Button25";
        }else if(button == select26){
            return "Button26";
        }else if(button == select27){
            return "Button27";
        }
        return "Button28";
    }
    public void reset(){
        buildingLabel.setText("You have selected 0/7 buildings");
        for(int i = 0; i <7; i++) {
            buildingsSelect.set(i, false);
        }
        select12.setText("Select");
        select13.setText("Select");
        select14.setText("Select");
        select15.setText("Select");
        select16.setText("Select");
        select17.setText("Select");
        select18.setText("Select");
        select19.setText("Select");
        select20.setText("Select");
        select21.setText("Select");
        select22.setText("Select");
        select23.setText("Select");
        select24.setText("Select");
        select25.setText("Select");
        select26.setText("Select");
        select27.setText("Select");
        select28.setText("Select");

        select12.setBackground(Color.black);
        select12.setForeground(Color.white);
        select13.setBackground(Color.black);
        select13.setForeground(Color.white);
        select14.setBackground(Color.black);
        select14.setForeground(Color.white);
        select15.setBackground(Color.black);
        select15.setForeground(Color.white);
        select16.setBackground(Color.black);
        select16.setForeground(Color.white);
        select17.setBackground(Color.black);
        select17.setForeground(Color.white);
        select18.setBackground(Color.black);
        select18.setForeground(Color.white);
        select19.setBackground(Color.black);
        select19.setForeground(Color.white);
        select20.setBackground(Color.black);
        select20.setForeground(Color.white);
        select21.setBackground(Color.black);
        select21.setForeground(Color.white);
        select22.setBackground(Color.black);
        select22.setForeground(Color.white);
        select23.setBackground(Color.black);
        select23.setForeground(Color.white);
        select24.setBackground(Color.black);
        select24.setForeground(Color.white);
        select25.setBackground(Color.black);
        select25.setForeground(Color.white);
        select26.setBackground(Color.black);
        select26.setForeground(Color.white);
        select27.setBackground(Color.black);
        select27.setForeground(Color.white);
        select28.setBackground(Color.black);
        select28.setForeground(Color.white);

        for(int i = 0; i < allButtons.size(); i++) {
            JButton button = allButtons.get(i);
            button.setBorder(BorderFactory.createEmptyBorder());
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBorder(new LineBorder(Color.white, 1));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBorder(BorderFactory.createEmptyBorder());
                }
            });
        }

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loading, 0, 0, 1920, 1050, null);
        g.drawImage(image12, 60, 90, 180, 270, null);
        g.drawImage(image13, 260, 90, 180, 270, null);
        g.drawImage(image14, 460, 90, 180, 270, null);
        g.drawImage(image15, 660, 90, 180, 270, null);
        g.drawImage(image16, 860, 90, 180, 270, null);
        g.drawImage(image17, 1060, 90, 180, 270, null);
        g.drawImage(image18, 1260, 90, 180, 270, null);
        g.drawImage(image19, 1460, 90, 180, 270, null);
        g.drawImage(image20, 1660, 90, 180, 270, null);
        //new line
        g.drawImage(image21, 160, 430, 180, 270, null);
        g.drawImage(image22, 360, 430, 180, 270, null);
        g.drawImage(image23, 560, 430, 180, 270, null);
        g.drawImage(image24, 760, 430, 180, 270, null);

        g.drawImage(image25, 960, 430, 180, 270, null);
        g.drawImage(image26, 1160, 430, 180, 270, null);
        g.drawImage(image27, 1360, 430, 180, 270, null);
        g.drawImage(image28, 1560, 430, 180, 270, null);
    }
    public synchronized CharacterSelectPanel getCharacterSelectPanel(){
        return characterSelectPanel;
    }

    public void setServerMain(ServerMain serverMain) {
        this.serverMain = serverMain;
    }

    //serverMain.broadcastMessage(3, nameTextField.getText());
}
