package Project.src;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class ServerListener {
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private ServerMain serverMain;



    private volatile boolean isRunning = true;
    public ServerListener(Socket clientSocket, ServerMain serverMain){
        this.clientSocket = clientSocket;
        this.serverMain = serverMain;
        try{
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }







    public void stopListening(){
        isRunning = false;
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