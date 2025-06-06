package com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale implements Command {
    private ResourceBundle messages;
    private String languageTag;

    public SetLocale(ResourceBundle messages, String languageTag) {
        this.messages = messages;
        this.languageTag = languageTag;
    }

    @Override
    public void execute() {
        Locale locale = Locale.forLanguageTag(languageTag);
        Locale.setDefault(locale);
        String pattern = messages.getString("locale.set");
        String message = MessageFormat.format(pattern, locale.getDisplayName());
        System.out.println(message);
    }
} 