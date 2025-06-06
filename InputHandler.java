package org.example;

public class InputHandler {
    public static String askForInputClassName() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter class name (e.g., SampleTestClass): ");
        return scanner.nextLine().trim();
    }
} 