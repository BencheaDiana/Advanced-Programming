
import java.time.LocalTime;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Airport Henri_Coanda = new Airport();
        Henri_Coanda.addRunway("Runway 1");
        Henri_Coanda.addRunway("Runway 2");
        Aircraft aircraft1 = new Airliner("Avion mic", "A1", 64.4,410);
        Aircraft aircraft2 = new Freighter("Avion marfa", "M1", 35.8, 400);
        Aircraft aircraft3 = new Airliner("Avion privat", "A2", 36.0,500);

        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight(aircraft1, LocalTime.of(10, 0), LocalTime.of(10, 30)));
        flights.add(new Flight(aircraft2, LocalTime.of(10, 15), LocalTime.of(10, 45)));
        flights.add(new Flight(aircraft3, LocalTime.of(10, 30), LocalTime.of(11, 0)));

        SchedulingProblem problema = new SchedulingProblem(Henri_Coanda, flights);
        Map<Flight, String> schedule = problema.scheduleFlights();
        
        for (Map.Entry<Flight, String> entry : schedule.entrySet()) {
            System.out.println("Flight " + entry.getKey().getAircraft().getName() + " assigned to " + entry.getValue());
        }
    }
}
