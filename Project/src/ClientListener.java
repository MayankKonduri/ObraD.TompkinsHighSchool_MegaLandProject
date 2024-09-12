package Project.src;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.http.HttpConnectTimeoutException;

public class ClientListener implements Runnable {
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private ConnectPanel connectP = null;
    public ClientListener(ObjectInputStream is, ObjectOutputStream os, ConnectPanel connectP) {
        this.is = is;
        this.os = os;
        this.connectP = connectP;

    }
    @Override
    public void run() {
        try {
            while(true) {
                CommandFromServer cfs = (CommandFromServer)is.readObject();
                if(cfs.getCommand() == CommandFromServer.JOIN) {
                    String data = cfs.getData();
                    connectP.setPlayersJoined(data);
                }
            }
        }catch (Exception ec) {
            ec.printStackTrace();
        }

    }
}
