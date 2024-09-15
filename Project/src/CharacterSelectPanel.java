package Project.src;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.lang.*;
import java.util.ArrayList;

public class CharacterSelectPanel extends JPanel {
    private BufferedImage indianWoman, gandalf, cat, frog, white;
    private JLabel title = new JLabel ("Choose Your Characters Wisely!");
    private JLabel selected = new JLabel ("");
    private JButton gandalfB = new JButton ("Available");
    private JButton whiteB = new JButton ("Available");
    private JButton indianWomanB = new JButton ("Available");
    private JButton catB = new JButton ("Available");
    private JButton frogB = new JButton ("Available");
    public ArrayList<Boolean> available= new ArrayList<Boolean>();
    private JButton done = new JButton ("Done");
    private boolean isSelected = false;
    private String characterSelected;
    private JFrame jFrame1;
    private ClientMain clientMain;
    private ServerMain serverMain;
    private Boolean isHost;
    private HostPanel hostPanel;
    private ConnectPanel connectPanel;

    private CommandFromClient commandFromClient;
    public Object[][] FINAL_ARRAY = new Object[5][2];


    public CharacterSelectPanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, Boolean isHost) {
        setSize(1920, 1010);
        setLayout(null);

        FINAL_ARRAY[0][0] = "No_Player";
        FINAL_ARRAY[1][0] = "No_Player";
        FINAL_ARRAY[2][0] = "No_Player";
        FINAL_ARRAY[3][0] = "No_Player";
        FINAL_ARRAY[4][0] = "No_Player";
        FINAL_ARRAY[0][1] = "Available";
        FINAL_ARRAY[1][1] = "Available";
        FINAL_ARRAY[2][1] = "Available";
        FINAL_ARRAY[3][1] = "Available";
        FINAL_ARRAY[4][1] = "Available";

        this.jFrame1 = frame;
        this.clientMain = clientMain;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;
        this.connectPanel = connectPanel;
        this.isHost = isHost;

        if(clientMain != null){
            clientMain.setcharacterSelectPanel(this);
        } else{
            System.err.println("ClientMain is not Init.");
        }


        for(int i=0;i<=4;i++){
            available.add(false);
        }


        try {
            indianWoman = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player1.png")));
            gandalf = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player2.png")));
            cat = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player3.png")));
            frog = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player4.png")));
            white = ImageIO.read((new File("Project\\src\\Images\\MegaLand_Player5.png")));


        } catch (Exception ah) {
            ah.printStackTrace();
            System.out.println("Error Loading Images: " + ah.getMessage());
        }
        title.setBounds(610, 50, 700,75);
        title.setFont(new Font("Georgia", Font.BOLD, 50));



        catB.setBounds(145, 800, 180, 30);
        indianWomanB.setBounds(510, 800, 180, 30);
        whiteB.setBounds(875, 800, 180, 30);
        frogB.setBounds(1235, 800, 180, 30);
        gandalfB.setBounds(1600, 800, 180, 30);
        selected.setBounds(680, 900, 700, 70);
        selected.setFont(new Font("Georgia", Font.BOLD, 40));


        catB.addActionListener(e -> {
          if(catB.isEnabled()) {
              if(isSelected)
              {
                  notifyCharacterUNSelection(characterSelected);
              }
              if (catB.getText().equals("Available")) {
                  characterSelected = "catB";
                  reset(catB);
                  catB.setText("Selected");
                  catB.setBackground(Color.GREEN);
                  catB.setForeground(Color.BLACK);
                  available.set(0, true);
                  selected.setText("Your Character is Cat");
                  isSelected = true;
                  System.out.println("Selected: Cat");
                  notifyCharacterSelection("catB");
              } else {
                  characterSelected = "";
                  catB.setText("Available");
                  System.out.println("Unselected: catB");
                  isSelected = false;
                  catB.setBackground(null);
                  catB.setForeground(null);
                  available.set(0, false);
                  selected.setText("Your Character is ");
                      notifyCharacterUNSelection("catB");
              }
              System.out.println(available.toString());
          }
        });
        indianWomanB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
            }
            if(indianWomanB.getText().equals("Available")) {
                characterSelected = "indianWomanB";
                reset(indianWomanB);
                indianWomanB.setText("Selected");
                indianWomanB.setBackground(Color.GREEN);
                indianWomanB.setForeground(Color.BLACK);
                available.set(1,true);
                selected.setText("Your Character is Woman");
                isSelected = true;
                System.out.println("Selected: frogB");
                notifyCharacterSelection("indianWomanB");
            } else {
                characterSelected = "";
                indianWomanB.setText("Available");
                System.out.println("Unselected: indianWomanB");
                isSelected = false;
                indianWomanB.setBackground(null);
                indianWomanB.setForeground(null);
                available.set(1,false);
                selected.setText("Your Character is ");
                    notifyCharacterUNSelection("indianWomanB");
            }
            System.out.println(available.toString());
        });
        whiteB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
            }
            if(whiteB.getText().equals("Available")) {
                characterSelected = "whiteB";
                reset(whiteB);
                whiteB.setText("Selected");
                whiteB.setBackground(Color.GREEN);
                whiteB.setForeground(Color.BLACK);
                available.set(2,true);
                selected.setText("Your Character is White Boy");
                isSelected = true;
                System.out.println("Selected: whiteB");
                notifyCharacterSelection("whiteB");
            } else {
                characterSelected = "";
                whiteB.setText("Available");
                System.out.println("Unselected: whiteB");
                whiteB.setBackground(null);
                whiteB.setForeground(null);
                available.set(2,false);
                isSelected = false;
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("whiteB");
            }
            System.out.println(available.toString());
        });
        frogB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
            }
            if(frogB.getText().equals("Available")) {
                characterSelected = "frogB";
                reset(frogB);
                frogB.setText("Selected");
                frogB.setBackground(Color.GREEN);
                frogB.setForeground(Color.BLACK);
                available.set(3,true);
                selected.setText("Your Character is Froggy");
                isSelected = true;
                System.out.println("Selected: frogB");
                notifyCharacterSelection("frogB");
            } else {
                characterSelected = "";
                frogB.setText("Available");
                System.out.println("Unselected: frogB");
                frogB.setBackground(null);
                frogB.setForeground(null);
                available.set(3,false);
                isSelected = false;
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("frogB");
            }
            System.out.println(available.toString());
        });
        gandalfB.addActionListener(e -> {
            if(isSelected)
            {
                notifyCharacterUNSelection(characterSelected);
            }
            if(gandalfB.getText().equals("Available")) {
                characterSelected = "gandalfB";
                reset(gandalfB);
                gandalfB.setText("Selected");
                gandalfB.setBackground(Color.GREEN);
                gandalfB.setForeground(Color.BLACK);
                available.set(3,true);
                selected.setText("Your Character is Gandalf");
                isSelected = true;
                System.out.println("Selected: gandalfB");
                notifyCharacterSelection("gandalfB");
            } else {
                characterSelected = "";
                gandalfB.setText("Available");
                System.out.println("Unselected: gandalfB");
                gandalfB.setBackground(null);
                gandalfB.setForeground(null);
                available.set(4,false);
                isSelected = false;
                selected.setText("Your Character is ");
                notifyCharacterUNSelection("gandalfB");
            }
            System.out.println(available.toString());
        });
        done.setBounds(1770, 900, 100, 60);
        done.addActionListener(e -> {
            if(isSelected) {
                frame.setContentPane(new GamePanel(frame));
                frame.revalidate();
            } else{
                selected.setText("You need to select a character.");
            }

        });


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(cat, 65, 250, 360, 540, null);
        g.drawImage(indianWoman, 425, 250, 360, 540, null);
        g.drawImage(white, 785, 250, 360, 540, null);
        g.drawImage(frog, 1145, 250, 360, 540, null);
        g.drawImage(gandalf, 1505, 250, 360, 540, null);


        add(title);
        add(selected);
        add(gandalfB);
        add(indianWomanB);
        add(frogB);
        add(whiteB);
        add(catB);
        add(done);
        setVisible(true);
    }

    public void reset(JButton button) {
        available.clear();
        for(int i = 0; i <5; i++) {
            available.add(false);
        }
        gandalfB.setText("Available");
        indianWomanB.setText("Available");
        frogB.setText("Available");
        whiteB.setText("Available");
        catB.setText("Available");
        catB.setBackground(null);
        gandalfB.setForeground(null);
        gandalfB.setBackground(null);
        indianWomanB.setForeground(null);
        indianWomanB.setBackground(null);
        whiteB.setForeground(null);
        whiteB.setBackground(null);
        frogB.setForeground(null);
        frogB.setBackground(null);
        catB.setForeground(null);


    }
    public void switchToGamePanel() {
        jFrame1.setContentPane(new GamePanel(jFrame1));
        jFrame1.revalidate();
    }
    public void notifyCharacterSelection(String characterName){
          try{
              if(isHost){
                  String playerName1 = hostPanel.nameTextField.getText();
                  serverMain.broadcastMessage(6, playerName1 + "-" + characterName);

              }
              else{
                  ObjectOutputStream out = clientMain.getOut();
                  String playerName1 = connectPanel.nameTextField.getText();
                  System.out.println("NotifyingCharacterSelection" + characterName);
                  CommandFromClient.notify_CHARACTER_SELECTION(out, playerName1, characterName);
              }
          }catch (Exception e){
              e.printStackTrace();
          }
      }
    private void notifyCharacterUNSelection(String characterName) {
        try{
            if(isHost){
                String playerName1 = hostPanel.nameTextField.getText();
                serverMain.broadcastMessage(7, playerName1 + "-" + characterName);

            }
            else{
                ObjectOutputStream out = clientMain.getOut();
                String playerName1 = connectPanel.nameTextField.getText();
                System.out.println("NotifyingCharacterUNSelection" + characterName);
                CommandFromClient.notify_UNCHARACTER_SELECTION(out, playerName1, characterName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
      public void updateAvailability(Object[][] FINAL_ARRAY){
            for(int i=0;i<5;i++){
                    if(FINAL_ARRAY[i][1].equals("NotMine"));{
                        if(i==0){
                            catB.setEnabled(false);
                        }
                        if(i==1){
                            indianWomanB.setEnabled(false);
                        }
                        if(i==2){
                            whiteB.setEnabled(false);
                        }
                        if(i==3){
                            frogB.setEnabled(false);
                        }
                        if(i==4){
                            gandalfB.setEnabled(false);
                        }
                    }
                if(FINAL_ARRAY[i][1].equals("Available"));{
                    if(i==0){
                        catB.setEnabled(true);
                    }
                    if(i==1){
                        indianWomanB.setEnabled(true);
                    }
                    if(i==2){
                        whiteB.setEnabled(true);
                    }
                    if(i==3){
                        frogB.setEnabled(true);
                    }
                    if(i==4){
                        gandalfB.setEnabled(true);
                    }
                }
            }
            for(int i=0; i<5;i++){
                for (int j=0;j<2;j++){
                    System.out.println(FINAL_ARRAY[i][j] + "     ||     ");
                }
            }
      }

}
