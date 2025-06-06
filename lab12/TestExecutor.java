package org.example;

import org.junit.jupiter.api.Test;
import java.lang.reflect.*;

public class TestExecutor {
    public static boolean isTestClass(Class<?> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers())) return false;
        for (Method method : clazz.getDeclaredMethods()) {
            if (isTestMethod(method)) return true;
        }
        return false;
    }

    public static boolean isTestMethod(Method method) {
        return method.isAnnotationPresent(Test.class);
    }

    public static boolean invokeTestMethod(Class<?> clazz, Method method) {
        boolean isStatic = Modifier.isStatic(method.getModifiers());
        Object instance = null;
        if (!isStatic) {
            try {
                Constructor<?> ctor = clazz.getDeclaredConstructor();
                ctor.setAccessible(true);
                instance = ctor.newInstance();
            } catch (Exception e) {
                System.out.println("Cannot instantiate " + clazz.getName());
                return false;
            }
        }
        Object[] argsForMethod = generateMockArguments(method.getParameterTypes());
        try {
            method.setAccessible(true);
            method.invoke(instance, argsForMethod);
            System.out.println("Test " + method.getName() + ": PASSED");
            return true;
        } catch (Exception e) {
            System.out.println("Test " + method.getName() + ": FAILED (" + e.getCause() + ")");
            return false;
        }
    }

    private static Object[] generateMockArguments(Class<?>[] paramTypes) {
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> type = paramTypes[i];
            if (type == int.class) args[i] = 0;
            else if (type == boolean.class) args[i] = false;
            else if (type == double.class) args[i] = 0.0;
            else if (type == float.class) args[i] = 0.0f;
            else if (type == long.class) args[i] = 0L;
            else if (type == short.class) args[i] = (short) 0;
            else if (type == byte.class) args[i] = (byte) 0;
            else if (type == char.class) args[i] = '\0';
            else if (type == String.class) args[i] = "";
            else args[i] = null;
        }
        return args;
    }
} 