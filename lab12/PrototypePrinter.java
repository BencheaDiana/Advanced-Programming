package org.example;

import java.lang.reflect.*;

public class PrototypePrinter {
    public static void printPrototype(Class<?> clazz) {
        System.out.println("\nClass: " + Modifier.toString(clazz.getModifiers()) + " " + clazz.getName());
        if (clazz.getSuperclass() != null)
            System.out.println("  extends " + clazz.getSuperclass().getName());
        if (clazz.getInterfaces().length > 0) {
            System.out.print("  implements ");
            for (Class<?> iface : clazz.getInterfaces()) {
                System.out.print(iface.getName() + " ");
            }
            System.out.println();
        }
        System.out.println("Fields:");
        for (Field f : clazz.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName());
        }
        System.out.println("Constructors:");
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            System.out.print("  " + Modifier.toString(c.getModifiers()) + " " + clazz.getSimpleName() + "(");
            printParamTypes(c.getParameterTypes());
            System.out.println(")");
        }
        System.out.println("Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            System.out.print("  " + Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            printParamTypes(m.getParameterTypes());
            System.out.println(")");
        }
    }

    private static void printParamTypes(Class<?>[] params) {
        for (int i = 0; i < params.length; i++) {
            System.out.print(params[i].getSimpleName());
            if (i < params.length - 1) System.out.print(", ");
        }
    }
} 