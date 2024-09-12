package Project.src;

public class CommandFromClient {
    private int command;
    private String data;

    public static final int JOIN = 0;

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
