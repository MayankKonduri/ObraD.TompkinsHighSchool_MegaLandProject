package Project.src;

import java.io.Serializable;


public class CommandFromServer implements Serializable
{
    private int command;
    private String data ="";
    public static final int JOIN = 0;
    public static final int CONFIRM_CONNECT = 1;
    public static final int VERIFY = 2;
    public static final int NO_VERIFY=3;
    public static final int VERIFY_CONFIRM = 4;



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