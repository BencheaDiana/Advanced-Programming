import java.io.*;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8100;
    private boolean running;

    public static void main(String[] args) {
        new GameClient().start();
    }

    public void start() {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to Hex Game Server");
            printHelp();

            running = true;

            new Thread(() -> {
                try {
                    String response;
                    while (running && (response = in.readLine()) != null) {
                        System.out.println("> " + response);
                    }
                } catch (IOException e) {
                    if (running) System.err.println("Connection lost: " + e.getMessage());
                }
            }).start();

            while (running) {
                String input = console.readLine();
                if (input == null || input.equalsIgnoreCase("exit")) {
                    out.println("exit");
                    running = false;
                } else {
                    out.println(input);
                }
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
        System.out.println("Disconnected from server");
    }

    private void printHelp() {
        System.out.println("Available commands:");
        System.out.println("create <size> <time>: Create new game (e.g. 'create 11 300')");
        System.out.println("join <gameId>: Join existing game");
        System.out.println("move <x> <y>: Make a move (e.g. 'move 5 5')");
        System.out.println("exit: Quit the game");
    }
}