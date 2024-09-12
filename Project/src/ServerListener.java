package Project.src;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ServerListener implements Runnable{
    private ObjectInputStream is;
    private ObjectOutputStream os;
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    private int playerID;
    public ServerListener(ObjectInputStream is, ObjectOutputStream os, int playerID) {
        this.is = is;
        this.os = os;
        this.playerID = playerID;
        outs.add(os);
    }
    @Override
    public void run() {
        try {
            while (true){
                CommandFromClient cfc = (CommandFromClient) is.readObject();
                if(cfc.getCommand() == CommandFromClient.JOIN) {
                    sendCommand(new CommandFromServer(CommandFromServer.JOIN, null));
                }
            }

        }catch (Exception ec) {
             ec.printStackTrace();
        }

    }

    public void sendCommand(CommandFromServer cfs) {
        for(ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
