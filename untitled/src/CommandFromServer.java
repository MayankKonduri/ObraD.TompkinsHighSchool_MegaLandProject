package untitled.src;

import java.io.Serializable;


public class CommandFromServer implements Serializable
{
    private int command;
    private String data ="";


    // Command list goes here


    public CommandFromServer(int command, String data) {
        this.command = command;
        this.data = data;
    }


    public int getCommand() {
        return command;
    }


    public String getData() {
        return data;
    }
}