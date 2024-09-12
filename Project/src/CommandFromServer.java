package Project.src;

import java.io.Serializable;


public class CommandFromServer implements Serializable
{
    public static final int JOIN = 0;
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