
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8100;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to server. Type commands or 'exit' to quit.");

            while (true) {
                System.out.print("Enter command: ");
                String command = consoleInput.readLine();

                if (command == null || command.equals("exit")) {
                    break;
                }

                out.println(command);
                String response = in.readLine();
                System.out.println("Server response: " + response);

                if (response != null && response.equals("Server stopped")) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e);
        }
    }
}