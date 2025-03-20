import java.time.LocalTime;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.addRunway("Runway 1");
        airport.addRunway("Runway 2");
        List<Flight> flights = generateRandomFlights(10);

        SchedulingProblem problem = new SchedulingProblem(airport, flights);
        Map<Flight, String> schedule = problem.scheduleFlights();

        System.out.println("Scheduled Flights:");
        for (Map.Entry<Flight, String> entry : schedule.entrySet()) {
            System.out.println("Flight " + entry.getKey().getAircraft().getName() +
                    " assigned to " + entry.getValue());
        }

        int minRunways = problem.calculateMinimumRunways();
        if (minRunways > airport.getRunways().size()) {
            System.out.println("\nEquitable scheduling is not possible with the current number of runways.");
            System.out.println("Minimum runways required: " + minRunways);
        } else {
            System.out.println("\nEquitable scheduling is possible with the current number of runways.");
        }
    }

    private static List<Flight> generateRandomFlights(int numFlights) {
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= numFlights; i++) {
            String model = "Model" + i;
            String name = "Flight" + i;
            double wingspan = 30 + random.nextDouble() * 50; 
            int maxPassengers = 100;
            Aircraft aircraft = new Airliner(model, name, wingspan, maxPassengers);

            int startHour = random.nextInt(24);
            int startMinute = random.nextInt(60);
            LocalTime landingStart = LocalTime.of(startHour, startMinute);
            LocalTime landingEnd = landingStart.plusMinutes(30 + random.nextInt(60));

            flights.add(new Flight(aircraft, landingStart, landingEnd));
        }

        return flights;
    }
}
