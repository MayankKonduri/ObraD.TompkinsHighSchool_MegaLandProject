package Project.src;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;


public class CommandFromServer implements Serializable
{
    public static final String START_GAME = "START_GAME";
    public static void notify_START_GAME(ObjectOutputStream out, String message){
        sendMessage(out, START_GAME);
    }
    public static final String DONE_WITH_CARD_SELECTION = "DONE_WITH_CARD_SELECTION";
    public static void notify_DONE_WITH_CARD_SELECTION(ObjectOutputStream out, String message) {
        sendMessage(out, DONE_WITH_CARD_SELECTION);
    }
    public static final String DONE_WITH_CHARACTER_SELECTION = "DONE_WITH_CHARACTER_SELECTION";
    public static void notify_DONE_WITH_CHARACTER_SELECTION(ObjectOutputStream out, String message) {
        sendMessage(out, DONE_WITH_CHARACTER_SELECTION);
    }
    public static final String HOST_DISCONNECTED = "HOST_DISCONNECTED";
    public static void  notify_HOST_DISCONNECTED(ObjectOutputStream out, String message){
        sendMessage(out, HOST_DISCONNECTED);
    }
    public static final String HOST_NAME = "CLIENT_NAME:";
    public static void notify_HOST_NAME(ObjectOutputStream out, String playerName){
        sendMessage(out, HOST_NAME + playerName);
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