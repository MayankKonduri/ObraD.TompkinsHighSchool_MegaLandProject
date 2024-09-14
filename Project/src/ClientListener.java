package Project.src;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ClientListener implements Runnable
{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private ConnectPanel connectP = null;
    private HostPanel hostP = null;




    public ClientListener(ObjectInputStream is, ObjectOutputStream os, ConnectPanel connectP, HostPanel hostP) {
        this.is = is;
        this.os = os;
        this.connectP = connectP;
        this.hostP = hostP;



    }


    @Override
    public void run() {
        try
        {
            while(true)
            {
                CommandFromServer cfs = (CommandFromServer)is.readObject();

                if(cfs.getCommand() == CommandFromServer.CONFIRM_CONNECT) {
                    String total = cfs.getData();
                    String[] parts = total.split("\\+");
                    String name = parts[0];
                    String connection = parts[1];
                    System.out.println("Person joined: " + name);
                    hostP.updatePeopleListString(name);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }




}