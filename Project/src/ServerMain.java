package Project.src;

import javax.swing.JFrame;

public class ServerMain{
    public static void main(String[] args){
        /*JFrame frame = new JFrame("Host");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1040);
        frame.setContentPane(new HostPanel(frame));
        frame.setVisible(true);*/
        int port = 12345;
        ServerListener serverListener = new ServerListener(port);
        serverListener.start();

    }
}