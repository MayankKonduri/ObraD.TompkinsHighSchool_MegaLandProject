package Project.src;

import javax.swing.JFrame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Objects;

public class ServerMain{
    private ServerSocket serverSocket;
    private final ArrayList<ServerListener> clientListeners = new ArrayList<>();
    public final ArrayList<ObjectOutputStream> clientOutputStreams = new ArrayList<>();
    public final ArrayList<String> gamePlayerNames = new ArrayList<>();
    private final String hostName;
    int port;
    private boolean isRunning = false;
    private Socket socket;
    private ObjectOutputStream out;
    private HostPanel hostPanel;
    private CharacterSelectPanel characterSelectPanel;
    private ChatPanel chatPanel;
    public ArrayList<Player> playerArrayList_Host = new ArrayList<>();
    private GamePanel gamePanel;
    public ArrayList<String> charactersInLevel_host = new ArrayList<>();
    public int tempInt;


    public ServerMain(int port, String hostName, HostPanel hostPanel, CharacterSelectPanel characterSelectPanel, ChatPanel chatPanel, GamePanel gamePanel){
        this.port = port;
        this.hostName = hostName;
        this.hostPanel = hostPanel;
        this.characterSelectPanel = characterSelectPanel;
        this.chatPanel = chatPanel;
        this.gamePanel = gamePanel;
    }

    public void setChatPanel(ChatPanel chatPanel){
        this.chatPanel = chatPanel;
    }
    public ChatPanel getChatPanel(){ return chatPanel; }

    public void startServer() {
        isRunning = true;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started on Port: " + port);
            gamePlayerNames.add(hostPanel.nameTextField.getText());
            addClientToList(hostName);
            hostPanel.updatePeopleList(gamePlayerNames);
            while (isRunning){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted: " + clientSocket.getInetAddress());
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                out.flush();
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                addClientOutputStream(out);
                ServerListener serverListener = new ServerListener(clientSocket, this, in, out);
                clientListeners.add(serverListener);
                new Thread(serverListener).start();
                System.out.println("SERVER HAS STARTED SUCCESSFULLY");
            }
        } catch (IOException e) {
            if (isRunning) {
                e.printStackTrace();
            }
        }
    }
    public void addClientOutputStream(ObjectOutputStream out) {
        clientOutputStreams.add(out);
    }

    public void removeClientOutputStream(ObjectOutputStream out) {
        clientOutputStreams.remove(out);
    }
    public void addClientToList(String clientName) {
        for(ObjectOutputStream clientOut : clientOutputStreams){
            //CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
            //broadcastMessage(4,clientNameVerify);
            broadcastMessage(8, hostName);
        }
        sendHostList(gamePlayerNames);
    }
    public void addClientToList_Verify(String clientNameVerify) {
        if(!gamePlayerNames.contains(clientNameVerify)){
            gamePlayerNames.add(clientNameVerify);
            for(ObjectOutputStream clientOut : clientOutputStreams){
                //CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
                broadcastMessage(4,clientNameVerify);
                broadcastMessage(8, hostName);
            }
            System.out.println("New Client Joined: " + clientNameVerify);
        }
        hostPanel.playerList_serverSide.add(clientNameVerify);
        hostPanel.updatePeopleList(gamePlayerNames);
        sendHostList(gamePlayerNames);
    }

    public void removeClientFromList(String clientName) {
        if(gamePlayerNames.remove(clientName)) {
            for (ObjectOutputStream clientOut : clientOutputStreams) {
                //CommandFromServer.notify_CLIENT_DISCONNECTED(clientOut, clientName);
                broadcastMessage(5, clientName);
            }
        }
        hostPanel.playerList_serverSide.remove(clientName);
        hostPanel.updatePeopleList(gamePlayerNames);
        sendHostList(gamePlayerNames);
    }
    public void tempFinalAndMessage(String nameAndMessage) {
        String[] finalNameAndMessage = nameAndMessage.split("-");
        System.out.println("Player " + finalNameAndMessage[0] + " Has Sent Message: " + finalNameAndMessage[1]);
        chatPanel.handleIncomingMessage(finalNameAndMessage[0], finalNameAndMessage[1]);
        //CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
        broadcastMessage(11, nameAndMessage);
    }
    public void sendHostList(ArrayList<String> gamePlayerNames){
        String namesString = "";
        for(int i=0; i<gamePlayerNames.size();i++){
            namesString += "-"+gamePlayerNames.get(i);
        }
        namesString = namesString.substring(1);

        broadcastMessage(12, namesString);
    }
    public void processLCNAME(String lcname) {
        gamePanel.GUILevelCardsHost();
        broadcastMessage(13, lcname);
    }
    public void processClickName(String clickName) {
        broadcastMessage(14, clickName);
        if(gamePanel.current_player == gamePlayerNames.indexOf(clickName)){
            gamePanel.playerGameView(gamePanel.current_player);
        }
    }

    public void characterTempChoose(String playerChoosing) {
        String[] characterChosenInfo = playerChoosing.split("-");
        for(ObjectOutputStream clientOut : clientOutputStreams){
            //CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
            broadcastMessage(6, playerChoosing);
        }
        if(characterChosenInfo[1].equals("catB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[0][0] = characterChosenInfo[0];
            temp[0][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("indianWomanB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[1][0] = characterChosenInfo[0];
            temp[1][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("whiteB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[2][0] = characterChosenInfo[0];
            temp[2][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("frogB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[3][0] = characterChosenInfo[0];
            temp[3][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterChosenInfo[1].equals("gandalfB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[4][0] = characterChosenInfo[0];
            temp[4][1] = "NotMine";
            characterSelectPanel.updateAvailability(temp);
        }
    }
    public void characterTempUNChoose(String playerChoosing) {
        String[] characterUNChosenInfo = playerChoosing.split("-");
        for(ObjectOutputStream clientOut : clientOutputStreams){
            //CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
            broadcastMessage(7, playerChoosing);// I fixed now...
        }
        if(characterUNChosenInfo[1].equals("catB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[0][0] = "No_Player";
            temp[0][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("indianWomanB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[1][0] = "No_Player";
            temp[1][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("whiteB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[2][0] = "No_Player";
            temp[2][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("frogB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[3][0] = "No_Player";
            temp[3][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
        else if(characterUNChosenInfo[1].equals("gandalfB")) {
            Object[][] temp = characterSelectPanel.FINAL_ARRAY;
            temp[4][0] = "No_Player";
            temp[4][1] = "Available";
            characterSelectPanel.updateAvailability(temp);
        }
    }
    public synchronized void broadcastMessagePlayers(ArrayList<Player> playerArrayListHost) {
        walletTemp();
        synchronized (clientOutputStreams){
            if(gamePanel!=null){
                gamePanel.LeaderBoardUpdateHost();
            }
            for(ObjectOutputStream out: clientOutputStreams){
                CommandFromServer.notify_PLAYEROBJECT_LIST(out, playerArrayListHost);
            }
        }
        if(playerArrayListHost.size()>1) {
            if(playerArrayList_Host.get(1).getPlayerBuildings() != null){
                System.out.println("1" + playerArrayList_Host.get(1).getPlayerBuildings().isEmpty());
            }
        }

    }
    public synchronized void broadcastMessage(int values, String name){
        switch (values){
            case 1:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_START_GAME(out, name);
                    }}
                break;
            case 2:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_DONE_WITH_CARD_SELECTION(out, name);
                    }}
                break;
            case 3:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_HOST_DISCONNECTED(out, name);
                    }}
                break;
            case 4:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_CLIENT_NAME(out, name);
                    }}
                break;
            case 5:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_CLIENT_DISCONNECTED(out, name);
                    }}
                break;
            case 6:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        String[] characterCombined = name.split("-");
                        CommandFromServer.notify_CHARACTER_SELECTION(out, characterCombined[0], characterCombined[1]);
                    }}
                break;
            case 7:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        String[] characterCombined = name.split("-");
                        CommandFromServer.notify_CHARACTER_UNSELECTION(out, characterCombined[0], characterCombined[1]);
                    }}
                break;
            case 8:
                synchronized (clientOutputStreams){
                    ObjectOutputStream out = clientOutputStreams.get(clientOutputStreams.size()-1);
                    CommandFromServer.notify_HOST_NAME(out, name);
                }
                break;
            case 9:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_ALL_DONE_WITH_CHARACTER_SELECTION(out);
                    }
                }
                break;
            case 10:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_CARDPANEL_TOSTRING(out, name);
                    }
                }
                break;
            case 11:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        String[] messageAndName = name.split("-");
                        CommandFromServer.notify_CHAT_MESSAGE_HOST(out, messageAndName[0], messageAndName[1]);
                    }}
                break;
            case 12:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_PLAYER_LIST(out, name);
                    }}
                break;
            case 13:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_LevelCard_Name(out, name);
                    }}
                break;
            case 14:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_INTERCLICK(out, name);
                    }}
                break;
            case 15:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notifyFinalCharacter(out, name);
                    }}
                break;
            case 16:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_LEVELDISCONNECTION(out, name);
                    }}
                break;
            case 17:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_CHANGEMAIN(out, name);
                    }}
                break;
            case 18:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_ENTERNIGHT(out);
                    }}
                break;
            case 19:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_DONERUNALL(out);
                    }}
                break;
            case 20:
                synchronized (clientOutputStreams){
                    for(ObjectOutputStream out: clientOutputStreams){
                        CommandFromServer.notify_DONENIGHT(out);
                    }}
                break;
        }

    }
    public void stopServer(){
        if(serverSocket != null && !serverSocket.isClosed()) {
            try {
                isRunning = false;
                serverSocket.close();
                for(ServerListener listener : clientListeners){
                    listener.stopListening();
                }
                clientListeners.clear();
                clientOutputStreams.clear();
                gamePlayerNames.clear();
                System.out.println("Server Stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //CommandFromServer.notify_HOST_DISCONNECTED(out, hostName);
        broadcastMessage(3, hostName);
        hostPanel.clearPeopleList();
    }
    public void handleFinalcharacter(String fCharacter) {
        String[] tempFinalChar = fCharacter.split("-");
        charactersInLevel_host.add(tempFinalChar[1]);
        broadcastMessage(15, fCharacter);
    }
    public void finalizeLevelDisconnection(String tempLevelD) {
        String tempCharFinalFinal[] = tempLevelD.split("-");
        gamePanel.updateTempChar(tempCharFinalFinal[1]);
        broadcastMessage(16, tempLevelD);
    }

    public ObjectOutputStream getOut(){
        return out;
    }
    public Socket getSocket(){
        return socket;
    }
    public void setOut(ObjectOutputStream out){
        this.out = out;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    public ArrayList<String> getGamePlayerNames(){
        return gamePlayerNames;
    }
    public void setCharacterSelectPanel(CharacterSelectPanel characterSelectPanel){
        this.characterSelectPanel = characterSelectPanel;
    }
    public void setGamePanel(GamePanel gamePanel) { this.gamePanel = gamePanel;}

    public void walletTemp() {
        if(gamePanel!= null) {
            gamePanel.wallet(playerArrayList_Host.get(gamePlayerNames.indexOf(hostPanel.nameTextField.getText())).getPlayerName(), playerArrayList_Host.get(gamePlayerNames.indexOf(hostPanel.nameTextField.getText())).getPlayerHearts(), playerArrayList_Host.get(gamePlayerNames.indexOf(hostPanel.nameTextField.getText())).getPlayerCoins(), playerArrayList_Host.get(gamePlayerNames.indexOf(hostPanel.nameTextField.getText())).getPlayerJumps());
        }
    }

    public void processChange(String tempChange) {
        tempInt = Integer.parseInt(tempChange);
        gamePanel.start = tempInt;
        playerArrayList_Host.get(0).setCanDrawLevel(tempInt==0);
        broadcastMessagePlayers(playerArrayList_Host);
    }

    public void handleDoneBuy() {
        gamePanel.totalDone++;
        if(gamePanel.totalDone == gamePlayerNames.size()){
            broadcastMessage(18, "Done With Buy Phase");
            gamePanel.buyHearts.setEnabled(false);
            gamePanel.buyHearts.setVisible(false);
            gamePanel.buyBuildings.setEnabled(false);
            gamePanel.buyBuildings.setVisible(false);
            gamePanel.DoneBuy.setEnabled(false);
            gamePanel.DoneBuy.setVisible(false);
            gamePanel.phase.setText("Night Phase");
            gamePanel.DoneNight.setEnabled(true);
            gamePanel.DoneNight.setVisible(true);
        }
    }

    public void handleDoneRun() {
        gamePanel.doneRun++;
        if(gamePanel.doneRun == gamePlayerNames.size()){
            broadcastMessage(19, "Done With Run Phase");
            gamePanel.phase.setText("Buy Phase");
            //gamePanel.phase.setText("xBuy Phase");
            //gamePanel.updateTempChar(null);
        }
    }

    public void handleDoneNight() {
        gamePanel.totalNight++;
        if(gamePanel.totalNight == gamePlayerNames.size()){
            broadcastMessage(20, "Done With Night Phase");
            gamePanel.resetRun();
        }
    }
}