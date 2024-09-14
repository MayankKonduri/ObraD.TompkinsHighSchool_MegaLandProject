//package Project.src;
//
//import java.net.*;
//import java.io.*;
//
//public class ServerMain
//{
//    private Socket          socket   = null;
//    private ServerSocket    server   = null;
//    private DataInputStream in       =  null;
//
//    // constructor with port
//    public ServerMain(int port)
//    {
//        try
//        {
//            server = new ServerSocket(port);
//            System.out.println("Server started");
//
//            System.out.println("Waiting for a client ...");
//
//            socket = server.accept();
//            System.out.println("Client accepted");
//
//            in = new DataInputStream(
//                    new BufferedInputStream(socket.getInputStream()));
//
//            String line = "";
//
//            while (!line.equals("Over"))
//            {
//                try
//                {
//                    line = in.readUTF();
//                    System.out.println(line);
//
//                }
//                catch(IOException i)
//                {
//                    System.out.println(i);
//                }
//            }
//            System.out.println("Closing connection");// close connection
//            socket.close();
//            in.close();
//        }
//        catch(IOException i)
//        {
//            System.out.println(i);
//        }
//    }
//
//    public static void main(String args[])
//    {
//        ServerMain server = new ServerMain(5000);
//    }
//}
package Project.src;
import java.net.*;
import java.io.*;
public class ServerMain {
    public static void main(String[] args) {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8001);
            Socket rCon = serverSocket.accept();
            ObjectOutputStream ros = new ObjectOutputStream(rCon.getOutputStream());
            ObjectInputStream ris = new ObjectInputStream(rCon.getInputStream());

            ros.writeObject(new CommandFromServer(CommandFromServer.JOIN,null));
            ServerListener sl = new ServerListener(ris,ros);
            Thread t = new Thread(sl);
            t.start();

            Socket yCon = serverSocket.accept();
            ObjectOutputStream yos = new ObjectOutputStream(yCon.getOutputStream());
            ObjectInputStream yis = new ObjectInputStream(yCon.getInputStream());

            yos.writeObject(new CommandFromServer(CommandFromServer.JOIN,null));
            System.out.println("Yellow has ");
            sl = new ServerListener(yis,yos);
            t = new Thread(sl);
            t.start();

            Socket aCon = serverSocket.accept();
            ObjectOutputStream aos = new ObjectOutputStream(aCon.getOutputStream());
            ObjectInputStream ais = new ObjectInputStream(aCon.getInputStream());

            aos.writeObject(new CommandFromServer(CommandFromServer.JOIN,null));
            sl = new ServerListener(ais,aos);
            t = new Thread(sl);
            t.start();

            Socket bCon = serverSocket.accept();
            ObjectOutputStream bos = new ObjectOutputStream(bCon.getOutputStream());
            ObjectInputStream bis = new ObjectInputStream(bCon.getInputStream());

            bos.writeObject(new CommandFromServer(CommandFromServer.JOIN,null));
            sl = new ServerListener(bis,bos);
            t = new Thread(sl);
            t.start();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}