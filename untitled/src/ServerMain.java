package untitled.src;
import java.net.*;
import java.io.*;

public class ServerMain {
    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(8001);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}