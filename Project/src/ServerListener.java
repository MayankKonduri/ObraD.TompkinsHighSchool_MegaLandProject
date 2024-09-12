package Project.src;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class ServerListener {
    private ServerSocket serverSocket;
    private final ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
    private int port;
    private boolean isRunning = false;
    private ClientUpdateListener clientUpdateListener;
    public ServerListener(int port){
        this.port = port;
    }

    public void setClientUpdateListener(ClientUpdateListener listener) {
        this.clientUpdateListener = listener;
    }

    public void start(){
        isRunning = true;
        try{
            System.out.println("Attempting to start the server...");
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started on Port: " + port);

            new Thread(() -> {
                System.out.println("Server thread started. Waiting for client connections...");
                while (isRunning) {
                    try{
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("Client accepted: " + clientSocket.getInetAddress());
                        handleClientConnection(clientSocket);
                    } catch (SocketException e) {
                        if(isRunning){e.printStackTrace();}
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void handleClientConnection(Socket clientSocket){
        try{
            /*String clientID = clientSocket.getInetAddress().toString();

            if(clientHandlers.stream().anyMatch(handler -> handler.getClientSocket().getInetAddress().toString().equals(clientID))){
                System.out.print("Client Already Connected: " + clientID);
                clientSocket.close();
                return;
            }*/

            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            new Thread(clientHandler).start();
            System.out.println("New client handler started for: " + clientSocket.getInetAddress());
            //addClient(clientHandler);
            notifyClientOfNewConnection(clientHandler);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
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
        if(serverSocket != null && !serverSocket.isClosed()) {
            try {
                isRunning = false;
                serverSocket.close();
                synchronized (clientHandlers) {
                    for (ClientHandler handler : clientHandlers) {
                        handler.close();
                    }
                    clientHandlers.clear();
                }
                System.out.println("Server Stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
            notifyClientOfHostDisconnection();
        }
    }
    public synchronized void notifyClientOfHostDisconnection(){
        synchronized (clientHandlers){
            for(ClientHandler handler : clientHandlers){
                handler.getOut().println("HOST_DISCONNECTED");
            }
        }
    }
    public ArrayList<ClientHandler> getClientHandlers(){
        return clientHandlers;  }
}