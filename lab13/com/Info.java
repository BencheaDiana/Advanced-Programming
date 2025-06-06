package com;

import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info implements Command {
    private ResourceBundle messages;
    private String languageTag;

    public Info(ResourceBundle messages, String languageTag) {
        this.messages = messages;
        this.languageTag = languageTag;
    }

    @Override
    public void execute() {
        Locale locale = Locale.forLanguageTag(languageTag);
        String pattern = messages.getString("info");
        String message = MessageFormat.format(pattern, locale.getDisplayName());
        System.out.println(message);
        
        System.out.println("Country: " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println("Language: " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");

        try {
            Currency currency = Currency.getInstance(locale);
            System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName() + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Currency: Not available for this locale");
        }

        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        String[] weekDays = symbols.getWeekdays();
        System.out.print("Week Days: ");
        for (int i = 1; i < weekDays.length; i++) {
            System.out.print(weekDays[i].toLowerCase());
            if (i < weekDays.length - 1) System.out.print(", ");
        }
        System.out.println();

        String[] months = symbols.getMonths();
        System.out.print("Months: ");
        for (int i = 0; i < months.length; i++) {
            System.out.print(months[i].toLowerCase());
            if (i < months.length - 1) System.out.print(", ");
        }
        System.out.println();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", locale);
        System.out.println("Today: " + dateFormat.format(new java.util.Date()) + 
                         " (" + dateFormat.format(new java.util.Date()) + ")");
    }
} 