package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ReflectionAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter fully qualified class name: ");
        String className = scanner.nextLine();

        try {
            Class<?> clazz = Class.forName(className);
            System.out.println("\n\nClass Information:\n");
            System.out.println("Name: " + clazz.getName());
            System.out.println("Package: " + clazz.getPackageName());
            System.out.println("Modifiers: " + Modifier.toString(clazz.getModifiers()));

            System.out.println("\n\nMethods:\n");
            for (Method method : clazz.getDeclaredMethods()) {
                System.out.println("  " + Modifier.toString(method.getModifiers()) + " " +
                        method.getReturnType().getSimpleName() + " " +
                        method.getName() + "(" +
                        getParameterTypes(method) + ")");
            }

            System.out.println("\n\nInvoking static methods:\n");
            boolean foundTests = false;
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(org.junit.jupiter.api.Test.class) &&
                    Modifier.isStatic(method.getModifiers()) &&
                    method.getParameterCount() == 0) {
                    foundTests = true;
                    System.out.print("Executing " + method.getName() + "... ");
                    method.setAccessible(true);
                    try {
                        method.invoke(null);
                        System.out.println("Passed");
                    } catch (Exception e) {
                        System.out.println("Failed: " + e.getCause().getMessage());
                    }
                }
            }
            if (!foundTests) {
                System.out.println("No static methods found.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class not found - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static String getParameterTypes(Method method) {
        StringBuilder sb = new StringBuilder();
        Class<?>[] params = method.getParameterTypes();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i].getSimpleName());
            if (i < params.length - 1) sb.append(", ");
        }
        return sb.toString();
    }
} 