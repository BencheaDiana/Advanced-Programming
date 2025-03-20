import java.time.LocalTime;
import java.util.*;

public class SchedulingProblem {
    private Airport airport;
    private List<Flight> flights;

    public SchedulingProblem(Airport airport, List<Flight> flights) {
        this.airport = airport;
        this.flights = flights;
    }

    public Map<Flight, String> scheduleFlights() {
        Map<Flight, String> schedule = new HashMap<>();
        List<String> runways = airport.getRunways();
        int numRunways = runways.size();
        int numFlights = flights.size();

        flights.sort(Comparator.comparing(Flight::getLandingStart));

        int flightsPerRunway = numFlights / numRunways;
        int remainingFlights = numFlights % numRunways;

        int flightIndex = 0;
        for (int i = 0; i < numRunways; i++) {
            int flightsToAssign = flightsPerRunway + (i < remainingFlights ? 1 : 0);
            for (int j = 0; j < flightsToAssign; j++) {
                if (flightIndex < numFlights) {
                    schedule.put(flights.get(flightIndex), runways.get(i));
                    flightIndex++;
                }
            }
        }

        return schedule;
    }

    public int calculateMinimumRunways() {
        int numFlights = flights.size();
        int minRunways = 1;

        while (true) {
            int flightsPerRunway = numFlights / minRunways;
            int remainingFlights = numFlights % minRunways;

            if (flightsPerRunway == 0 || remainingFlights > 1) {
                minRunways++;
            } else {
                break;
            }
        }

        return minRunways;
    }

    @Override
    public String toString() {
        return "SchedulingProblem{" + "airport=" + airport + ", flights=" + flights + '}';
    }
}