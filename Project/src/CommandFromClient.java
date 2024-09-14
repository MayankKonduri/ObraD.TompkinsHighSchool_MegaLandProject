package Project.src;

import java.io.Serializable;
public class CommandFromClient implements Serializable
{
    // The command being sent to the server
    private int command;
    // Text data for the command
    private String data ="";


    // Command list
    public static final int START_HOSTING = 0;
    public static final int CONNECT = 1;


    public CommandFromClient(int command, String data) {
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