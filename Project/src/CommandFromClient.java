package Project.src;

import java.io.PrintWriter;

public class CommandFromClient {
    private PrintWriter out;

    public CommandFromClient(PrintWriter out){
        this.out = out;
    }

    public void sendStartGame(){
        sendMessage("START_GAME");
    }
    public void sendDoneWithCardSelection(){
        sendMessage("DONE_WITH_CARD_SELECTION");
    }
    public void sendCharacterSelection(String character){
        sendMessage("CHARACTER_SELECTION: " + character);
    }

    public void sendMessage(String message){
        if(out != null){
            out.println(message);
            out.flush();
        }
    }
}
