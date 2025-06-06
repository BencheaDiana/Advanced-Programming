package app;

import com.Command;
import com.DisplayLocales;
import com.Help;
import com.Info;
import com.SetLocale;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    private static ResourceBundle messages;

    public static void main(String[] args) {
        messages = ResourceBundle.getBundle("res.Messages");
        System.out.println(messages.getString("welcome"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(messages.getString("prompt"));
            String command = scanner.nextLine().trim();

            if (command.equals("exit")) {
                break;
            }

            String[] parts = command.split("\\s+");
            Command cmd = null;

            switch (parts[0]) {
                case "display-locales":
                    cmd = new DisplayLocales(messages);
                    break;
                case "set-locale":
                    if (parts.length > 1) {
                        cmd = new SetLocale(messages, parts[1]);
                    }
                    break;
                case "info":
                    if (parts.length > 1) {
                        cmd = new Info(messages, parts[1]);
                    } else {
                        cmd = new Info(messages, Locale.getDefault().toLanguageTag());
                    }
                    break;
                case "help":
                    cmd = new Help(messages);
                    break;
                default:
                    System.out.println(messages.getString("invalid"));
                    continue;
            }

            if (cmd != null) {
                cmd.execute();
            }
        }

        scanner.close();
    }
} 