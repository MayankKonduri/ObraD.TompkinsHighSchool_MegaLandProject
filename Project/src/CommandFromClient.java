package Project.src;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;


public class CommandFromClient implements Serializable {

    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static void notify_CLIENT_NAME(ObjectOutputStream out, String clientName){
        sendMessage(out, CLIENT_NAME + clientName);
    }
    public static final String CLIENT_DISCONNECTED = "CLIENT_DISCONNECTED:";
    public static void notify_CLIENT_DISCONNECTED(ObjectOutputStream out, String clientName) {
        try{
            sendMessage(out, CLIENT_DISCONNECTED + clientName);
            out.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public static final String CHARACTER_SELECTION = "CHARACTER_SELECTION:";
    public static void notify_CHARACTER_SELECTION(ObjectOutputStream out, String playerName, String characterName){
        sendMessage(out, CHARACTER_SELECTION + playerName + "-" + characterName);
    }
    public static final String CHARACTER_UNSELECTION = "CHARACTER_UNSELECTION:";
    public static void notify_UNCHARACTER_SELECTION(ObjectOutputStream out, String playerName, String characterName){
        sendMessage(out, CHARACTER_UNSELECTION + playerName + "-" + characterName);
    }
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION:";
    public static void notify_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out, String playerName, String characterName) {
        sendMessage(out, DONE_WITH_CHARACTER_SELECTION + playerName + "-" + characterName);
    }

    public static void sendMessage(ObjectOutputStream out, String message) {
        try {
            if (out != null) {
                out.writeObject(message);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
