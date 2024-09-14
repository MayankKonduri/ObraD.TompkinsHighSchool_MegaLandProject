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
    private final ArrayList<ObjectOutputStream> clientOutputStreams = new ArrayList<>();
    private final ArrayList<String> gamePlayerNames = new ArrayList<>();
    private final String hostName;
    int port;
    private boolean isRunning = false;

    public ServerMain(int port, String hostName){
        this.port = port;
        this.hostName = hostName;
    }
    public void startServer() {
        isRunning = true;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started on Port: " + port);
            addClientToList(hostName);
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted: " + clientSocket.getInetAddress());

                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                ServerListener serverListener = new ServerListener(clientSocket, this);
                clientListeners.add(serverListener);
                new Thread(serverListener).start();
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
        if(!gamePlayerNames.contains(clientName)){
            gamePlayerNames.add(clientName);

            for(ObjectOutputStream clientOut : clientOutputStreams){
                CommandFromServer.notify_CLIENT_NAME(clientOut, clientName);
            }
            System.out.println("New Client Joined: " + clientName);
        }
    }

    public void removeClientFromList(String clientName) {
        if(gamePlayerNames.remove(clientName)){
            for(ObjectOutputStream clientOut : clientOutputStreams){
                CommandFromServer.notify_CLIENT_DISCONNECTED(clientOut, clientName);
            }
        }
    }
    public synchronized void broadcastMessage(String message){
        for(ObjectOutputStream out: clientOutputStreams){
            CommandFromServer.sendMessage(out, message);
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
    }
    public ArrayList<String> getGamePlayerNames(){
        return gamePlayerNames;
    }

}