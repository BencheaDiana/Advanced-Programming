package org.example;

public class TestStatistics {
    private int testClasses = 0, testMethods = 0, passed = 0, failed = 0;

    public void incrementTestClasses() { testClasses++; }
    public void incrementTestMethods() { testMethods++; }
    public void incrementPassed() { passed++; }
    public void incrementFailed() { failed++; }

    public void printSummary() {
        System.out.println("\n\nTest Statistics\n");
        System.out.println("Test classes: " + testClasses);
        System.out.println("Test methods: " + testMethods);
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }
} 