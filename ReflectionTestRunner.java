package org.example;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class ReflectionTestRunner {
    public static void main(String[] args) throws Exception {
        String className = InputHandler.askForInputClassName();

        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            try {
                clazz = Class.forName("org.example." + className);
            } catch (ClassNotFoundException ex) {
                System.out.println("Class not found: " + className);
                return;
            }
        }

        TestStatistics stats = new TestStatistics();
        PrototypePrinter.printPrototype(clazz);

        if (TestExecutor.isTestClass(clazz)) {
            stats.incrementTestClasses();
            for (var method : clazz.getDeclaredMethods()) {
                if (TestExecutor.isTestMethod(method)) {
                    stats.incrementTestMethods();
                    boolean passed = TestExecutor.invokeTestMethod(clazz, method);
                    if (passed) stats.incrementPassed();
                    else stats.incrementFailed();
                }
            }
        }
        stats.printSummary();
    }
} 