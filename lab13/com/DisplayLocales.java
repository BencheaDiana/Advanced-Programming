package com;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales implements Command {
    private ResourceBundle messages;

    public DisplayLocales(ResourceBundle messages) {
        this.messages = messages;
    }

    @Override
    public void execute() {
        System.out.println(messages.getString("locales"));
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale locale : availableLocales) {
            System.out.println(locale.getDisplayName());
        }
    }
} 