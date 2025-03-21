package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;

import java.util.*;
import java.util.stream.Collectors;

enum LocationType {
    FRIENDLY, NEUTRAL, ENEMY, TEST
}
public class DroneExplorer {

    public static void main(String[] args) {
        Location[] locations = {
                new Location("Locatie F1", LocationType.FRIENDLY),
                new Location("Locatie F2", LocationType.FRIENDLY),
                new Location("Locatie N1", LocationType.NEUTRAL),
                new Location("Locatie E1", LocationType.ENEMY),
                new Location("Locatie T1", LocationType.TEST),
                new Location("Locatie T2", LocationType.TEST),
                new Location("Locatie F3", LocationType.FRIENDLY),
                new Location("Locatie E2", LocationType.ENEMY),
                new Location("Locatie N2", LocationType.NEUTRAL),
                new Location("Locatie E3", LocationType.ENEMY)
        };

        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Locatii friendly:");
        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.ENEMY)
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.print('\n');
        System.out.println("Locatii enemy:");
        enemyLocations.stream()
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .forEach(System.out::println);
    }
}
