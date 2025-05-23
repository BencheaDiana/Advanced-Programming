package org.example;
public class Player {
    private String name;
    private ClientThread clientThread;

    public Player(String name, ClientThread clientThread) {
        this.name = name;
        this.clientThread = clientThread;
    }

    public String getName() {
        return name;
    }

    public ClientThread getClientThread() {
        return clientThread;
    }
}
