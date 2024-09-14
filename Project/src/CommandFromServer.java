package Project.src;

import java.io.Serializable;


public class CommandFromServer implements Serializable
{
    private int command;
    private String data ="";
    public static final int JOIN = 0;
    public static final int CONFIRM_CONNECT = 1;


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