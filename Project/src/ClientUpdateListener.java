package Project.src;

public interface ClientUpdateListener {
    void onClientAdded(ClientHandler clientHandler);
    void onClientRemoved(ClientHandler clientHandler);
}