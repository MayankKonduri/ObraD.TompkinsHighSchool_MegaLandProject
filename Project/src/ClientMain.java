package Project.src;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class ClientMain
{
    public static void main(String[] args)
    {
        try {
            // create an object for the TTT game



            // create a connection to server
            Socket socket = new Socket("192.168.40.54",8001);
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());


            // determine if playing as X or O
            CommandFromServer cfs = (CommandFromServer) is.readObject();
            Frame frame = new Frame(os);



            // Create the Frame based on which player the server says this client is
//            if(cfs.getCommand() == CommandFromServer.JOIN)
//                frame = new Frame(os);
//            else
//                frame = new Frame(os);
            ConnectPanel connectP = new ConnectPanel(frame, os);
            HostPanel hostP = new HostPanel(frame, os);



            // Starts a thread that listens for commands from the server
            ClientListener cl = new ClientListener(is, os, connectP, hostP);
            Thread t = new Thread(cl);
            t.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}