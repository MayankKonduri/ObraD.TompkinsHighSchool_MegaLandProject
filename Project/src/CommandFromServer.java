package Project.src;

import java.io.PrintWriter;
import java.io.Serializable;


public class CommandFromServer implements Serializable
{
    private PrintWriter out;

    public CommandFromServer(PrintWriter out){
        this.out = out;
    }
    public void notifyClientsOfStartGame(){
        sendMessage("START_GAME");
    }
    public void notifyClientsOfCardSelection(){
        sendMessage("CARD_SELECTION_UPDATE");
    }
    public void notifyClientsOfCharacterSelection(String character){
        sendMessage("CHARACTER_SELECTION: " + character);
    }
    public void sendMessage(String message){
        if(out!=null){
            out.println(message);
            out.flush();
        }
    }
}