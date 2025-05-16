package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private static final int PORT = 8100;
    private ServerSocket serverSocket;
    private boolean running = false;

    public static void main(String[] args) {
        GameServer server = new GameServer();
        server.init();
        server.waitForClients();
    }

    private void init() {
        try {
            serverSocket = new ServerSocket(PORT);
            running = true;
            System.out.println("Server started on port " + PORT);
        } catch (IOException e) {
            System.err.println("Error creating server: " + e.getMessage());
        }
    }

    private void waitForClients() {
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket, this).start();
            } catch (IOException e) {
                if (running) {
                    System.err.println("Error accepting client: " + e.getMessage());
                }
            }
        }
    }

    public void stop() throws IOException {
        running = false;
        serverSocket.close();
        System.out.println("Server stopped");
    }
}