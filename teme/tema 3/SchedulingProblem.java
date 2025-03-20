
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
        int runwayIndex = 0;

        flights.sort(Comparator.comparing(Flight::getLandingStart));

        for (Flight flight : flights) {
            if (runwayIndex >= runways.size()) {
                runwayIndex = 0;
            }
            schedule.put(flight, runways.get(runwayIndex));
            runwayIndex++;
        }

        return schedule;
    }

    @Override
    public String toString() {
        return "SchedulingProblem{" + "airport=" + airport + ", flights=" + flights + '}';
    }
}
