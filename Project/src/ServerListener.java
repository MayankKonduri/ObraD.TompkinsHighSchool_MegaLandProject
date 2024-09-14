package Project.src;

import java.io.*;
import java.net.Socket;

    public class ServerListener implements Runnable{
        private Socket clientSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private ServerMain serverMain;
        private volatile boolean isRunning = true;

        public static final String CLIENT_NAME = "CLIENT_NAME:";
        public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
        public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
        public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";

    /*
    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static void notify_CLIENT_NAME(ObjectOutputStream out, String clientName){
        sendMessage(out, CLIENT_NAME + clientName);
    }

    public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
    public static void notify_CLIENT_DISCONNECTED(ObjectOutputStream out, String clientName) {
        sendMessage(out, CLIENT_DISCONNECTED + clientName);
    }
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static void notify_CHARACTER_SELECTION(ObjectOutputStream out, String playerName, String characterName){
        sendMessage(out, CHARACTER_SELECTION + playerName + "-" + characterName);
    }
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static void notify_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out, String playerName) {
        sendMessage(out, DONE_WITH_CHARACTER_SELECTION + playerName);
    }
     */

        public ServerListener(Socket clientSocket, ServerMain serverMain, ObjectInputStream in, ObjectOutputStream out) {
            this.clientSocket = clientSocket;
            this.serverMain = serverMain;
            this.in = in;
            this.out = out;
        }
        public void run(){
            try{
                String message;
                while(isRunning && (message = (String) in.readObject()) != null) {
                    System.out.println("Received Message From Client:           " + message); // Debug statement
                    processMessage(message);
                }
            }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            } finally {
                stopListening();
            }
        }
        private void processMessage(String message) {
            if (message.startsWith(CLIENT_NAME)) {
                handleUpdatePlayers_Client(message);
            }else if (message.startsWith(CLIENT_DISCONNECTED)) {
                handleClientLeft(message);
            } else if(message.startsWith(CHARACTER_SELECTION)){
                handle_CharacterSelection(message);
            } else if(message.startsWith(DONE_WITH_CHARACTER_SELECTION)) {
                halfway_CHARACTER_SELECTION(message);
            } else{
                System.out.println("Received Message: " + message); //chat-feature for Mr. Nischal and Mr. Ayan, as this is abstract Message
            }
        }

        public void handleUpdatePlayers_Client(String message){
            String clientName = message.substring(CLIENT_NAME.length());
            serverMain.addClientToList(clientName);
            System.out.println("New Client Joined: " + clientName);
        }
        public void handleClientLeft(String message){
            String clientName = message.substring(CLIENT_DISCONNECTED.length());
            serverMain.removeClientFromList(clientName);
            System.out.println("Host " + clientName + " Disconnected");
        }
        public String[] handle_CharacterSelection(String message){
            String playerChoosing = message.substring(CHARACTER_SELECTION.length());
            String[] characterChosenInfo = playerChoosing.split("-");
            System.out.println("Player " + characterChosenInfo[0] + " Has Chosen Character " + characterChosenInfo[1]);
            return characterChosenInfo;
        }
        public String[] halfway_CHARACTER_SELECTION(String message){
            String finalPlayerChoosing = message.substring(DONE_WITH_CHARACTER_SELECTION.length());
            String[] finalCharacterChosenInfo = finalPlayerChoosing.split("-");
            System.out.println("Player " + finalCharacterChosenInfo[0] + " Has Finalized Character " + finalCharacterChosenInfo[1]);
            return finalCharacterChosenInfo;
        }

    public void stopListening(){
        isRunning = false;
        serverMain.removeClientOutputStream(out);
        try{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
            if(clientSocket!=null && !clientSocket.isClosed()){
                clientSocket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /*
    public void handleClientConnection(Socket clientSocket){
        try {
            String clientID = clientSocket.getInetAddress().toString();

            if(clientHandlers.stream().anyMatch(handler -> handler.getClientSocket().getInetAddress().toString().equals(clientID))){
                System.out.print("Client Already Connected: " + clientID);
                clientSocket.close();
                return;

        }}
    public synchronized void addClient(ClientHandler clientHandler){
        clientHandlers.add(clientHandler);
        System.out.println("Client added: " + clientHandler.getName());
        printClientHandlers();
        if(clientUpdateListener != null){
            clientUpdateListener.onClientAdded(clientHandler);
        }
    }

    public void printClientHandlers() {
        System.out.println("Current Clients:");
        for (ClientHandler clientHandler : clientHandlers) {
            System.out.println("Client: " + clientHandler.getName() + " at " + clientHandler.getClientSocket().getInetAddress());
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler){
        synchronized (clientHandlers){
            clientHandlers.remove(clientHandler);}
        if(clientUpdateListener!=null){
            clientUpdateListener.onClientRemoved(clientHandler);
        }
        notifyClientOfNewConnection(clientHandler);
    }
    public synchronized void notifyClientOfNewConnection(ClientHandler newClient){
        synchronized (clientHandlers){
            for(ClientHandler handler : clientHandlers){
                if(!handler.equals(newClient)){
                    handler.sendMessage("NEW_CLIENT" + newClient.getName());
                    System.out.println("Notified client of new connection: " + newClient.getName());

                }
            }
        }
    }
    private synchronized void notifyClientOfDisconnection(ClientHandler disconnectedClient){
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                handler.getOut().println("PLAYER_LEFT\n" + disconnectedClient.getName());
            }
        }
    }
    public synchronized void stop(){

    }
    public synchronized void notifyClientOfHostDisconnection(){
        synchronized (clientHandlers){
            for(ClientHandler handler : clientHandlers){
                handler.getOut().println("HOST_DISCONNECTED");
            }
        }
    }
    public ArrayList<ClientHandler> getClientHandlers(){
        return clientHandlers;  }*/
}