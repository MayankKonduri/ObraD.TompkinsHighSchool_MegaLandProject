package Project.src;

import javax.swing.*;
import java.io.*;
import java.sql.BatchUpdateException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private String name;
    private PrintWriter out;
    private BufferedReader in;
    private final ServerListener serverListener;
    private ClientMain clientMain;
    private static final int MAX_NAME_LENGTH = 20;
    private DisconnectionListener disconnectionListener;
    public ClientHandler(Socket socket, ServerListener serverListener) throws IOException{
        this.clientSocket = socket;
        this.out = new PrintWriter(socket.getOutputStream(),true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.serverListener = serverListener;
    }

    public void setDisconnectionListener(DisconnectionListener listener){
        this.disconnectionListener = listener;
    }

    public String getName() {
        return name;
    }
    public PrintWriter getOut(){
        return out;
    }
    public BufferedReader getIn() { return in;}

    public void run(){
        try {
            out.println("Please enter your name (max " + MAX_NAME_LENGTH + " characters):");
            name = in.readLine();
            while (name == null || name.trim().isEmpty() || name.length() > MAX_NAME_LENGTH) {
                if (name == null || name.trim().isEmpty()) {
                    out.println("ERROR: Name cannot be empty. Please enter a valid name.");
                    break;
                } else if (name.length() > MAX_NAME_LENGTH) {
                    out.println("ERROR: Name is too long. Please choose a name with 20 characters or fewer.");
                    break;
                }
            }
            serverListener.addClient(this);
            serverListener.notifyClientOfNewConnection(this);

            String message;
            while ((message = in.readLine()) != null) {
                if(message.equals("HOST_DISCONNECTED")){
                    handleHostDisconnected();
                    break;
                }
                System.out.println("Received from " + name + ": " + message);
            }
        }catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }
    public void sendMessage(String message){
        out.println(message);
    }
    public void close(){
        try {
            if(in!=null){ in.close();}
            if(out!=null){ out.close();}
            if(clientSocket!=null && !clientSocket.isClosed()){clientSocket.close();}
        } catch (IOException e){
            e.printStackTrace();
        }finally{
            if(serverListener != null){
                try{
                    serverListener.removeClient(this);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        serverListener.removeClient(this);
    }
    private void handleHostDisconnected(){
        if(disconnectionListener != null){
            disconnectionListener.onHostDisconnected();
        }
    }

    public String toString() {
        return "ClientHandler{name='" + name + "', address=" + clientSocket.getInetAddress() + '}';
    }
    public Socket getClientSocket(){
        return clientSocket;
    }
}