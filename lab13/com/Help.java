package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class Help implements Command {
    private ResourceBundle messages;

    public Help(ResourceBundle messages) {
        this.messages = messages;
    }

    @Override
    public void execute() {
        if (Locale.getDefault().getLanguage().equals("ro")) {
            System.out.println("Comenzi disponibile:");
            System.out.println("display-locales - Afiseaza toate localizarile disponibile in sistem");
            System.out.println("set-locale <language_tag> - Seteaza localizarea curenta (en-US/ro-RO)");
            System.out.println("info [language_tag] - Afiseaza informatii despre localizarea curenta sau specificata");
            System.out.println("help - Afiseaza acest mesaj de ajutor");
            System.out.println("exit - Iesire din aplicatie");
        } else {
            System.out.println("Available commands:");
            System.out.println("display-locales - Shows all available locales in the system");
            System.out.println("set-locale <language_tag> - Sets the current locale (en-US/ro-RO)");
            System.out.println("info [language_tag] - Shows information about the current or specified locale");
            System.out.println("help - Shows this help message");
            System.out.println("exit - Exits the application");
        }
    }
} 