
import java.time.LocalTime;

public class Flight {
    private Aircraft aircraft;
    private LocalTime landingStart;
    private LocalTime landingEnd;

    public Flight(Aircraft aircraft, LocalTime landingStart, LocalTime landingEnd) {
        this.aircraft = aircraft;
        this.landingStart = landingStart;
        this.landingEnd = landingEnd;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public LocalTime getLandingStart() {
        return landingStart;
    }

    public LocalTime getLandingEnd() {
        return landingEnd;
    }

    @Override
    public String toString() {
        return "Flight{" + "aircraft=" + aircraft + ", landingStart=" + landingStart + ", landingEnd=" + landingEnd + '}';
    }
}
