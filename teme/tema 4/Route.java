package org.example;

public class Route {
    private Location source;
    private Location destination;
    private double time;  // time needed to travel
    private double safetyProbability;  // safety probability (0-1)

    public Route(Location source, Location destination, double time, double safetyProbability) {
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.safetyProbability = safetyProbability;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public double getTime() {
        return time;
    }

    public double getSafetyProbability() {
        return safetyProbability;
    }
}
