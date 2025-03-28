package org.example;

import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.*;
import java.util.stream.Collectors;

enum LocationType {
    FRIENDLY, NEUTRAL, ENEMY
}
public class DroneExplorer {

    public static void main(String[] args) {
        Faker faker = new Faker();

        Location[] locations = {
                new Location(faker.address().cityName(), LocationType.FRIENDLY),
                new Location(faker.address().cityName(), LocationType.NEUTRAL),
                new Location(faker.address().cityName(), LocationType.ENEMY),
                new Location(faker.address().cityName(), LocationType.FRIENDLY),
                new Location(faker.address().cityName(), LocationType.FRIENDLY),
                new Location(faker.address().cityName(), LocationType.FRIENDLY),
                new Location(faker.address().cityName(), LocationType.FRIENDLY),
                new Location(faker.address().cityName(), LocationType.ENEMY),
                new Location(faker.address().cityName(), LocationType.ENEMY),
                new Location(faker.address().cityName(), LocationType.ENEMY),
                new Location(faker.address().cityName(), LocationType.NEUTRAL),
                new Location(faker.address().cityName(), LocationType.NEUTRAL)
        };

        Map<LocationType, List<Location>> locationsByType = Arrays.stream(locations)
                .collect(Collectors.groupingBy(Location::getType));
        Graph<Location, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        for (Location location : locations) {
            graph.addVertex(location);
        }
        List<Route> routes = Arrays.asList(
                new Route(locations[0], locations[3], 10.0),
                new Route(locations[1], locations[11], 15.0),
                new Route(locations[2], locations[9], 20.0),
                new Route(locations[1], locations[10], 12.0),
                new Route(locations[0], locations[5], 25.0)
        );

        for (Route route : routes) {
            DefaultWeightedEdge edge = graph.addEdge(route.getSource(), route.getDestination());
            graph.setEdgeWeight(edge, route.getTime());
        }
        Location startLocation = locations[0];

        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstraShortestPath =
                new DijkstraShortestPath<>(graph);

        LocationType[] typeOrder = {LocationType.FRIENDLY, LocationType.NEUTRAL, LocationType.ENEMY};

        for (LocationType type : typeOrder) {
            System.out.println("\n" + "Distanta de la " + startLocation.getName() + " la locatiiile de tip "+ type + ":");

            List<Location> typedLocations = locationsByType.get(type);
            for (Location destination : typedLocations) {
                if (!destination.equals(startLocation)) {
                    double time = dijkstraShortestPath.getPathWeight(startLocation, destination);
                    if(time<100000) System.out.println(destination.getName() + ": " + time + " time units");
                    else if(type!=startLocation.getType()) System.out.println(startLocation.getName() + " nu este " + type+ ", deci nu se poate ajunge la " + destination.getName());
                    else System.out.println("Nu exista drum de la " + startLocation.getName() + " la " + destination.getName());
                }
            }
        }
    }
}