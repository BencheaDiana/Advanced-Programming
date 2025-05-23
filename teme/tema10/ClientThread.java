package org.example;
import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {
    private final Socket socket;
    private final GameServer server;
    private PrintWriter out;
    private Player player;
    private Integer currentGameId;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out = new PrintWriter(socket.getOutputStream(), true);

            String request;
            while ((request = in.readLine()) != null) {
                String response = processCommand(request);
                if (response == null) break;
                out.println(response);
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Error closing socket: " + e.getMessage());
            }
        }
    }

    private String processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0].toLowerCase()) {
            case "stop":
                {
                    server.stop();
                    return "Server stopped";
                }

            case "create":
                if (parts.length == 3) {
                    try {
                        int size = Integer.parseInt(parts[1]);
                        int time = Integer.parseInt(parts[2]);
                        if (player == null) {
                            player = new Player("Player" + Thread.currentThread().getId(), this);
                        }
                        currentGameId = server.getGameManager().createGame(size, time, player);
                        return "Game created with ID: " + currentGameId + ". Waiting for opponent...";
                    } catch (NumberFormatException e) {
                        return "Error: Invalid parameters";
                    }
                }
                return "Error: Usage: create <size> <time>";

            case "join":
                if (parts.length == 2) {
                    try {
                        int gameId = Integer.parseInt(parts[1]);
                        if (player == null) {
                            player = new Player("Player" + Thread.currentThread().getId(), this);
                        }
                        boolean success = server.getGameManager().joinGame(gameId, player);
                        if (success) {
                            currentGameId = gameId;
                            return "Joined game " + gameId;
                        }
                        return "Error:  Could not join game";
                    } catch (NumberFormatException e) {
                        return "Error: Invalid game ID";
                    }
                }
                return "Error: Usage: join <gameId>";

            case "move":
                if (parts.length == 3 && player != null && currentGameId != null) {
                    try {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        HexGame game = server.getGameManager().getGame(currentGameId);
                        if (game != null) {
                            boolean valid = game.makeMove(player, x, y);
                            return valid ? "Move accepted" : "Error: Invalid move";
                        }
                        return "Error: Not in a valid game";
                    } catch (NumberFormatException e) {
                        return "Error: Invalid coordinates";
                    }
                }
                return "Error: Usage: move <x> <y>";
            case "time":
                if (currentGameId != null) {
                    HexGame game = server.getGameManager().getGame(currentGameId);
                    if (game != null && game.isStarted() && !game.isFinished()) {
                        int playerIndex = game.getPlayers().indexOf(player);
                        if (playerIndex >= 0) {
                            return "Your time remaining: " + game.getTimer().getTime(playerIndex) + "s";
                        }
                    }
                }
                return "Error: Could not get time";

            case "exit":
                return null;

            default:
                return "Error: Unknown command";
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }
}
