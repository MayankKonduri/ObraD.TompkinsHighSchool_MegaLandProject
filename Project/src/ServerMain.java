package Project.src;

import javax.swing.JFrame;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerMain{
    private ServerSocket serverSocket;
    private final ArrayList<ServerListener> clientListener = new ArrayList<>();
    int port;
    private boolean isRunning = false;

    public ServerMain(int port){
        this.port = port;
    }
    public void startServer() {
        isRunning = true;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server Started on Port: " + port);
            while (isRunning) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client accepted: " + clientSocket.getInetAddress());

                ServerListener serverListener = new ServerListener(clientSocket);
                clientListener.add(serverListener);
                new Thread(serverListener).start();
            }
        } catch (IOException e) {
            if (isRunning) {
                e.printStackTrace();
            }
        }
    }
    public void stopServer(){
        if(serverSocket != null && !serverSocket.isClosed()) {
            try {
                isRunning = false;
                if(serverSocket != null && !serverSocket.isClosed()) {
                    serverSocket.close();
                }
                for(ServerListener listener : clientListener){
                    listener.stopListening()''
                }
                clientListener.clear();
                System.out.println("Server Stopped");
            } catch (IOException e) {
                e.printStackTrace();
            }
            notifyClientOfHostDisconnection();
        }
    }
}