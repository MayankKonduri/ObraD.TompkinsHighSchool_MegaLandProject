package Project.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientListener implements Runnable{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private ClientMain cLientMain;
    public ClientListener(Socket socket, ClientMain clientMain){
        this.socket = socket;
        this.cLientMain = clientMain;
        try{
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.out = new PrintWriter(socket.getOutputStream(), true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void run(){
        try{
            String message;
            while((message = in.readLine()) != null) {
                System.out.println("Received message from server: " + message); // Debug statement
                processMessage(message);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void processMessage(String message) {
        if (message.startsWith("UPDATE_PLAYERS")) {
            String[] playerNames = message.substring("UPDATE_PLAYERS\n".length()).split("\n");
            cLientMain.updatePlayerList(playerNames);
            System.out.println("Updated player list received: " + String.join(", ", playerNames));
        } else if (message.startsWith("PLAYER_LEFT")) {
            String playerName = message.substring("PLAYER_LEFT".length());
            cLientMain.handlePLayerLeft(playerName);
            System.out.println("Player left received: " + playerName);
        }
    }


    public void sendMessage(String message){
        if(out != null){
            System.out.println("Sending message to server: " + message); // Debug statement
            out.println(message);
            out.flush();
        }
    }
    public void close(){
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}