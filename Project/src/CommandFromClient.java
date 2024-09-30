package Project.src;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CommandFromClient implements Serializable {

    public static final String CLIENT_NAME = "CLIENT_NAME:";
    public static void notify_CLIENT_NAME(ObjectOutputStream out, String clientName){
        sendMessage(out, CLIENT_NAME + clientName);
    }
    public static final String CLIENT_NAME_VERIFY = "CLIENT_NAME_VERIFY:";
    public static void notify_CLIENT_NAME_VERIFY(ObjectOutputStream out, String clientName_Verify) {
        sendMessage(out, CLIENT_NAME_VERIFY + clientName_Verify);
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
    public static final String VERIFY_NAME = "VERIFY_NAME:";
    public static void notify_VERIFY_NAME(ObjectOutputStream out, String clientName){
        sendMessage(out, VERIFY_NAME + clientName);
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
    public static final String CLIENT_MESSAGE = "CLIENT_MESSAGE:";
    public static void notify_CLIENT_MESSAGE(ObjectOutputStream out, String playerName, String message) {
        sendMessage(out, CLIENT_MESSAGE + playerName + "-" + message);
    }
    public static final String LEVEL_CARD_NAME = "LEVEL_CARD_NAME:";
    public static void notify_LEVEL_CARD_NAME(ObjectOutputStream out, String levelCardName) {
        sendMessage(out, LEVEL_CARD_NAME + levelCardName);
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

    public static void notifyPlayerObject(ObjectOutputStream out, Player playerClient) {
        sendPlayerObject(out, playerClient);
        System.out.println("Before Sending: " + playerClient.getPlayerName());
    }
    public static void sendPlayerObject(ObjectOutputStream out, Player player) {
        try {
            out.reset();
            out.writeObject(player);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}