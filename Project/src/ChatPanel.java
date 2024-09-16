package Project.src;

import javax.swing.*;


public class ChatPanel extends JPanel{
    private JFrame frame1;
    private ClientMain clientMain;
    private ServerMain serverMain;
    private HostPanel hostPanel;
    private ConnectPanel connectPanel;
    private CharacterSelectPanel characterSelectPanel;
    private Boolean isHost1;

    public ChatPanel(JFrame frame, ClientMain clientMain, ServerMain serverMain, HostPanel hostPanel, ConnectPanel connectPanel, CharacterSelectPanel characterSelectPanel, Boolean isHost){
        setSize(400,400);
        setLayout(null);
        this.frame1 = frame;
        this.clientMain = clientMain;
        this.serverMain = serverMain;
        this.hostPanel = hostPanel;
        this.connectPanel = connectPanel;
        this.characterSelectPanel = characterSelectPanel;
        this.isHost1 = isHost;

        if(isHost1){

        }
    }
}
