package Project.src;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
public class ServerListener implements Runnable {

    private ObjectInputStream is;
    private ObjectOutputStream os;
    private char player;
    private static char turn = 'R';
    private static ArrayList<ObjectOutputStream> outs = new ArrayList<>();
    public static String ipAddress = "";

    public ServerListener(ObjectInputStream is, ObjectOutputStream os) {
        this.is = is;
        this.os = os;
        this.player = player;
        outs.add(os);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String ipA = "";
                CommandFromClient cfc = (CommandFromClient) is.readObject();
                if(cfc.getCommand() == CommandFromClient.START_HOSTING) {
                    System.out.println("Test: " + cfc.getData());
                    String ipH = cfc.getData();
                    ipA = ipH;
                    ipAddress = cfc.getData();
                    System.out.println("Test1: " + ipAddress);
                }
                if(cfc.getCommand() == CommandFromClient.CONNECT) {
                    System.out.println("Connection received");
                    String total = cfc.getData();
                    String[] parts = total.split("\\+");
                    String name = parts[0];
                    String connection = parts[1];
                    System.out.println("hostIP: " + ipAddress + " connectIP: " + connection);
                    if(connection.equals(ipAddress)) {
                        System.out.println("It matches - command sent");
                        sendCommand(new CommandFromServer(CommandFromServer.CONFIRM_CONNECT, total));
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(CommandFromServer cfs) {
        // Sends command to both players
        for (ObjectOutputStream o : outs) {
            try {
                o.writeObject(cfs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}