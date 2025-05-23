package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    protected GameManager gameManager;
    private boolean running;

    public static void main(String[] args) {
        new GameServer().start();
    }

    public GameServer() {
        this.gameManager = new GameManager();
        this.running = false;
    }
    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Server started on port " + PORT);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket, this).start();

            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing server: " + e.getMessage());
        }
        System.out.println("Server stopped");
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
