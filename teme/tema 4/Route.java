package org.example;

public class Route {
    private Location source;
    private Location destination;
    private double time;  // time needed to travel

    public Route(Location source, Location destination, double time) {
        this.source = source;
        this.destination = destination;
        this.time = time;
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

}
