package Project.src;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ArrayList<Boolean> buildingsSelect = new ArrayList<>(17);
    private JLabel buildingLabel= new JLabel("You have selected 0/7 buildings");
    private JLabel randomLabel = new JLabel("Press to Select Random Buildings");
    private JButton random = new JButton("Random");

    private JButton done = new JButton ("Done");
    private JLabel error = new JLabel("");
    private ServerMain serverMain;
    private HostPanel hostPanel;
    private ClientMain clientMain;
    private JFrame jFrame;

    public CardSelectPanel(JFrame frame, ServerMain serverMain, HostPanel hostPanel) {
        this.jFrame = frame;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;

        setSize(1920, 1010);
        setLayout(null);

        for(int i = 0; i <17; i++) {
            buildingsSelect.add(false);
        }
        try {
            image12 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0012.jpg")));
            image13 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0013.jpg")));
            image14 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0014.jpg")));
            image15 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0015.jpg")));
            image16 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0016.jpg")));
            image17 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0017.jpg")));
            image18 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0018.jpg")));
            image19 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0019.jpg")));
            image20 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0020.jpg")));
            image21 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0021.jpg")));
            image22 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0022.jpg")));
            image23 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0023.jpg")));
            image24 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0024.jpg")));
            image25 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0026.jpg")));
            image26 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0027.jpg")));
            image27 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0028.jpg")));
            image28 = ImageIO.read((new File("Project\\src\\Images\\2024-08-19-10-14-0029.jpg")));

        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(710, 0, 500,75);
        title.setFont(new Font("Georgia", Font.BOLD, 40));
        done.setBounds(1770, 900, 100, 60);
        done.addActionListener(e -> {
            if(selectionCount==7) {
                serverMain.broadcastMessage(2, hostPanel.nameTextField.getText());
                frame.setContentPane(new CharacterSelectPanel(frame, null, serverMain, hostPanel, null, true));
                frame.revalidate();
                CommandFromServer.notify_DONE_WITH_CARD_SELECTION(serverMain.getOut(), hostPanel.nameTextField.getText());
            } else if(selectionCount<7){
                error.setVisible(true);
                int needed = 7 - selectionCount;
                error.setText("You need to select " + needed + " more.");
            } else {
                error.setVisible(true);
                int neededG = selectionCount -7;
                error.setText("You need to deselect " + neededG + ".");
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
        buildingLabel.setBounds(630, 920, 800, 80);
        buildingLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        error.setBounds(730, 890, 500, 40);
        error.setFont(new Font("Georgia", Font.BOLD, 35));
        randomLabel.setBounds(610, 770, 800, 60);
        randomLabel.setFont(new Font("Georgia", Font.BOLD, 40));
        random.setBounds(910,840 , 100, 40);
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
    }

    public void randomSelectedColor(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
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
            button.setBackground(Color.black);
            button.setForeground(Color.WHITE);
        } else {
            button.setText("Select");
            selectionCount--;
            pressed = false;
            String buttonSelected = identifyButton(button);
            int buttonNumber = Integer.parseInt(buttonSelected.substring(buttonSelected.length() - 2));
            buildingsSelect.set(buttonNumber-12,false);
            System.out.println("Unselected: " + selectionCount + pressed);
            buildingLabel.setText("You have selected " + selectionCount + "/7 buildings");
            button.setBackground(null);
            button.setForeground(null);
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

        select12.setBackground(null);
        select12.setForeground(null);
        select13.setBackground(null);
        select13.setForeground(null);
        select14.setBackground(null);
        select14.setForeground(null);
        select15.setBackground(null);
        select15.setForeground(null);
        select16.setBackground(null);
        select16.setForeground(null);
        select17.setBackground(null);
        select17.setForeground(null);
        select18.setBackground(null);
        select18.setForeground(null);
        select19.setBackground(null);
        select19.setForeground(null);
        select20.setBackground(null);
        select20.setForeground(null);
        select21.setBackground(null);
        select21.setForeground(null);
        select22.setBackground(null);
        select22.setForeground(null);
        select23.setBackground(null);
        select23.setForeground(null);
        select24.setBackground(null);
        select24.setForeground(null);
        select25.setBackground(null);
        select25.setForeground(null);
        select26.setBackground(null);
        select26.setForeground(null);
        select27.setBackground(null);
        select27.setForeground(null);
        select28.setBackground(null);
        select28.setForeground(null);

    }
    public void buttonsEnabled() {
        select12.setEnabled(false);
        select13.setEnabled(false);
        select14.setEnabled(false);
        select15.setEnabled(false);
        select16.setEnabled(false);
        select17.setEnabled(false);
        select18.setEnabled(false);
        select19.setEnabled(false);
        select20.setEnabled(false);
        select21.setEnabled(false);
        select22.setEnabled(false);
        select23.setEnabled(false);
        select24.setEnabled(false);
        select25.setEnabled(false);
        select26.setEnabled(false);
        select27.setEnabled(false);
        select28.setEnabled(false);


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
}
