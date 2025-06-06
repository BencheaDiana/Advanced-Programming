package org.example;

public class InputHandler {
    public static String askForInputClassName() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Input your class's name: ");
        return scanner.nextLine().trim();
    }
} 
